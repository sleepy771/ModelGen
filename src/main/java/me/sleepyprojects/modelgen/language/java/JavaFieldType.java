package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.FieldType;
import me.sleepyprojects.modelgen.language.HasAnnotations;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.HasType;
import me.sleepyprojects.modelgen.language.InstanceType;
import me.sleepyprojects.modelgen.language.ModifierType;
import me.sleepyprojects.modelgen.language.Signature;

import java.util.Map;

class JavaFieldType extends InstanceType<JavaMarker>
        implements FieldType<JavaMarker>, HasModifiers<JavaMarker>, HasAnnotations<JavaMarker>, HasType {
    private Type type;

    @Override
    public boolean addAnnotation(AnnotationType<JavaMarker> annotation) {
        return false;
    }

    @Override
    public boolean addModifier(ModifierType<JavaMarker> modifier) {
        return false;
    }

    @Override
    public Signature createSignature() {
        return new JavaFieldSignature(this, getDeclaringType());
    }

    public Type getType() {
        return type;
    }

    public void setType(final @NotNull Type type) {
        this.type = type;
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {

    }
}
