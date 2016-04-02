package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Bind;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.ClassDefinition;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.Modifier;
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
import me.sleepyprojects.modelgen.language.ModifierType;
import me.sleepyprojects.modelgen.language.MultiPart;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

public class JavaClassType extends BaseNamedType implements ClassType<JavaMarker>, HasInterfaces, HasModifiers<JavaMarker>, HasAnnotations<JavaMarker> {

    private BuildMultiple<JavaFieldType> fieldList;
    private BuildMultiple<JavaMethodType> methodList;
    private BuildMultiple<JavaAnnotationType> annotations;
    private MultiPart<JavaModifierType> modifiers;
    private Type superClass;
    private MultiPart<Type> interfaces;

    public JavaClassType() {
        fieldList = new BuildMultiple<>(new ArrayList<>(), "fields", "class-fields", CanAppend.uniqueSignature());
        methodList = new BuildMultiple<>(new ArrayList<>(), "methods", "class-methods", CanAppend.uniqueSignature());
        annotations = new BuildMultiple<>(new ArrayList<>(), "annotations", "class-annotations", CanAppend.unique());
        modifiers = new MultiPart<>(new TreeSet<>(), "modifiers", ((collection, element) -> element.getSupportedTypes().contains(BlockType.TYPE) && !collection.contains(element)));
        interfaces = new MultiPart<>(new ArrayList<>(), "interfaces", CanAppend.unique());
    }

    @Override
    public void addSuperType(final @NotNull Type superType) {
        if (superType.getMetaType() == Type.MetaType.TYPE) {
            if (superClass != null) {
                throw new RuntimeException("Java does not support multidimensional inheritance");
            }
            superClass = superType;
        }
        if (superType.getMetaType() == Type.MetaType.INTERFACE) {
            addInterface(superType);
        }
        throw new RuntimeException("Can not set supper type");
    }

    @Override
    public void addMethod(MethodType<JavaMarker> method) {

    }

    @Override
    public void addField(FieldType<JavaMarker> field) {

    }

    public void addMethod(JavaMethodType method) {
        methodList.add(method);
    }

    public void addField(JavaFieldType field) {
        fieldList.add(field);
    }

    @Override
    public void addInterface(Type superType) {
        interfaces.add(superType);
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put("name", getName());
        if (superClass != null) {
            partMap.put("superClass", superClass);
        }
        blockMap.put("interfaces", modifiers.create());
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("methods", methodList.create());
        blockMap.put("fields", fieldList.create());

    }

    @Override
    public boolean addAnnotation(AnnotationType<JavaMarker> annotation) {
        return annotations.add((JavaAnnotationType) annotation);
    }

    @Override
    public boolean addModifier(ModifierType<JavaMarker> modifier) {
        return modifiers.add((JavaModifierType) modifier);
    }
}
