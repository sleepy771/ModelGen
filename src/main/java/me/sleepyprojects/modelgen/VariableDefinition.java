package me.sleepyprojects.modelgen;

public class VariableDefinition {
    public static final BlockType TYPE = BlockType.VARIABLE;
    private final String name;
    private final Type type;
    private final Iterable<Meta> metas;


    VariableDefinition(String name, Type type, Iterable<Meta> metas) {
        this.name = name;
        this.type = type;
        this.metas = metas;
    }

    public final String getName() {
        return this.name;
    }

    public final Type getType() {
        return this.type;
    }

    public final Iterable<Meta> getMetas() {
        return metas;
    }

}
