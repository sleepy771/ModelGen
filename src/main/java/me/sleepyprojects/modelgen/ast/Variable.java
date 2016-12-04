package me.sleepyprojects.modelgen.ast;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class Variable implements Named, Modified, Assignable, Owned {

    private final String name;

    private final Set<Modifier> modifiers;

    private final Scoped inScope;

    Variable(final String name, final Set<Modifier> modifiers, Scoped inScope) {
        this.name = name;
        this.modifiers = modifiers;
        this.inScope = inScope;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public Scoped getScope() {
        return this.inScope;
    }
}
