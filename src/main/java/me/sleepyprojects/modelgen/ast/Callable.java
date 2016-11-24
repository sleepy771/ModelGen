package me.sleepyprojects.modelgen.ast;

import me.sleepyprojects.modelgen.Argument;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface Callable {
    Set<Argument> getArguments();

    int getArgumentSize();
}
