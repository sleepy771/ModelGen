package me.sleepyprojects.modelgen;

import java.util.List;

public class VariableDefinition {
    public static final BlockType TYPE = BlockType.VARIABLE;
    private final String name;
    private final Type type;
    private final List<Meta> metas;


    VariableDefinition(String name, Type type, List<Meta> metas) {
        this.name = name;
        this.type = type;
        this.metas = metas;
    }

    public final List<Meta> getMetas() {
        return metas;
    }

    public final String getName() {
        return this.name;
    }

    public final Type getType() {
        return this.type;
    }

}
