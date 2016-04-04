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

import java.util.List;

public class MethodDefinition {
    private static final BlockType TYPE = BlockType.METHOD;
    private final String name;
    private final List<Meta> metas;
    private final List<ArgumentDefinition> args;
    private final Type returnType;
    private Type declaringType;

    private MethodDefinition(String name, List<Meta> metas, List<ArgumentDefinition> args, Type returnType) {

        this.name = name;
        this.metas = metas;
        this.args = args;
        this.returnType = returnType;
    }

    public List<ArgumentDefinition> getArgs() {
        return args;
    }

    public Type getDeclaringType() {
        return declaringType;
    }

    public void setDeclaringType(Type declaringType) {
        this.declaringType = declaringType;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public String getName() {
        return name;
    }

    public Type getReturnType() {
        return returnType;
    }
}
