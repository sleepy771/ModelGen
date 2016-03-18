package me.sleepyprojects.modelgen.data;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.TemplateId;
import me.sleepyprojects.modelgen.TemplateManager;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.File;
import java.io.StringReader;
import java.util.HashMap;

public class TemplateManagerImpl implements TemplateManager {

    private HashMap<String, ITemplateModel> modelMap;
    private static TemplateManagerImpl INSTANCE;

    private TemplateManagerImpl() {
        modelMap = new HashMap<>();
        INSTANCE = this;
    }

    public ITemplateModel get(String id) {
        if (!modelMap.containsKey(id)) {
            throw new RuntimeException(String.format("Template %s does not exist!", id)); // Create manager exception
        }
        return modelMap.get(id);
    }

    void loadTemplates(Iterable<TemplateModel> models) {
        for (TemplateModel model : models) {
            register(model);
        }
    }

    public void register(TemplateModel model) {
        if (modelMap.containsKey(model.getId())) {
            throw new RuntimeException(String.format("Template %s already registered!", model.getId()));
        }
        modelMap.put(model.getId(), model);
    }

    void unregister(TemplateModel model) {
        modelMap.remove(model.getId());
    }

    public static TemplateManagerImpl create(File file) {
        if (TemplateManagerImpl.INSTANCE != null) {
            return TemplateManagerImpl.INSTANCE;
        }
        final XMLLangParser parser = new XMLLangParser(file);
        final TemplateManagerImpl manageris = new TemplateManagerImpl();
        manageris.loadTemplates(parser.getModels());
        return manageris;
    }

    public static TemplateManagerImpl getInstance() {
        if (TemplateManagerImpl.INSTANCE == null) {
            throw new RuntimeException("Template manager was not instantiated");
        }
        return TemplateManagerImpl.INSTANCE;
    }

    @Override
    public Template loadTemplate(Block block) {
        final String id = block.useTemplate();
        final ITemplateModel model = get(id);
        final TemplateModelProxy proxy = TemplateModelProxy.create(model);
        if (!proxy.hasVelocityTemplate()) {
            try {
                initTemplate(proxy);
            } catch (ParseException pe) {
                throw new RuntimeException(pe);
            }
        }
        return proxy.getVelocityTemplate();
    }

    private void initTemplate(final @NotNull TemplateModelProxy proxy) throws ParseException {
        final String templateStr = proxy.getTemplate();
        final String preparedTpl = templateStr.replaceAll("\\s+", " ").replaceAll("\\\\\\\\", "\n");
        final RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        final StringReader templateReader = new StringReader(preparedTpl);
        final SimpleNode node = runtimeServices.parse(templateReader, proxy.getId());
        final Template template = new Template();
        template.setRuntimeServices(runtimeServices);
        template.setData(node);
        template.initDocument();
        proxy.setVelocityTemplate(template);
    }
}
