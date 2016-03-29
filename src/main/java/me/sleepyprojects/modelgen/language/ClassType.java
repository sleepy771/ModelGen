package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Type;

public interface ClassType extends HasName, CreateBlock {

    void addSuperType(Type superType);

    void addMethod(MethodType method);

    void addField(FieldType field);
}
