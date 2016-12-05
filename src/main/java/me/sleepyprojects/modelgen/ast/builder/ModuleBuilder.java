package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

public class ModuleBuilder {

    private final Path path;
    private final String name;
    private final List<Code> possessions;

    ModuleBuilder(final String name, final Path path) {
        this.name = name;
        this.path = path;
        this.possessions = new ArrayList<>();
    }

    public final ModuleBuilder add(@NotNull Prototype prototype) {
        this.possessions.add(prototype);
        return this;
    }

    public final ModuleBuilder add(@NotNull Function function) {
        this.possessions.add(function);
        return this;
    }

    public final ModuleBuilder add(@NotNull Variable variable) {
        this.possessions.add(variable);
        return this;
    }

    public final Module build() {
        return null;
    }
}
