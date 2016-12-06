package me.sleepyprojects.modelgen.ast;

import java.util.Optional;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface ForLoop extends Loop {

    Optional<Declaration> getDeclaration();

    Assignment getAssignment();

    Code getIncrementer();

    Condition getCondition();

    boolean isDeclaredInside();
}
