package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class FieldImpl extends VariableImpl implements Field {
    private final Provider<Prototype> prototype;

    FieldImpl(String name, Set<Modifier> modifiers, Provider<Prototype> prototype) {
        super(name, modifiers);
        this.prototype = prototype;
    }

    @Override
    public Type getType() {
        return this.prototype.get().getType();
    }

    @Override
    public Prototype getPrototype() {
        return this.prototype.get();
    }
}
