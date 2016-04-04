package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.ClassDefinition;

public interface ClassFactory<T extends ClassType> {
    T create(ClassDefinition definition);
}
