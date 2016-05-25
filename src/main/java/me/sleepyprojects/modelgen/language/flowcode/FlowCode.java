package me.sleepyprojects.modelgen.language.flowcode;

import java.util.List;

public interface FlowCode<Language> {
    List<CodeElement> getElements();
}
