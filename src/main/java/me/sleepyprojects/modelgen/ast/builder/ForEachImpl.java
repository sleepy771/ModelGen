package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.Collections;
import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

@Scope(ScopeType.EXECUTION)
public class ForEachImpl implements ForEachLoop {

    private final Variable variable;
    private final Assignable assignable;
    private final CodeBlock block;

    public ForEachImpl(Variable variable, Assignable assignable, CodeBlock block) {
        this.variable = variable;
        this.assignable = assignable;
        this.block = block;
    }

    @Override
    public Variable getVariable() {
        return variable;
    }

    @Override
    public Assignable getIterable() {
        return assignable;
    }

    @Override
    public CodeBlock getCodeBlock() {
        return block;
    }

    @Override
    public List<Code> getCodeLines() {
        return block.getCodeLines();
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return Collections.singletonList(block);
    }
}
