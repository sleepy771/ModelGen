package me.sleepyprojects.modelgen.ast;

import me.sleepyprojects.modelgen.ast.build.AssignmentImpl;

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

    AssignmentImpl getAssigment();

    Statement getIncrementer();

    Condition getCondition();
}
