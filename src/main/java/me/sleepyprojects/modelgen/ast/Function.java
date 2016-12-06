package me.sleepyprojects.modelgen.ast;

import java.util.Optional;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

public interface Function extends Named, HasBlocks, Callable, Modified, CodeBlock, Code, Declarable, Declaration {
    boolean hasReturn();

    Optional<Assignable> asAssignable();
}
