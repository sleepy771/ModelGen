package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class Name {
    public final String id;

    public Name(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
