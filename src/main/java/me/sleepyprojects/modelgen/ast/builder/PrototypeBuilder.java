package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

public class PrototypeBuilder {

    private final String name;
    private final List<Code> codes;
    private final Set<Modifier> modifiers;
    private final Path path;
    private final Provider<Module> moduleProvider;

    private PrototypeBuilder(
            @NotNull Provider<Module> moduleProvider,
            @NotNull String name,
            @NotNull Path path
    ) {
        this.moduleProvider = moduleProvider;
        this.name = name;
        this.path = path;
        this.codes = new ArrayList<>();
        this.modifiers = new TreeSet<>();
    }

    public final PrototypeBuilder add(Modifier modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public final PrototypeBuilder add(Method method) {
        this.codes.add(method);
        return this;
    }

    public final PrototypeBuilder add(Prototype prototype) {
        this.codes.add(prototype);
        return this;
    }

    public final PrototypeBuilder add(Field field) {
        this.codes.add(field);
        return this;
    }

    public Prototype build() {
        return null;
    }
}
