package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.Assignable;
import me.sleepyprojects.modelgen.ast.Call;
import me.sleepyprojects.modelgen.ast.Function;

import java.util.List;
import java.util.Optional;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 6.12.16
 */

public class AssignableCallImpl extends CallImpl implements Assignable, Call {
    AssignableCallImpl(@NotNull Function function, List<Assignable> args) {
        super(function, args);
    }

    public static Optional<AssignableCallImpl> createFromCall(Call call) {
        if (!call.getFunction().hasReturn()) {
            return Optional.empty();
        } else {
            return Optional.of(new AssignableCallImpl(call.getFunction(), call.getArguments()));
        }
    }
}
