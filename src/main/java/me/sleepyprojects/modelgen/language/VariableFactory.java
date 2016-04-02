package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.VariableDefinition;
import me.sleepyprojects.modelgen.language.VariableType;

public interface VariableFactory<T extends VariableType> {
    T create(VariableDefinition definition);
}
