package me.sleepyprojects.modelgen.ast.builder;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ast.*;

import java.util.*;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

public class MethodBuilder implements CodeBuilder<Method> {

    private List<Argument> arguments;
    private Set<Modifier> modifiers;
    private @NotNull String name;
    private List<Code> linesOfCode;
    private boolean hasReturn;
    private Provider<Prototype> protoBuilder;

    protected MethodBuilder(
            final @NotNull Provider<Prototype> prototypeProvider
    ) {
        this.arguments = new ArrayList<>();
        this.modifiers = new TreeSet<>();
        this.linesOfCode = new ArrayList<>();
        this.protoBuilder = prototypeProvider;
    }

    public final MethodBuilder add(@NotNull Argument argument) {
        this.arguments.add(argument);
        return this;
    }

    public final MethodBuilder add(@NotNull Modifier modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public final MethodBuilder add(@NotNull Function function) {
        this.linesOfCode.add(function);
        return this;
    }

    public final MethodBuilder add(@NotNull Prototype prototype) {
        this.linesOfCode.add(prototype);
        return this;
    }

    public final MethodBuilder add(@NotNull Code line) {
        this.linesOfCode.add(line);
        return this;
    }

    public final MethodBuilder add(@NotNull CodeBlock block) {
        this.linesOfCode.add(block);
        return this;
    }

    public final MethodBuilder add(@NotNull ReturnStatement statement) {
        this.linesOfCode.add(statement);
        this.hasReturn = true;
        return this;
    }

    public final MethodBuilder setName(final @NotNull String name) {
        this.name = name;
        return this;
    }

    @Override
    public Method build() {
        return null;
    }
}
