package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.ClassDefinition;
import me.sleepyprojects.modelgen.language.ClassType;

public interface ClassFactory<T extends ClassType> {
    T create(ClassDefinition definition);
}
