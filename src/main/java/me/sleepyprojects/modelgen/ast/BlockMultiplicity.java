package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */
public enum BlockMultiplicity {
    ONE(1),
    TWO(2),
    MULTIPLE(-1),
    NONE(0);

    private final int size;

    BlockMultiplicity(int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }
}
