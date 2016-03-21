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

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.ClassType;
import me.sleepyprojects.modelgen.Field;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.Method;
import me.sleepyprojects.modelgen.MethodDefinition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PythonClass implements HasDecorators, HasMeta, ClassType {

    private final MutableMeta classType;

    private Meta classMeta;

    public PythonClass(Meta classType) {
        this.classType = MutableMeta.create(classType);
    }

    @Override
    public String getName() {
        return classType.getName();
    }

    @Override
    public void setName(String name) {
        classType.setName(name);
    }

    @Override
    public boolean addDecorator(final @NotNull MethodDefinition methodDefinition) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addMeta(final @NotNull Meta meta) {
        if (meta.equals(this.classMeta)) {
            return false;
        }
        this.classMeta = meta;
        return true;
    }

    @Override
    public void addMethod(Method method) {

    }

    @Override
    public void addField(Field field) {

    }

    @Override
    public void addConstructor(Method constructor) {

    }

    @Override
    public void addConstant(Field constant) {

    }
}
