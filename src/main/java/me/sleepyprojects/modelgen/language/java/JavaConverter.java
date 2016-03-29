/* 
 * Copyright (c) 2016 Filip Hornak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.ArgumentDefinition;
import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.ClassDefinition;
import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.Modifiers;
import me.sleepyprojects.modelgen.language.ClassType;
import me.sleepyprojects.modelgen.language.Converter;
import me.sleepyprojects.modelgen.language.FieldType;
import me.sleepyprojects.modelgen.language.MethodType;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class JavaConverter implements Converter {
    private static final Map<Modifier, JavaModifierType> JAVA_MODIFIERS;

    static {
        HashMap<Modifier, JavaModifierType> modifiers = new HashMap<>();
        final EnumSet<BlockType> visibilitySet = EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.FIELD);

        modifiers.put(Modifiers.PRIVATE, new JavaModifierType(visibilitySet, 1, "private"));
        modifiers.put(Modifiers.PACKAGE_LOCAL, new JavaModifierType(visibilitySet, 1, ""));
        modifiers.put(Modifiers.PROTECTED, new JavaModifierType(visibilitySet, 1, "protected"));
        modifiers.put(Modifiers.PUBLIC, new JavaModifierType(visibilitySet, 1, "private"));

        modifiers.put(Modifiers.STATIC, new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.FIELD, BlockType.BLOCK), 2, "static"));

        modifiers.put(Modifiers.ABSTRACT, new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD), 3, "abstract"));

        modifiers.put(Modifiers.SYNCHRONISED, new JavaModifierType(EnumSet.of(BlockType.BLOCK, BlockType.METHOD), 4, "synchronised")); // TODO remove lang specific

        modifiers.put(Modifiers.TRANSIENT, new JavaModifierType(EnumSet.of(BlockType.FIELD), 5, "transient"));
        modifiers.put(Modifiers.VOLATILE, new JavaModifierType(EnumSet.of(BlockType.FIELD), 5, "volatile")); // TODO remove specific for java

        modifiers.put(Modifiers.FINAL, new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.VARIABLE, BlockType.FIELD), 6, "final"));

        modifiers.put(Modifiers.NATIVE, new JavaModifierType(EnumSet.of(BlockType.METHOD), 7, "native")); // TODO remove

        JAVA_MODIFIERS = Collections.unmodifiableMap(modifiers);
    }


    @Override
    public JavaArgumentType convert(ArgumentDefinition argument) {
        JavaArgumentType argumentType = new JavaArgumentType();
        argumentType.setName(argument.getName());
        argumentType.setType(argument.getType());

        return null;
    }

    @Override
    public MethodType convert(MethodDefinition method) {
        return null;
    }

    @Override
    public FieldType convert() {
        return null;
    }

    @Override
    public ClassType convert(ClassDefinition classDefinition) {
        return null;
    }

    @Override
    public JavaModifierType convert(Modifier modifier) {
        if (!JAVA_MODIFIERS.containsKey(modifier)) {
            throw new RuntimeException("Unknown modifier");
        }
        return JAVA_MODIFIERS.get(modifier);
    }
}
