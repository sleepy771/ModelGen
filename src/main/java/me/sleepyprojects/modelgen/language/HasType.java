package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Type;

public interface HasType<Language> {
    Type<Language> getType();

    void setType(Type type);
}
