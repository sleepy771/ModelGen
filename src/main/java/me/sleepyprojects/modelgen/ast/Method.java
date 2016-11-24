package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public abstract class Method extends Function implements Owned {

    public Method(String name) {
        super(name);
    }
}
