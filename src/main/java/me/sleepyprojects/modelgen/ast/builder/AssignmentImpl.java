package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

@Scope({ScopeType.EXECUTION, ScopeType.LANGUAGE_SPECIFIC})
public class AssignmentImpl implements Assignment {
    private final List<Variable> targets;

    private final Assignable producer;

    public AssignmentImpl(List<Variable> targets, Assignable producer) {
        this.targets = targets;
        this.producer = producer;
    }

    @Override
    public List<Variable> getTargets() {
        return targets;
    }

    @Override
    public Assignable getProducer() {
        return producer;
    }

}
