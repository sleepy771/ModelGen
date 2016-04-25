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
package me.sleepyprojects.modelgen.data.types;

import me.sleepyprojects.modelgen.MethodProxy;
import me.sleepyprojects.modelgen.Type;

import java.util.List;

public class TypeMethodModel implements MethodProxy<DefaultType> {
    private String name;
    private List<String> args;
    private Type<DefaultType> returnType;

    @Override
    public Type<DefaultType> getDeclaringType() {
        return null;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getArguments() {
        return args;
    }

    @Override
    public Type<DefaultType> getReturnType() {
        return returnType;
    }

    public void setArguments(List<String> args) {
        this.args = args;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReturnType(Type<DefaultType> returnType) {
        this.returnType = returnType;
    }
}
