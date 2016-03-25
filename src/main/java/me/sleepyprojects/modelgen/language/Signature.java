package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Meta;

public interface Signature {
    BlockType getBlockType();

    String getName();

    Meta getMetaType();
}
