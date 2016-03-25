package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Argument;

public interface AnnotationType extends HasName, CreateBlock {
    void addArgument(Argument argument);
}
