package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

/**
 * Interface of positional tree nodes, like arguments (or java modifiers).
 */
public interface Positional {

    /**
     * Returns ordering number, according which will be this element ordered.
     * @return ordering number.
     */
    int getOrder();
}
