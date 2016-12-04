package me.sleepyprojects.modelgen.ast;

import java.util.Collections;
import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public abstract class Loop implements HasBlocks, Scoped {

    protected abstract CodeBlock getCodeBlock();

    @Override
    public List<CodeBlock> getBlocks() {
        return Collections.singletonList(getCodeBlock());
    }
}
