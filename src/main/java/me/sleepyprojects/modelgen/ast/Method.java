package me.sleepyprojects.modelgen.ast;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class Method extends Function implements TypeOwned {

    private final Prototype prototype;

    Method(String name, CodeBlock codeBlock, Set<Modifier> modifiers, Set<Argument> arguments, Prototype prototype) {
        super(name, codeBlock, modifiers, arguments, prototype);
        this.prototype = prototype;
    }

    @Override
    public Type getType() {
        return prototype.getType();
    }

    @Override
    public Prototype getPrototype() {
        return prototype;
    }
}
