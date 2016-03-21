package me.sleepyprojects.modelgen.data;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Modifier;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLLangParser {

    private final File file;
    private Document document;
    private Set<TemplateModel> models;
    private static XMLLangParser instance;

    public XMLLangParser(final File file) {
        this.file = file;
    }

    public static XMLLangParser getInstance() {
        return instance;
    }

    public void init() throws ParserConfigurationException, IOException,
            SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(file);

        document.getDocumentElement().normalize();
    }

    private List<Element> getElements(Element element, String name) {
        NodeList list = element.getElementsByTagName(name);
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                elements.add((Element) list.item(i));
            }
        }
        return elements;
    }

    private Element getElement(Element element, String name) {
        NodeList list = element.getElementsByTagName(name);
        if (list.getLength() != 1) {
            throw new RuntimeException(String.format("Invalid node list size %d of %s in element %s", list.getLength(), name, element.getAttribute("id")));
        }
        if (list.item(0).getNodeType() == Node.ELEMENT_NODE) {
            return (Element) list.item(0);
        }
        return null;
    }

    private Set<VariableModel> loadVariables(final Element element) {
        final Element variables = getElement(element, "variables");
        if (variables == null) {
            throw new RuntimeException("Variables node does not exist");
        }
        final Set<VariableModel> models = new HashSet<>();
        for (final Element variableElement : getElements(variables, "variable")) {
            final String id = variableElement.getAttribute("id");
            final String type = variableElement.getAttribute("type");
            final VariableModel model = new VariableModel();
            model.setBaseType(ClassBinder.getInstance().get(type));
            model.setId(id);
            model.setIterable(type.endsWith("[]"));
            models.add(model);
        }
        return models;
    }

    public Set<TemplateModel> getModels() {
        if (models == null) {
            try {
                init();
            } catch (IOException | ParserConfigurationException | SAXException e) {
                throw new RuntimeException("NOPE");
            }
            models = loadTemplates(document.getDocumentElement());
        }
        return models;
    }

    private Set<TemplateModel> loadTemplates(final Element element) {
        final Element templates = getElement(element, "templates");
        if (templates == null) {
            throw new RuntimeException("Templates does not exist");
        }
        final Set<TemplateModel> templateModels = new HashSet<>();
        for (final Element templateElement : getElements(templates, "template")) {
            final String id = templateElement.getAttribute("id");
            final String scopeAttr = templateElement.getAttribute("scope");
            final String[] scopes = scopeAttr.split(",");
            final Set<VariableModel> variables = loadVariables(templateElement);
            final Element velocity = getElement(templateElement, "velocity");
            if (velocity == null) {
                throw new NullPointerException("Velocity not found");
            }
            final String[] velocityContextLines = velocity.getTextContent().trim().split("\n");
            final StringBuilder velocityContext = new StringBuilder();
            for (int i = 0; i < velocityContextLines.length; i++) {
                velocityContext.append(velocityContextLines[i].trim());
                if (velocityContextLines.length - 1 == i) {
                    continue;
                }
                velocityContext.append("\n");
            }
            final TemplateModel model = new TemplateModel();
            templateModels.add(model);
            model.setId(id);
            model.setTemplate(velocityContext.toString());
            model.setVariables(new HashSet<>(variables));
            model.setScopes(new HashSet<>(Arrays.asList(scopes)));
        }
        return templateModels;
    }
}
