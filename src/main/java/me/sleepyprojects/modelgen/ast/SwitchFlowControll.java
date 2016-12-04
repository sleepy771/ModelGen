package me.sleepyprojects.modelgen.ast;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public class SwitchFlowControll implements HasBlocks {

    private final Assignable valueProducer;
    private List<CodeBlock> blocks;

    SwitchFlowControll(final Assignable producer) {
        this.valueProducer = producer;
    }

    public Assignable getValueProducer() {
        return this.valueProducer;
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return null;
    }
}
