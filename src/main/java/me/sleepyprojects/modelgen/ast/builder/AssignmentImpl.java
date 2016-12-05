package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.Assignable;
import me.sleepyprojects.modelgen.ast.Variable;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class AssignmentImpl {
    private final List<Variable> targets;

    private final Assignable producer;

    public AssignmentImpl(List<Variable> targets, Assignable producer) {
        this.targets = targets;
        this.producer = producer;
    }

    public List<Variable> getTargets() {
        return targets;
    }

    public Assignable getProducer() {
        return producer;
    }

}
