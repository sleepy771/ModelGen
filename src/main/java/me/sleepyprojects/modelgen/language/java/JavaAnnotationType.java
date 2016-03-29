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
import me.sleepyprojects.modelgen.language.ArgumentType;
import me.sleepyprojects.modelgen.language.BaseNamedType;
import me.sleepyprojects.modelgen.language.BuildMultiple;
import me.sleepyprojects.modelgen.language.CanAppend;
import me.sleepyprojects.modelgen.language.HasArguments;

import java.util.ArrayList;
import java.util.Map;

public class JavaAnnotationType extends BaseNamedType implements HasArguments {

    private final BuildMultiple<JavaArgumentType> argumentTypes;

    JavaAnnotationType() {
        this.argumentTypes = new BuildMultiple<>(new ArrayList<>(), "arguments", "annotation", CanAppend.unique());
    }

    @Override
    protected void assign(Map<String, Block> blockMap, Map<String, Object> partMap) {
        partMap.put("name", getName());
        blockMap.put("arguments", argumentTypes.create());
    }

    @Override
    public boolean addArgument(ArgumentType argument) {
        return JavaArgumentType.class.equals(argument.getClass()) && argumentTypes.add((JavaArgumentType) argument);
    }

    @Override
    public boolean hasArguments() {
        return !argumentTypes.isEmpty();
    }
}
