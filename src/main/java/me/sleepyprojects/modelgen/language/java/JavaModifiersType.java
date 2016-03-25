package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.*;
import me.sleepyprojects.modelgen.language.BuildableType;
import me.sleepyprojects.modelgen.language.HasModifiers;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@TemplateId("modifiers")
public class JavaModifiersType extends BuildableType implements HasModifiers {

    public static final String MODIFIERS = "modifiers";
    private TreeSet<Modifier> modifiers;

    public JavaModifiersType(Comparator<Modifier> comparator) {
        modifiers = new TreeSet<>(comparator);
    }

    public JavaModifiersType() {
        this(Modifier.Comparator.INSTANCE);
    }

    public void setComparator(Comparator<Modifier> comparator) {
        final Set<Modifier> modifiers = this.modifiers;
        this.modifiers = new TreeSet<>(comparator);
        this.modifiers.addAll(modifiers);
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Part> partMap) {
        partMap.put(MODIFIERS, new PartsIterable<>(modifiers));
    }

    @Override
    public boolean addModifier(Modifier modifier) {
        return modifiers.add(modifier);
    }
}
