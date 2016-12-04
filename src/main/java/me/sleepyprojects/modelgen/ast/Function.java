package me.sleepyprojects.modelgen.ast;

import java.util.Collections;
import java.util.List;
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
 * By default Function has only one body.
 */
// TODO this might be actual type
@BlockMultiplicityRestriction(multiplicity = BlockMultiplicity.ONE)
@BlockLocation(level = Location.BOTH)
public class Function implements Named, HasBlocks, Assignable, Callable, Modified, Scoped, Owned {

    private final String name;
    private final CodeBlock codeBlock;
    private final Set<Modifier> modifiers;
    private final Set<Argument> arguments;
    private final Scoped scope;

    Function(final String name, CodeBlock codeBlock, Set<Modifier> modifiers, Set<Argument> arguments, Scoped scope) {
        this.name = name;
        this.codeBlock = codeBlock;
        this.modifiers = Collections.unmodifiableSet(modifiers);
        this.arguments = Collections.unmodifiableSet(arguments);
        this.scope = scope;
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
    public Set<Argument> getArguments() {
        return this.arguments;
    }

    @Override
    public Scoped getScope() {
        return scope;
    }

    public boolean hasReturn() {
        return false;
    }
}
