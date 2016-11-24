package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public abstract class Field implements Variable, Owned {
    private final String name;
    private final Prototype prototype;

    public Field(final String name, Prototype prototype) {
        this.name = name;
        this.prototype = prototype;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Type getDeclaringType() {
        return this.prototype.getType();
    }

    @Override
    public Prototype getDeclaringPrototype() {
        return this.prototype;
    }
}
