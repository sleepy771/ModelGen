package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Bind;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.language.*;

import java.util.Map;

class JavaFieldType extends InstanceType implements FieldType<JavaMarker>, HasModifiers<JavaMarker>, HasAnnotations<JavaMarker>, HasType {
    private Type type;

    @Override
    public boolean addAnnotation(AnnotationType<JavaMarker> annotation) {
        return false;
    }

    @Override
    public boolean addModifier(ModifierType<JavaMarker> modifier) {
        return false;
    }

    public void setType(final @NotNull Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {

    }

    @Override
    public Signature createSignature() {
        return new JavaFieldSignature(this, getDeclaringType());
    }
}
