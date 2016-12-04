package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

@BlockMultiplicityRestriction(multiplicity = BlockMultiplicity.ONE)
public abstract class ForEachLoop extends Loop {
    public abstract Variable getVariable();

    public abstract Assignable getIterable();
}
