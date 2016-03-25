package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Meta;

public interface ClassType extends HasName, CreateBlock {

    void addSuperType(Meta superType);

    void addMethod(MethodType method);

    void addField(FieldType field);
}
