package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.List;
import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

// TODO make this as composite
public class MethodImpl extends FunctionImpl implements Method {

    private final Prototype prototype;

    MethodImpl(String name, CodeBlock codeBlock, Set<Modifier> modifiers, List<Argument> arguments, Prototype prototype) {
        super(name, codeBlock, modifiers, arguments);
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
