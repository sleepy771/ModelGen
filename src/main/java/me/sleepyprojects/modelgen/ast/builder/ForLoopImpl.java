package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 6.12.16
 */

public class ForLoopImpl implements ForLoop {

    private final CodeBlock body;
    private final Condition terminatingCondition;
    private final Variable variable;
    private final Code incrementOperator;
    private boolean declaredInside;
    private Assignable initial;

    public ForLoopImpl(
            final @NotNull CodeBlock body,
            final @NotNull Condition terminatingCondition,
            final @NotNull Variable variable,
            final @NotNull Code incrementOperator,
            final @NotNull Assignable initialValue,
            final boolean declaredInside
    ) {
        this.body = body;
        this.terminatingCondition = terminatingCondition;
        this.variable = variable;
        this.incrementOperator = incrementOperator;
        this.declaredInside = declaredInside;
        this.initial = initialValue;
    }

    @Override
    public List<Code> getCodeLines() {
        final List<Code> lines = new ArrayList<>();
        getDeclaration().ifPresent(lines::add);
        lines.add(getAssignment());
        lines.add(getCondition());
        lines.add(getIncrementer());
        lines.addAll(this.body.getCodeLines());
        return Collections.unmodifiableList(lines);
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return Collections.singletonList(body);
    }

    @Override
    public Optional<Declaration> getDeclaration() {
        if (this.isDeclaredInside()) {
            return Optional.of(variable.asDeclaration());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Assignment getAssignment() {
        return new AssignmentImpl(
                Collections.singletonList(this.variable),
                this.initial);
    }

    @Override
    public Code getIncrementer() {
        return this.incrementOperator;
    }

    @Override
    public Condition getCondition() {
        return this.terminatingCondition;
    }

    @Override
    public boolean isDeclaredInside() {
        return this.declaredInside;
    }
}
