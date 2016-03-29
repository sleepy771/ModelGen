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
package me.sleepyprojects.modelgen;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.language.AnnotationType;

import java.util.ArrayList;
import java.util.List;

@TemplateId("argument")
public class ArgumentDefinition extends BaseDefinition {
    public static final BlockType TYPE = BlockType.VARIABLE;
    private final String name;
    private final Type type;
    private final Iterable<Modifier> modifiers;


    private ArgumentDefinition(String name, Type type, Iterable<Modifier> modifiers) {
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
    }

    public final String getName() {
        return this.name;
    }

    public final Type getType() {
        return this.type;
    }

    public final Iterable<Modifier> getModifiers() {
        return modifiers;
    }

    public static class Builder implements Block.Builder<ArgumentDefinition> {

        private final String name;
        private Type argType;
        private final List<Modifier> modifiers;
        private final List<AnnotationType> annotations;

        public Builder(String name) {
            this.name = name;
            this.modifiers = new ArrayList<>();
            this.annotations = new ArrayList<>();
        }

        public void setType(final @NotNull Type argType) {
            this.argType = argType;
        }

        public boolean hasType() {
            return this.argType != null;
        }

        public boolean hasModifiers() {
            return !modifiers.isEmpty();
        }

        public boolean hasAnnotations() {
            return !annotations.isEmpty();
        }

        public void addModifier(final @NotNull Modifier modifier) {
            this.modifiers.add(modifier);
        }

        public void addAnnotation(final @NotNull AnnotationType annotation) {
            this.annotations.add(annotation);
        }

        @Override
        public ArgumentDefinition build() {
            return new ArgumentDefinition(name, argType, modifiers);
        }
    }
}
