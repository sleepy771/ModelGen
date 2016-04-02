package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.FieldDefinition;

public interface FieldFactory<T extends FieldType> {
    T create(FieldDefinition definition);
}
