package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Type;

public interface ClassType<Language> extends HasName, CreateBlock {

    void addField(FieldType<Language> field);

    void addMethod(MethodType<Language> method);

    void addSuperType(Type superType);
}
