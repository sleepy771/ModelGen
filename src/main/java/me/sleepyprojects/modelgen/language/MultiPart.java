package me.sleepyprojects.modelgen.language;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.PartsIterable;

import java.util.Collection;
import java.util.Map;

public class MultiPart<T> extends BuildableType {

    private CanAppend<T> canAppend;
    private Collection<T> elements;
    private String commonName;

    public MultiPart(final @NotNull Collection<T> elements,
                     final @NotNull String commonName,
                     final @NotNull CanAppend<T> canAppend) {
        this.canAppend = canAppend;
        this.commonName = commonName;
        this.elements = elements;
    }

    public boolean add(final @NotNull T part) {
        return canAppend.canAppend(elements, part) && elements.add(part);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put(commonName, new PartsIterable<>(elements));
    }
}
