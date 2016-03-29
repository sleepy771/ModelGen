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
import me.sleepyprojects.modelgen.BlockType;
import me.sleepyprojects.modelgen.language.ModifierType;

import java.util.Comparator;
import java.util.EnumSet;

public class JavaModifierType implements ModifierType {

    private final EnumSet<BlockType> supportedBlocks;
    private final int order;
    private final String modifier;

    public JavaModifierType(final @NotNull EnumSet<BlockType> supportedBlocks, final int order, final String modifier) {
        this.supportedBlocks = supportedBlocks;
        this.order = order;
        this.modifier = modifier;
    }

    public final EnumSet<BlockType> getSupportedTypes() {
        return this.supportedBlocks;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String toString() {
        return this.modifier;
    }

    public final String getName() {
        return this.modifier;
    }

    static class Comparator implements java.util.Comparator<JavaModifierType> {

        public static final Comparator INSTANCE = new Comparator();

        private Comparator() {
        }

        @Override
        public int compare(JavaModifierType o1, JavaModifierType o2) {
            return o1.getOrder() -  o2.getOrder();
        }
    }
}