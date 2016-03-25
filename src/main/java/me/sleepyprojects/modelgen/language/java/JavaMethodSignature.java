package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.language.Signature;

import java.util.List;

public class JavaMethodSignature implements Signature {

    private JavaMethodType methodType;
    private volatile int hashCode;

    JavaMethodSignature(JavaMethodType methodType) {
        this.methodType = methodType;
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
    public Meta getMetaType() {
        return methodType.getMeta();
    }

    private List<Meta> getArgumentTypes() {
        return methodType.getArgumentTypes();
    }

    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            int hash = 17;
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
        return hashCode() == other.hashCode() && getName().equals(other.getName()) && getArgumentTypes().equals(other.getArgumentTypes());
    }
}
