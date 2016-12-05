package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface ForEachLoop extends Loop {

    Variable getVariable();

    Assignable getIterable();

    CodeBlock getCodeBlock();
}
