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
package me.sleepyprojects.modelgen.language.python;

import me.sleepyprojects.modelgen.Meta;

public class MutableMeta implements Meta {


    private String name;
    private String pkg;
    private Type type;

    private MutableMeta(String name, String pkg, Type type) {
        this.name = name;
        this.pkg = pkg;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPackage() {
        return pkg;
    }

    @Override
    public Type getType() {
        return type;
    }

    public static MutableMeta create(Meta meta) {
        if (meta instanceof MutableMeta) {
            return (MutableMeta) meta;
        }
        return new MutableMeta(meta.getName(), meta.getPackage(), meta.getType());
    }

    public static MutableMeta create(String name, String pkg, Type type) {
        return new MutableMeta(name, pkg, type);
    }
}
