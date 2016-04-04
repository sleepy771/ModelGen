package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.PartsIterable;
import me.sleepyprojects.modelgen.TemplateId;
import me.sleepyprojects.modelgen.language.BuildableType;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.ModifierType;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@TemplateId("modifiers")
public class JavaModifiersType extends BuildableType implements HasModifiers<JavaMarker> {

    public static final String MODIFIERS = "modifiers";
    private TreeSet<JavaModifierType> modifiers;

    public JavaModifiersType(Comparator<JavaModifierType> comparator) {
        modifiers = new TreeSet<>(comparator);
    }

    public JavaModifiersType() {
        this(JavaModifierType.Comparator.INSTANCE);
    }

    @Override
    public boolean addModifier(ModifierType<JavaMarker> modifier) {
        return addMod((JavaModifierType) modifier);
    }

    public void setComparator(Comparator<JavaModifierType> comparator) {
        final Set<JavaModifierType> modifiers = this.modifiers;
        this.modifiers = new TreeSet<>(comparator);
        this.modifiers.addAll(modifiers);
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put(MODIFIERS, new PartsIterable<>(modifiers));
    }

    private boolean addMod(JavaModifierType modifierType) {
        return modifiers.add(modifierType);
    }
}
