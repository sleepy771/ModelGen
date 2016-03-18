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
package me.sleepyprojects.modelgen.data;

import java.util.HashMap;

public abstract class ClassBinder {

    private HashMap<String, Class<?>> bindingMap;
    private static ClassBinder INSTANCE;


    protected ClassBinder() {
        if (INSTANCE != null) {
            throw new RuntimeException("Can not instantiate another binder");
        }
        bindingMap = new HashMap<>();
        init();
        INSTANCE = this;
    }

    public abstract void init();

    protected void register(String name, Class<?> type) {
        if (bindingMap.containsKey(name)) {
            if (bindingMap.get(name) != type) {
                throw new RuntimeException("can not rebind");
            }
        }
        bindingMap.put(name, type);
    }

    protected void register(Class<?> type) {
        this.register(type.getSimpleName(), type);
    }

    public Class<?> get(String bound) {
        if (bound.endsWith("[]")) {
            bound = bound.substring(0, bound.length() - 2);
        }
        if (!bindingMap.containsKey(bound)) {
            throw new RuntimeException(String.format("Could not find class %s", bound));
        }
        return this.bindingMap.get(bound);
    }

    public static ClassBinder getInstance() {
        return INSTANCE;
    }
}
