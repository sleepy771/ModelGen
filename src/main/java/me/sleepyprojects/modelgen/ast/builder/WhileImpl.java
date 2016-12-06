package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 6.12.16
 */

@Scope(ScopeType.EXECUTION)
public class WhileImpl implements WhileLoop {

    private final Condition condition;
    private final CodeBlock block;

    public WhileImpl(
            final @NotNull Condition condition,
            final @NotNull CodeBlock block
    ) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public Condition getCondition() {
        return this.condition;
    }

    @Override
    public List<Code> getCodeLines() {
        final List<Code> lines = new ArrayList<>();
        lines.add(this.condition);
        lines.addAll(this.block.getCodeLines());
        return Collections.unmodifiableList(lines);
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return Collections.singletonList(block);
    }
}
