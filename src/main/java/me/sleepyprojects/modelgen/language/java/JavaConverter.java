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
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.Modifiers;
import me.sleepyprojects.modelgen.language.Converter;
import me.sleepyprojects.modelgen.language.FieldFactory;
import me.sleepyprojects.modelgen.language.FieldType;
import me.sleepyprojects.modelgen.language.MetaFactory;
import me.sleepyprojects.modelgen.language.MethodFactory;
import me.sleepyprojects.modelgen.language.MethodType;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class JavaConverter implements Converter {
    private static final Map<Meta, JavaModifierType> JAVA_MODIFIERS;

    static {
        HashMap<Meta, JavaModifierType> modifiers = new HashMap<>();
        final EnumSet<BlockType> visibilitySet = EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.FIELD);

        modifiers.put(Modifiers.PRIVATE, new JavaModifierType(visibilitySet, 1, "private"));
        modifiers.put(JavaModifiers.PACKAGE_LOCAL, new JavaModifierType(visibilitySet, 1, ""));
        modifiers.put(Modifiers.PROTECTED, new JavaModifierType(visibilitySet, 1, "protected"));
        modifiers.put(Modifiers.PUBLIC, new JavaModifierType(visibilitySet, 1, "private"));

        modifiers.put(Modifiers.STATIC,
                      new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD, BlockType.FIELD, BlockType.BLOCK),
                                           2,
                                           "static"));

        modifiers.put(Modifiers.ABSTRACT,
                      new JavaModifierType(EnumSet.of(BlockType.TYPE, BlockType.METHOD), 3, "abstract"));

        modifiers.put(JavaModifiers.SYNCHRONISED,
                      new JavaModifierType(EnumSet.of(BlockType.BLOCK, BlockType.METHOD), 4, "synchronised"));

        modifiers.put(JavaModifiers.TRANSIENT, new JavaModifierType(EnumSet.of(BlockType.FIELD), 5, "transient"));
        modifiers.put(JavaModifiers.VOLATILE, new JavaModifierType(EnumSet.of(BlockType.FIELD), 5, "volatile"));

        modifiers.put(Modifiers.FINAL,
                      new JavaModifierType(EnumSet.of(BlockType.TYPE,
                                                      BlockType.METHOD,
                                                      BlockType.VARIABLE,
                                                      BlockType.FIELD), 6, "final"));

        modifiers.put(JavaModifiers.NATIVE, new JavaModifierType(EnumSet.of(BlockType.METHOD), 7, "native"));

        JAVA_MODIFIERS = Collections.unmodifiableMap(modifiers);
    }

    private MetaFactory<JavaAnnotationType> annotationFactory;
    private MetaFactory<JavaModifierType> modifierFactory;
    private MethodFactory<JavaMethodType> methodFactory;
    private FieldFactory<JavaFieldType> fieldFactory;

    @Override
    public JavaArgumentType convert(ArgumentDefinition argument) {
        JavaArgumentType argumentType = new JavaArgumentType();
        argumentType.setName(argument.getName());
        argumentType.setType(argument.getType());

        return null;
    }

    @Override
    public MethodType convert(MethodDefinition method) {
        JavaMethodType jMethod = new JavaMethodType(annotationsStack, modifiers, arguments);
        jMethod.setName(method.getName());
        jMethod.setType(method.getReturnType());
        method.getMetas().stream().filter(meta -> {
            return modifierFactory.is(meta);
        }).forEach(meta -> jMethod.addModifier(modifierFactory.get(meta)));
        method.getMetas().stream().filter(meta -> {
            return annotationFactory.is(meta);
        }).forEach(meta -> jMethod.addAnnotation(annotationFactory.get(meta)));
        return null;
    }

    @Override
    public FieldType convert() {
        return null;
    }

    @Override
    public JavaClassType convert(ClassDefinition classDefinition) {
        JavaClassType jClass = new JavaClassType();
        jClass.setName(classDefinition.getName());
        // set supetypes
        classDefinition.getSuperTypes().forEach(jClass::addSuperType);
        classDefinition.getMetas().stream().filter(meta -> {
            return annotationFactory.is(meta);
        }).forEach(meta -> jClass.addAnnotation(annotationFactory.get(meta)));
        classDefinition.getMetas().stream().filter(meta -> {
            return modifierFactory.is(meta);
        }).forEach(meta -> jClass.addModifier(modifierFactory.get(meta)));
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
