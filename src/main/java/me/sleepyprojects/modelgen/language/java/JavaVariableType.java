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

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Type;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.BaseNamedType;
import me.sleepyprojects.modelgen.language.BuildMultiple;
import me.sleepyprojects.modelgen.language.HasAnnotations;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.HasType;
import me.sleepyprojects.modelgen.language.ModifierType;
import me.sleepyprojects.modelgen.language.MultiPart;
import me.sleepyprojects.modelgen.language.VariableType;

import java.util.Map;

abstract class JavaVariableType extends BaseNamedType
        implements VariableType<JavaMarker>, HasModifiers<JavaMarker>, HasAnnotations<JavaMarker>, HasType {
    private Type variableType;
    private BuildMultiple<JavaAnnotationType> annotations;
    private MultiPart<JavaModifierType> modifiers;

    @Override
    public final boolean addAnnotation(final @NotNull AnnotationType<JavaMarker> annotation) {
        return this.annotations.add((JavaAnnotationType) annotation);
    }

    @Override
    public final boolean addModifier(final @NotNull ModifierType<JavaMarker> modifier) {
        return modifiers.add((JavaModifierType) modifier);
    }

    @Override
    public final Type getType() {
        return variableType;
    }

    @Override
    public final void setType(final @NotNull Type type) {
        this.variableType = type;
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        blockMap.put("annotations", annotations.create());
        blockMap.put("modifiers", modifiers.create());
        partMap.put("name", getName());
        partMap.put("type", getType());
    }
}
