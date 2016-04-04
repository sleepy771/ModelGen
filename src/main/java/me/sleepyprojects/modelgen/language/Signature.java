package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Type;

public interface Signature {
    BlockType getBlockType();

    Type getDeclaringType();

    Type getMetaType();

    String getName();
}
