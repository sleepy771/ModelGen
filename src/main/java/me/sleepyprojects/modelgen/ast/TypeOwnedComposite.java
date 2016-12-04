package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

public class TypeOwnedComposite implements TypeOwned {

    private Prototype prototype;

    TypeOwnedComposite(Prototype prototype) {
        this.prototype = prototype;
    }

    @Override
    public Scoped getOwner() {
        return this.prototype;
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
