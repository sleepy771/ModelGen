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

public class ReturningFunctionCall extends FunctionCall implements Assignable {
    public ReturningFunctionCall(Function function, Set<Argument> argumentSet) {
        super(function, argumentSet);
    }

    public static Optional<ReturningFunctionCall> fromCall(FunctionCall call) {
        if (!call.isReturning()) {
            return Optional.empty();
        }
        return Optional.of(new ReturningFunctionCall(call.getFunction(), call.getArguments()));
    }
}
