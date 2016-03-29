package me.sleepyprojects.modelgen.language.java;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Part;
import me.sleepyprojects.modelgen.PartsIterable;
import me.sleepyprojects.modelgen.language.BuildableType;
import me.sleepyprojects.modelgen.language.CanAppend;

import java.util.Collection;
import java.util.Map;

public class MultiPart<T extends Part> extends BuildableType {

    private CanAppend<T> canAppend;
    private Collection<T> elements;
    private String commonName;

    public MultiPart(final @NotNull Collection<T> elements, final @NotNull String commonName, final @NotNull CanAppend<T> canAppend) {
        this.canAppend = canAppend;
        this.commonName = commonName;
        this.elements = elements;
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put(commonName, new PartsIterable<>(elements));
    }

    public boolean add(final @NotNull T part) {
        return canAppend.canAppend(elements, part) && elements.add(part);
    }
}
