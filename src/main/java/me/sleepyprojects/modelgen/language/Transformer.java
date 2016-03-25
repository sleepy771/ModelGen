package me.sleepyprojects.modelgen.language;

public interface Transformer<T, E> {

    T transform(E object);

    Class<E> getType();
}
