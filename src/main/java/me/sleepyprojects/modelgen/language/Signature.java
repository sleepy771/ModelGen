package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Type;

public interface Signature {
    BlockType getBlockType();

    String getName();

    Type getMetaType();

    Type getDeclaringType();
}
