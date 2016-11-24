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
@BlockMultiplicityRestriction(multiplicity = BlockMultiplicityRestriction.Size.ONE)
@Level(level = Level.Part.BOTH)
public class Function implements Named, HasBlocks, ValueProducer, Callable, Modified {

    private final String name;
    private final CodeBlock codeBlock;
    private final Set<Modifier> modifiers;
    private final Set<Argument> arguments;

    Function(final String name, CodeBlock codeBlock, Set<Modifier> modifiers, Set<Argument> arguments) {
        this.name = name;
        this.codeBlock = codeBlock;
        this.modifiers = Collections.unmodifiableSet(modifiers);
        this.arguments = Collections.unmodifiableSet(arguments);
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
    public int getArgumentSize() {
        return arguments.size();
    }
}
