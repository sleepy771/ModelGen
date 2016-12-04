package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

@BlockMultiplicityRestriction(multiplicity = BlockMultiplicity.ONE)
public class ForEachLoop extends Loop {
    private final Assignable iterable;
    private final Variable variable;
    private final Scoped owner;
    private final CodeBlock codeBlock;

    public ForEachLoop(Scoped owner, Assignable iterable, Variable variable, CodeBlock block) {
        this.iterable = iterable;
        this.variable = variable;
        this.owner = owner;
        this.codeBlock = block;
    }


    public Variable getVariable() {
        return variable;
    }

    public Assignable getIterable() {
        return iterable;
    }

    @Override
    CodeBlock getCodeBlock() {
        return codeBlock;
    }

    @Override
    public Scoped getOwner() {
        return owner;
    }
}
