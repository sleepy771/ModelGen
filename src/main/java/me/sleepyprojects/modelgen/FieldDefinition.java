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

import java.util.List;

public class FieldDefinition {

    private List<Meta> metas;
    private Type fieldType;
    private Type declaringType;
    private String name;

    public void addMeta(final @NotNull Meta meta) {
        this.metas.add(meta);
    }

    public void removeMeta(final Meta meta) {
        this.metas.remove(meta);
    }

    public void setType(final @NotNull Type fieldType) {
        this.fieldType = fieldType;
    }

    public void setName(final @NotNull String name) {
        this.name = name;
    }

    public void setDeclaringType(final @NotNull Type declaringType) {
        this.declaringType = declaringType;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public String getName() {
        return name;
    }

    public Type getDeclaringType() {
        return declaringType;
    }

    public Type getType() {
        return fieldType;
    }
}
