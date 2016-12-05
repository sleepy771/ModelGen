package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.CodeBlock;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

public interface Provider<T extends CodeBlock> {
    T get();
}
