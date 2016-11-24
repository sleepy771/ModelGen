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

    private final ValueProducer valueProducer;
    private List<CodeBlock> blocks;

    SwitchFlowControll(final ValueProducer producer) {
        this.valueProducer = producer;
    }

    public ValueProducer getValueProducer() {
        return this.valueProducer;
    }

    @Override
    public List<CodeBlock> getBlocks() {
        return null;
    }

    @Override
    public int getMaxBlockCount() {
        return -1;
    }
}
