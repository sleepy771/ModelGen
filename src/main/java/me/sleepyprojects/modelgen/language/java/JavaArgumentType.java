package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.*;
import me.sleepyprojects.modelgen.language.*;

import java.util.ArrayList;
import java.util.Map;


@TemplateId("argument")
class JavaArgumentType extends BaseNamedType implements ArgumentType<JavaMarker>, HasModifiers<JavaMarker>, HasAnnotations<JavaMarker>, HasType {
    private MultiPart<ModifierType<JavaMarker>> modifiers;
    private BuildMultiple<AnnotationType<JavaMarker>> annotationsStack;
    private Type argumentType;

    JavaArgumentType() {
        annotationsStack = new BuildMultiple<>(new ArrayList<>(), "annotations", "variable-annotations", CanAppend.unique());
        modifiers = new MultiPart<>(new ArrayList<>(), "modifiers", CanAppend.unique());
    }

    @Override
    public boolean addAnnotation(AnnotationType<JavaMarker> annotation) {
        return annotationsStack.add(annotation);
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("annotations", annotationsStack.create());
        partMap.put("type", argumentType);
        partMap.put("name", getName());
    }

    public void setType(final @NotNull Type type) {
        this.argumentType = type;
    }

    public Type getType() {
        return this.argumentType;
    }

    @Override
    public boolean addModifier(ModifierType<JavaMarker> modifier) {
        return modifiers.add(modifier);
    }
}
