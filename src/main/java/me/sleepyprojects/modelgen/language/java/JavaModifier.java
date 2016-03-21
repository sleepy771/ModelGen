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

import me.sleepyprojects.modelgen.Block;
import me.sleepyprojects.modelgen.Part;
import me.sleepyprojects.modelgen.language.HasModifiers;
import me.sleepyprojects.modelgen.language.modifiers.Modifier;

public class JavaModifier implements Modifier, Part, Comparable<JavaModifier> {

    private final String id;
    private final String name;
    private final String group;
    private final int order;

    private JavaModifier(String id, String name, String group, int order) {

        this.id = id;
        this.name = name;
        this.group = group;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public final int getOrder() {
        return order;
    }

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean apply(Block.Definition builder) {
        if (builder instanceof HasModifiers) {
            final HasModifiers hasModifiers = (HasModifiers) builder;
            return hasModifiers.addModifier(this);
        }
        return false;
    }

    @Override
    public int compareTo(JavaModifier o) {
        return this.order - o.order;
    }
}
