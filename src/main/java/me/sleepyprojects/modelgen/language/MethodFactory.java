package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.language.MethodType;

public interface MethodFactory<T extends MethodType> {

    T create(MethodDefinition definition);
}
