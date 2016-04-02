package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Meta;

public interface MetaFactory<T> {

    boolean is(Meta meta);

    T get(Meta meta);
}
