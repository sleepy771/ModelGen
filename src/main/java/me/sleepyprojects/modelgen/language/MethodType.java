package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.language.flowcode.FlowCode;

public interface MethodType<Language> extends HasName,
                                              CreateBlock,
                                              HasArguments<Language>,
                                              Significant,
                                              HasType<Language> {

    void setBody(FlowCode<Language> code);
}
