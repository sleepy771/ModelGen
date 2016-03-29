package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.language.Signature;

import java.util.List;

public class JavaMethodSignature implements Signature {

    private JavaMethodType methodType;
    private final Type declaringType;
    private volatile int hashCode;

    JavaMethodSignature(JavaMethodType methodType, Type declaringType) {
        this.methodType = methodType;
        this.declaringType = declaringType;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.METHOD;
    }

    @Override
    public String getName() {
        return methodType.getName();
    }

    @Override
    public Type getMetaType() {
        return methodType.getType();
    }

    @Override
    public Type getDeclaringType() {
        return declaringType;
    }

    private List<Type> getArgumentTypes() {
        return methodType.getArgumentTypes();
    }

    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            int hash = 17;
            hash = hash * 31 + declaringType.hashCode();
            hash = hash * 31 + getBlockType().hashCode();
            hash = hash * 31 + getName().hashCode();
            hash = hash * 31 + getArgumentTypes().hashCode();
            this.hashCode = hash;
        }
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == JavaMethodSignature.class && equals((JavaMethodSignature) obj);
    }

    private boolean equals(JavaMethodSignature other) {
        return hashCode() == other.hashCode() && this.declaringType.equals(other.declaringType) && getName().equals(other.getName()) && getArgumentTypes().equals(other.getArgumentTypes());
    }
}
