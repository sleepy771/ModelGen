package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.ArgumentType;
import me.sleepyprojects.modelgen.language.BuildMultiple;
import me.sleepyprojects.modelgen.language.CanAppend;
import me.sleepyprojects.modelgen.language.flowcode.FlowCode;
import me.sleepyprojects.modelgen.language.HasAnnotations;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.InstanceType;
import me.sleepyprojects.modelgen.language.MethodType;
import me.sleepyprojects.modelgen.language.ModifierType;
import me.sleepyprojects.modelgen.language.MultiPart;
import me.sleepyprojects.modelgen.language.Signature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

class JavaMethodType extends InstanceType<JavaMarker> implements MethodType<JavaMarker>, HasAnnotations<JavaMarker>, HasModifiers<JavaMarker> {
    private BuildMultiple<AnnotationType<JavaMarker>> annotationsStack;
    private MultiPart<JavaModifierType> modifiers;
    private BuildMultiple<JavaArgumentType> arguments;
    private Type<JavaMarker> type;

    JavaMethodType() {
        this.annotationsStack =
                new BuildMultiple<>(new ArrayList<>(), "annotations", "method-annotations", CanAppend.unique());
        this.modifiers =
                new MultiPart<>(new TreeSet<>(JavaModifierType.Comparator.INSTANCE), "modifiers", CanAppend.unique());
        this.arguments = new BuildMultiple<>(new ArrayList<>(), "arguments", "method-arguments", CanAppend.uniqueName());
    }

    @Override
    public boolean addAnnotation(AnnotationType<JavaMarker> annotation) {
        return annotationsStack.add(annotation);
    }

    @Override
    public boolean addArgument(ArgumentType<JavaMarker> argument) {
        return arguments.add((JavaArgumentType) argument);
    }

    @Override
    public boolean addModifier(ModifierType<JavaMarker> modifier) {
        return modifiers.add((JavaModifierType) modifier);
    }

    @Override
    public Signature createSignature() {
        return new JavaMethodSignature(this, getDeclaringType());
    }

    @Override
    public Type<JavaMarker> getType() {
        return this.type;
    }

    @Override
    public void setType(final @NotNull Type<JavaMarker> type) {
        this.type = type;
    }

    @Override
    public boolean hasArguments() {
        return !arguments.isEmpty();
    }

    @Override
    public void setBody(FlowCode<JavaMarker> code) {

    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put("name", getName());
        partMap.put("type", type);
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("arguments", arguments.create());
        blockMap.put("annotations", annotationsStack.create());
    }

    List<Type> getArgumentTypes() {
        return arguments.getCollection().stream().map(JavaArgumentType::getType).collect(Collectors.toList());
    }
}
