package me.sleepyprojects.modelgen.language;

public interface HasArguments<Language> extends CreateBlock {
    boolean addArgument(ArgumentType<Language> argument);

    boolean hasArguments();
}
