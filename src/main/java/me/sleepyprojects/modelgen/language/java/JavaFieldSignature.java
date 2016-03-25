package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.language.Signature;

public class JavaFieldSignature implements Signature {

    private JavaFieldType fieldType;
    private volatile int hashCode;

    JavaFieldSignature(JavaFieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.FIELD;
    }

    @Override
    public String getName() {
        return fieldType.getName();
    }

    @Override
    public Meta getMetaType() {
        return fieldType.getMeta();
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int hash = 17 * 31;
            hash += getBlockType().hashCode();
            hash = hash * 31 + getName().hashCode();
            hashCode = hash;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && JavaFieldType.class == obj.getClass() && this.getName().equals(((JavaFieldSignature) obj).getName());
    }
}
