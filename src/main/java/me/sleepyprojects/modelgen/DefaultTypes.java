package me.sleepyprojects.modelgen;

import me.sleepyprojects.modelgen.language.ClassType;
import me.sleepyprojects.modelgen.language.FieldType;
import me.sleepyprojects.modelgen.language.MethodType;

/**
 * Created by qualityunit on 8.4.2016.
 */
public enum DefaultTypes implements ClassType {
    STRING, INTEGER, FLOAT, LIST, SET, MAP, NONE;

    @Override
    public void addField(FieldType field) {

    }

    @Override
    public void addMethod(MethodType method) {

    }

    @Override
    public void addSuperType(Type superType) {

    }

    @Override
    public Block create() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }
}
