package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.Part;
import me.sleepyprojects.modelgen.language.*;

import java.util.Map;

public class JavaFieldType extends BaseNamedType implements FieldType, HasModifiers, HasAnnotations, HasType {
    private Meta meta;

    @Override
    public boolean addAnnotation(AnnotationType annotation) {
        return false;
    }

    @Override
    public boolean addModifier(Modifier modifier) {
        return false;
    }

    @Override
    public void setMeta(final @NotNull Meta meta) {
        this.meta = meta;
    }

    @Override
    public Meta getMeta() {
        return meta;
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Part> partMap) {

    }

    @Override
    public Signature createSignature() {
        return new JavaFieldSignature(this);
    }
}
