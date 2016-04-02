package me.sleepyprojects.modelgen.language;

public interface MethodType<Language> extends HasName, CreateBlock, HasArguments<Language>, HasAnnotations<Language>, HasModifiers<Language>, Significant {

    void setBody(FlowCode<Language> code);
}
