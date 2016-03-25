package me.sleepyprojects.modelgen.language;

public interface HasArguments<T extends ArgumentType> extends CreateBlock {

    boolean addArgument(T argument);
}
