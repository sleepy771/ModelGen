package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.BaseBlock;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Part;
import me.sleepyprojects.modelgen.TemplateId;
import me.sleepyprojects.modelgen.data.Utils;

import java.util.HashMap;
import java.util.Map;

public abstract class BuildableType implements CreateBlock {
    @Override
    public final Block create() {
        Map<String, Block> blockMap = new HashMap<>();
        Map<String, Part> partMap = new HashMap<>();
        final String id = getId();
        assign(blockMap, partMap);
        return new BaseBlock(blockMap, partMap, id);
    }

    protected abstract void assign(Map<String, Block> blockMap, Map<String, Part> partMap);

    protected String getId() {
        TemplateId id = getClass().getAnnotation(TemplateId.class);
        if (id == null || Utils.isEmpty(id.value())) {
            throw new RuntimeException("No value set");
        }
        return id.value();
    }
}
