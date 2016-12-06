package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.*;

import java.util.List;
import java.util.Optional;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 6.12.16
 */

public class CallImpl implements Call {

    private final Function function;
    private final List<Assignable> arguments;

    CallImpl(final @NotNull Function function, List<Assignable> args) {
        this.function = function;
        this.arguments = args;
    }

    @Override
    public Function getFunction() {
        return this.function;
    }

    @Override
    public List<Assignable> getArguments() {
        return this.arguments;
    }

    @Override
    public boolean isMethod() {
        return Method.class.isInstance(this.function);
    }

    @Override
    public boolean isAssignable() {
        return this.function.hasReturn();
    }

    @Override
    public Optional<AssignableCallImpl> asAssignable() {
        return AssignableCallImpl.createFromCall(this);
    }

    @Override
    public Optional<Method> getMethod() {
        if (this.isMethod()) {
            return Optional.of((Method) this.function);
        } else {
            return Optional.empty();
        }
    }
}
