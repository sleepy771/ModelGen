package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 6.12.16
 */

@Scope(ScopeType.EXECUTION)
public class IfImpl implements IfStatement {

    private final List<Condition> conditions;
    private final List<CodeBlock> blocks;

    private List<Code> codeList;

    IfImpl(List<Condition> conditions, List<CodeBlock> blocks) {
        this.conditions = conditions;
        this.blocks = blocks;
    }

    @Override
    public List<Code> getCodeLines() {
        if (codeList == null) {
            final Iterator<CodeBlock> blockIter = this.blocks.iterator();
            final Iterator<Condition> conditionIter = this.conditions.iterator();
            final List<Code> lines = new ArrayList<>();
            while (conditionIter.hasNext() && blockIter.hasNext()) {
                lines.add(conditionIter.next());
                for (final Code codeLine: blockIter.next().getCodeLines()) {
                    lines.add(codeLine);
                }
            }
            this.codeList = Collections.unmodifiableList(lines);
        }
        return this.codeList;
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return this.blocks;
    }

    @Override
    public List<Condition> getConditions() {
        return this.conditions;
    }
}
