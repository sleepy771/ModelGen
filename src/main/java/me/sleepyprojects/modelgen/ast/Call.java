package me.sleepyprojects.modelgen.ast;

import java.util.List;
import java.util.Optional;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

public interface Call {
    Function getFunction();

    List<Assignable> getArguments();

    boolean isMethod();

    boolean isAssignable();

    Optional<Assignable> asAssignable();

    Optional<Method> getMethod();
}
