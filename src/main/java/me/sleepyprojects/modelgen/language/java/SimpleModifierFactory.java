package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.Modifiers;
import me.sleepyprojects.modelgen.language.MetaFactory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class SimpleModifierFactory implements MetaFactory<JavaModifierType> {

    private final Map<Meta, JavaModifierType> javaModifiers;
    private final EnumSet<BlockType> visibilitySet = EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.FIELD);

    SimpleModifierFactory() {
        javaModifiers = new HashMap<>();
        initModifiers();
    }

    protected final boolean put(Meta meta, JavaModifierType modifier) {
        if (javaModifiers.containsKey(meta)) {
            return javaModifiers.get(meta).equals(modifier);
        }
        javaModifiers.put(meta, modifier);
        return true;
    }

    protected void initModifiers() {
        put(Modifiers.PRIVATE, new JavaModifierType(visibilitySet, 1, "private"));
        put(JavaModifiers.PACKAGE_LOCAL, new JavaModifierType(visibilitySet, 1, ""));
        put(Modifiers.PROTECTED, new JavaModifierType(visibilitySet, 1, "protected"));
        put(Modifiers.PUBLIC, new JavaModifierType(visibilitySet, 1, "private"));

        put(Modifiers.STATIC, new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.FIELD, BlockType.BLOCK), 2, "static"));

        put(Modifiers.ABSTRACT, new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD), 3, "abstract"));

        put(JavaModifiers.SYNCHRONISED, new JavaModifierType(EnumSet.of(BlockType.BLOCK, BlockType.METHOD), 4, "synchronised"));

        put(JavaModifiers.TRANSIENT, new JavaModifierType(EnumSet.of(BlockType.FIELD), 5, "transient"));
        put(JavaModifiers.VOLATILE, new JavaModifierType(EnumSet.of(BlockType.FIELD), 5, "volatile"));

        put(Modifiers.FINAL, new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.VARIABLE, BlockType.FIELD), 6, "final"));

        put(JavaModifiers.NATIVE, new JavaModifierType(EnumSet.of(BlockType.METHOD), 7, "native"));
    }

    @Override
    public boolean is(Meta meta) {
        return javaModifiers.containsKey(meta);
    }

    @Override
    public JavaModifierType get(Meta meta) {
        JavaModifierType modifier = javaModifiers.get(meta);
        if (modifier == null) {
            throw new RuntimeException("Undefined modifier");
        }
        return modifier;
    }
}
