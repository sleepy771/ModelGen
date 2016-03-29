package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.language.Signature;

public class JavaFieldSignature implements Signature {

    private JavaFieldType fieldType;
    private final Type declaringType;
    private volatile int hashCode;

    JavaFieldSignature(JavaFieldType fieldType, Type declaringType) {
        this.fieldType = fieldType;
        this.declaringType = declaringType;
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
    public Type getMetaType() {
        return fieldType.getType();
    }

    @Override
    public Type getDeclaringType() {
        return declaringType;
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int hash = 17;
            hash = hash * 31 + declaringType.hashCode();
            hash = hash * 31 + getBlockType().hashCode();
            hash = hash * 31 + getName().hashCode();
            hashCode = hash;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && JavaFieldType.class == obj.getClass() && this.declaringType.equals(((JavaFieldSignature)obj).declaringType) && this.getName().equals(((JavaFieldSignature) obj).getName());
    }
}
