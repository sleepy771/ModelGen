package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Type;

public interface ClassType<Language> extends HasName, CreateBlock {

    void addSuperType(Type superType);

    void addMethod(MethodType<Language> method);

    void addField(FieldType<Language> field);
}
