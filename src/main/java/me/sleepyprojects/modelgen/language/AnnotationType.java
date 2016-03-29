package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.ArgumentDefinition;

public interface AnnotationType extends HasName, CreateBlock, HasArguments {
    void addArgument(ArgumentDefinition argument);
}
