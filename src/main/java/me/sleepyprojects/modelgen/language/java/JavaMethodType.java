package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.Bind;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.ArgumentType;
import me.sleepyprojects.modelgen.language.BuildMultiple;
import me.sleepyprojects.modelgen.language.FlowCode;
import me.sleepyprojects.modelgen.language.HasAnnotations;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.HasType;
import me.sleepyprojects.modelgen.language.InstanceType;
import me.sleepyprojects.modelgen.language.MethodType;
import me.sleepyprojects.modelgen.language.Signature;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Bind(MethodDefinition.class)
public class JavaMethodType extends InstanceType implements MethodType, HasAnnotations, HasModifiers, HasType {
    private BuildMultiple<AnnotationType> annotationsStack;
    private HasModifiers modifiers;
    private BuildMultiple<JavaArgumentType> arguments;
    private Meta type;

    @Override
    public boolean addAnnotation(AnnotationType annotation) {
        return annotationsStack.add(annotation);
    }

    @Override
    public boolean addModifier(Modifier modifier) {
        return modifiers.addModifier(modifier);
    }

    public List<Meta> getArgumentTypes() {
        return arguments.getCollection().stream().map(JavaArgumentType::getMeta).collect(Collectors.toList());
    }

    @Override
    public void setBody(FlowCode code) {

    }

    @Override
    public void setMeta(Meta type) {
        this.type = type;
    }

    @Override
    public Meta getMeta() {
        return this.type;
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put("name", getName());
        partMap.put("type", type);
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("arguments", arguments.create());
        blockMap.put("annotations", annotationsStack.create());
    }

    @Override
    public boolean addArgument(ArgumentType argument) {
        return false;
    }

    @Override
    public boolean hasArguments() {
        return false;
    }

    @Override
    public Signature createSignature() {
        return new JavaMethodSignature(this, getDeclaringType());
    }
}
