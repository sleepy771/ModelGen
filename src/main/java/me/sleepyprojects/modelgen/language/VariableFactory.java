package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.VariableDefinition;

public interface VariableFactory<T extends VariableType> {
    T create(VariableDefinition definition);
}
