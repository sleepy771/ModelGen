package me.sleepyprojects.modelgen.language;

public interface HasArguments extends CreateBlock {
    boolean addArgument(ArgumentType argument);

    boolean hasArguments();
}
