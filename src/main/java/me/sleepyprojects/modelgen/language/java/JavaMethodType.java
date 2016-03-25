package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.*;
import me.sleepyprojects.modelgen.language.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaMethodType extends BaseNamedType implements MethodType<JavaArgumentType>, HasAnnotations, HasModifiers, HasType {
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
    protected void assign(Map<String, Block> blockMap, Map<String, Part> partMap) {
        partMap.put("name", new FlatPart(getName()));
        partMap.put("type", type);
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("arguments", arguments.create());
        blockMap.put("annotations", annotationsStack.create());
    }

    @Override
    public boolean addArgument(JavaArgumentType argument) {
        return arguments.add(argument);
    }

    @Override
    public Signature createSignature() {
        return new JavaMethodSignature(this);
    }
}
