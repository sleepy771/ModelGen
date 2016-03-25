package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Part;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class BuildMultiple<T extends CreateBlock> extends BuildableType implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return elementCollection.iterator();
    }

    public Collection<T> getCollection() {
        return elementCollection;
    }

    public enum AppendType implements CanAppend {
        UNIQUE {
            @Override
            public boolean canAppend(Collection collection, Object element) {
                return !collection.contains(element);
            }
        }, ALL {
            @Override
            public boolean canAppend(Collection collection, Object element) {
                return true;
            }
        };
    }

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

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Part> partMap) {
        int i = 0;
        for (final T element : elementCollection) {
            blockMap.put("L:" + commonName + "#" + i++, element.create());
        }
    }

    public boolean add(T element) {
        return this.type.canAppend(elementCollection, element) && elementCollection.add(element);
    }

    @Override
    protected String getId() {
        return templateId;
    }
}
