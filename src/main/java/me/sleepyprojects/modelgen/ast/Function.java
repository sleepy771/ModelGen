package me.sleepyprojects.modelgen.ast;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

// TODO this might be actual type
public abstract class Function implements Named, HasBlocks, ValueProducer, Callable, Modified {

    private final String name;

    public Function(final String name) {
        this.name = name;
    }

    @Override
    public abstract Set<Modifier> getModifiers();

    protected abstract CodeBlock getCodeBlock();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return Collections.singletonList(getCodeBlock());
    }

    @Override
    public int getMaxBlockCount() {
        return 1;
    }
}
