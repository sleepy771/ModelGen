package me.sleepyprojects.modelgen.language;

import com.sun.istack.internal.NotNull;

public abstract class BaseNamedType extends BuildableType implements HasName {

    private String name;

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final void setName(final @NotNull String name) {
        this.name = name;
    }
}
