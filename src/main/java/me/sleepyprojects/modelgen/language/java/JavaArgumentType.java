package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.*;
import me.sleepyprojects.modelgen.language.*;

import java.util.ArrayList;
import java.util.Map;

@TemplateId("argument")
public class JavaArgumentType extends BaseNamedType implements ArgumentType, HasModifiers, HasAnnotations, HasType {
    private JavaModifiersType modifiers;
    private BuildMultiple<AnnotationType> annotationsStack;
    private Meta argumentType;

    public JavaArgumentType() {
        annotationsStack = new BuildMultiple<>(new ArrayList<>(), "annotations", "variable-annotations", CanAppend.unique());
        modifiers = new JavaModifiersType();
    }

    @Override
    public boolean addAnnotation(AnnotationType annotation) {
        return annotationsStack.add(annotation);
    }

    @Override
    public boolean addModifier(Modifier modifier) {
        return modifiers.addModifier(modifier);
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Part> partMap) {
        blockMap.put("modifiers", modifiers.create());
        blockMap.put("annotations", annotationsStack.create());
        partMap.put("type", argumentType);
        partMap.put("name", new FlatPart(getName()));
    }

    @Override
    public void setMeta(final @NotNull Meta type) {
        this.argumentType = type;
    }

    @Override
    public Meta getMeta() {
        return this.argumentType;
    }
}
