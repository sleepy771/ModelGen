package me.sleepyprojects.modelgen.ast;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public abstract class Field extends Variable implements TypeOwned {
    private final Prototype prototype;

    Field(String name, Set<Modifier> modifiers, Prototype prototype) {
        super(name, modifiers, prototype);
        this.prototype = prototype;
    }

    @Override
    public Type getType() {
        return this.prototype.getType();
    }

    @Override
    public Prototype getPrototype() {
        return this.prototype;
    }
}
