package me.sleepyprojects.modelgen.ast;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface HasBlocks {
    List<CodeBlock> getBlocks();

    int getMaxBlockCount();
}
