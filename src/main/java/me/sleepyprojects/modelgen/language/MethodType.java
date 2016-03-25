package me.sleepyprojects.modelgen.language;

public interface MethodType<T extends ArgumentType> extends HasName, CreateBlock, HasArguments<T>, Significant {

    void setBody(FlowCode code);
}
