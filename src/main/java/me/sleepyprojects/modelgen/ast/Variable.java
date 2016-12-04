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

    private final HasScope inScope;

    Variable(final String name, final Set<Modifier> modifiers, HasScope inScope) {
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
    public HasScope getScope() {
        return this.inScope;
    }
}
