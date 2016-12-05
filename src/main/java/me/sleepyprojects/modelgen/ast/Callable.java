package me.sleepyprojects.modelgen.ast;

import me.sleepyprojects.modelgen.ast.build.ArgumentImpl;

import java.util.List;
import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface Callable {
    Set<ArgumentImpl> getArguments();

    Callable asCallable(List<Assignable> assignment);
}
