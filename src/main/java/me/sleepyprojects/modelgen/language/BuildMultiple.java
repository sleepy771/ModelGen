package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Block;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class BuildMultiple<T extends CreateBlock> extends BuildableType implements Iterable<T> {

    private final Collection<T> elementCollection;
    private final String commonName;
    private final String templateId;
    private final CanAppend<T> type;
    public BuildMultiple(Collection<T> contexts, String commonName, String templateId, CanAppend<T> type) {
        this.commonName = commonName;
        this.elementCollection = contexts;
        this.templateId = templateId;
        this.type = type;
    }

    public boolean add(T element) {
        return this.type.canAppend(elementCollection, element) && elementCollection.add(element);
    }

    public Collection<T> getCollection() {
        return elementCollection;
    }

    public boolean isEmpty() {
        return elementCollection.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return elementCollection.iterator();
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        int i = 0;
        for (final T element : elementCollection) {
            blockMap.put("L:" + commonName + "#" + i++, element.create());
        }
    }

    @Override
    protected String getId() {
        return templateId;
    }
}
