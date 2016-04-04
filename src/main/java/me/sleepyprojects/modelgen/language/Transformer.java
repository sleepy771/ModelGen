package me.sleepyprojects.modelgen.language;

public interface Transformer<T, E> {

    Class<E> getType();

    T transform(E object);
}
