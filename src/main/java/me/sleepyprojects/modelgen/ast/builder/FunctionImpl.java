package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

/**
 * This class provides all important information for creating function.
 * By default FunctionImpl has only one body.
 */

@Scope({ScopeType.LANGUAGE_SPECIFIC, ScopeType.DECLARATION})
public class FunctionImpl implements Function {

    private final String name;
    private final CodeBlock codeBlock;
    private final Set<Modifier> modifiers;
    private final List<Argument> arguments;

    FunctionImpl(final String name, CodeBlock codeBlock, Set<Modifier> modifiers, List<Argument> arguments) {
        this.name = name;
        this.codeBlock = codeBlock;
        this.modifiers = Collections.unmodifiableSet(modifiers);
        this.arguments = Collections.unmodifiableList(arguments);
    }

    @Override
    public Set<Modifier> getModifiers() {
        return modifiers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return Collections.singletonList(codeBlock);
    }

    @Override
    public List<Argument> getArguments() {
        return this.arguments;
    }

    @Override
    public Call createCall(List<Assignable> assignment) {
        return null;
    }

    public boolean hasReturn() {
        return false;
    }

    @Override
    public Optional<Assignable> asAssignable() {
        return null;
    }

    public List<Code> getCodeLines() {
        return null;
    }

    @Override
    public Declaration asDeclaration() {
        return this;
    }
}
