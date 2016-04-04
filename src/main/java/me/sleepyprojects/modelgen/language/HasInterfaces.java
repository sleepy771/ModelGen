package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Type;

public interface HasInterfaces<Language> {
    void addInterface(Type<Language> superType);
}
