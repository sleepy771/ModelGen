package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Type;

public interface MethodType<Language> extends HasName,
                                              CreateBlock,
                                              HasArguments<Language>,
                                              HasAnnotations<Language>,
                                              HasModifiers<Language>,
                                              Significant,
                                              HasType<Language> {

    void setBody(FlowCode<Language> code);
}
