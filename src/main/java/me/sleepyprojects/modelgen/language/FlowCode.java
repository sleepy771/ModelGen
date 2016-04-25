package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.LineCode;

public interface FlowCode<Language> {
    void addLine(LineCode<Language> line);
}
