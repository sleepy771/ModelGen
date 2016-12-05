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

public class FunctionBuilder implements CodeBuilder<Function> {

    private List<Argument> arguments;
    private Set<Modifier> modifiers;
    private
    @NotNull
    String name;
    private List<Code> linesOfCode;
    private boolean hasReturn;

    protected FunctionBuilder() {
        this.arguments = new ArrayList<>();
        this.modifiers = new TreeSet<>();
        this.linesOfCode = new ArrayList<>();
    }


    public final FunctionBuilder add(@NotNull Argument argument) {
        this.arguments.add(argument);
        return this;
    }

    public final FunctionBuilder add(@NotNull Modifier modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public final FunctionBuilder add(@NotNull Function function) {
        this.linesOfCode.add(function);
        return this;
    }

    public final FunctionBuilder add(@NotNull Prototype prototype) {
        this.linesOfCode.add(prototype);
        return this;
    }

    public final FunctionBuilder add(@NotNull Code line) {
        this.linesOfCode.add(line);
        return this;
    }

    public final FunctionBuilder add(@NotNull CodeBlock block) {
        this.linesOfCode.add(block);
        return this;
    }

    public final FunctionBuilder add(@NotNull ReturnStatement statement) {
        this.linesOfCode.add(statement);
        this.hasReturn = true;
        return this;
    }

    public final FunctionBuilder setName(final @NotNull String name) {
        this.name = name;
        return this;
    }

    public Function build() {
        return null;
    }
}
