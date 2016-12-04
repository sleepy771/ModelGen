package me.sleepyprojects.modelgen.ast;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class Argument extends Variable implements Positional {

    private final int order;

    Argument(String name, Set<Modifier> modifiers, int order, Function function) {
        super(name, modifiers, function);
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
