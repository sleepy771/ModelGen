package me.sleepyprojects.modelgen.ast;

import java.util.Optional;
import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

public class FunctionCall implements Statement, MightAssign {

    private Function function;

    public Set<Argument> getArguments() {
        return arguments;
    }

    private Set<Argument> arguments;


    public FunctionCall(Function function, Set<Argument> argumentSet) {
        this.function = function;
        this.arguments = argumentSet;
    }

    public boolean isReturning() {
        return function.hasReturn();
    }

    public Function getFunction() {
        return this.function;
    }

    public Optional<ReturningFunctionCall> asProducer() {
        return ReturningFunctionCall.fromCall(this);
    }
}
