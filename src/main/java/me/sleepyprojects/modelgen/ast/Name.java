package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class Name implements Assignable {
    public final String id;
    private final NameProducer producedBy;


    public Name(final String id, final NameProducer producedBy) {
        this.id = id;
        this.producedBy = producedBy;
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * Returns the declaration of this name.
     * @return declaration
     */
    public NameProducer getProducedBy() {
        return this.producedBy;
    }
}
