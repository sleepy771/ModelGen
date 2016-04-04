package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.MethodDefinition;

public interface MethodFactory<T extends MethodType> {

    T create(MethodDefinition definition);
}
