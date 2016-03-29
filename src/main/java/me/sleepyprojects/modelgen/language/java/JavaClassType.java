package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.FlatPart;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.Part;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.BaseNamedType;
import me.sleepyprojects.modelgen.language.BuildMultiple;
import me.sleepyprojects.modelgen.language.CanAppend;
import me.sleepyprojects.modelgen.language.ClassType;
import me.sleepyprojects.modelgen.language.FieldType;
import me.sleepyprojects.modelgen.language.HasAnnotations;
import me.sleepyprojects.modelgen.language.HasInterfaces;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.MethodType;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

public class JavaClassType extends BaseNamedType implements ClassType, HasModifiers, HasAnnotations, HasInterfaces {

    private BuildMultiple<FieldType> fieldList;
    private BuildMultiple<MethodType> methodList;
    private BuildMultiple<AnnotationType> annotations;
    private MultiPart<Modifier> modifiers;
    private Meta superClass;
    private MultiPart<Meta> interfaces;

    public JavaClassType() {
        fieldList = new BuildMultiple<>(new ArrayList<>(), "fields", "class-fields", CanAppend.uniqueSignature());
        methodList = new BuildMultiple<>(new ArrayList<>(), "methods", "class-methods", CanAppend.uniqueSignature());
        annotations = new BuildMultiple<>(new ArrayList<>(), "annotations", "class-annotations", CanAppend.unique());
        modifiers = new MultiPart<>(new TreeSet<>(), "modifiers", ((collection, element) -> element.getSupportedTypes().contains(BlockType.TYPE) && !collection.contains(element)));
        interfaces = new MultiPart<>(new ArrayList<>(), "interfaces", CanAppend.unique());
    }

    @Override
    public void addSuperType(final @NotNull Meta superType) {
        if (superType.getType() == Meta.Type.TYPE) {
            if (superClass != null) {
                throw new RuntimeException("Java does not support multidimensional inheritance");
            }
            superClass = superType;
        }
        if (superType.getType() == Meta.Type.INTERFACE) {
            addInterface(superType);
        }
        throw new RuntimeException("Can not set supper type");
    }

    @Override
    public void addMethod(MethodType method) {
        methodList.add(method);
    }

    @Override
    public void addField(FieldType field) {
        fieldList.add(field);
    }

    @Override
    public boolean addAnnotation(final @NotNull AnnotationType annotation) {
        return annotations.add(annotation);
    }

    @Override
    public boolean addModifier(final @NotNull Modifier modifier) {
        return modifiers.add(modifier);
    }

    @Override
    public void addInterface(Meta superType) {
        interfaces.add(superType);
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Part> partMap) {
        partMap.put("name", new FlatPart(getName()));
        if (superClass != null) {
            partMap.put("superClass", superClass);
        }
        blockMap.put("interfaces", modifiers.create());
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("methods", methodList.create());
        blockMap.put("fields", fieldList.create());

    }
}
