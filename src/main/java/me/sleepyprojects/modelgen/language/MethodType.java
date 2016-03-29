package me.sleepyprojects.modelgen.language;

public interface MethodType extends HasName, CreateBlock, HasArguments, Significant {

    void setBody(FlowCode code);
}
