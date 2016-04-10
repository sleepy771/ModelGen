package me.sleepyprojects.modelgen;

import me.sleepyprojects.modelgen.language.ClassType;

import java.util.EnumSet;

public class DefaultArgValue implements Part {

    private ClassType<?> type;
    private boolean raw;
    private String value;
    private Language language;

    @Override
    public String toString() {
        if (type == DefaultTypes.STRING && raw) {
            return language.asStringLiteral(value);
        }
        if (EnumSet.of(DefaultTypes.INTEGER, DefaultTypes.FLOAT).contains(type)) {
            return value;
        }
        return language.asVariableCall(value);
    }
}
