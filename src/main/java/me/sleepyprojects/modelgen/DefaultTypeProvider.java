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

import me.sleepyprojects.modelgen.data.Utils;
import me.sleepyprojects.modelgen.data.types.DefaultType;

import java.util.Map;

public class DefaultTypeProvider {

    // String, Float, Integer, Boolean, List, Map, Set

    private Map<String, Type<DefaultType>> typeMap;

    public Type<DefaultType> get(String id) {
        if (!typeMap.containsKey(id)) {
            throw new RuntimeException("undefined type");
        }
        return typeMap.get(id);
    }

    public void register(Type<DefaultType> type) {
        if (typeMap.containsKey(type.getName())) {
            if(!Utils.isEqual(type, typeMap.get(type.getName()))) {
                throw new RuntimeException("Can not redefine already defined type");
            }
            return;
        }
        typeMap.put(type.getName(), type);
    }
}
