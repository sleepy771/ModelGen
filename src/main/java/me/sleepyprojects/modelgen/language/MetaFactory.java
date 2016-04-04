package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Meta;

public interface MetaFactory<T> {

    T get(Meta meta);

    boolean is(Meta meta);
}
