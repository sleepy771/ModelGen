package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

// TODO this might be actual implementation
public abstract class ForLoop extends Loop {

    public abstract Variable getVariable();

    public abstract Assignment getAssigment();

    public abstract Line getIncrementer();

    public abstract Line getCondition();  // TODO create actual condition
}
