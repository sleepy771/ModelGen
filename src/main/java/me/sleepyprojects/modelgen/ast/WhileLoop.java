package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

@BlockMultiplicityRestriction(multiplicity = BlockMultiplticity.ONE)
@BlockLocation(level = Location.EXECUTION)
public abstract class WhileLoop extends Loop {

    public abstract Condition getCondition();
}
