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
    private TreeSet<JavaModifierType> modifiers;

    public JavaModifiersType(Comparator<JavaModifierType> comparator) {
        modifiers = new TreeSet<>(comparator);
    }

    public JavaModifiersType() {
        this(JavaModifierType.Comparator.INSTANCE);
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

    @Override
    public boolean addModifier(Modifier modifier) {
        if (JavaModifierType.class != modifier.getClass()) {
            throw new ClassCastException("Expected JavaModifierType");
        }
        return addModifier((JavaModifierType) modifier);
    }

    private boolean addModifier(JavaModifierType modifierType) {
        return modifiers.add(modifierType);
    }
}
