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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public interface Modifier extends Part {
    EnumSet<BlockType> getSupportedTypes();

    int getOrder();

    class Comparator implements java.util.Comparator<Modifier> {

        public static final Comparator INSTANCE = new Comparator();

        @Override
        public int compare(Modifier o1, Modifier o2) {
            return o1.getOrder() - o2.getOrder();
        }
    }

    class Factory {

        private static Factory INSTANCE;

        private final Map<String, Modifier> modifiers;

        private Factory() {
            this.modifiers = new HashMap<>();
        }

        public static Factory getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new Factory();
            }
            return INSTANCE;
        }

        public Modifier get(String name) {
            if (!modifiers.containsKey(name)) {
                throw new RuntimeException("Modifier does not exist");
                // TODO change this to some meaningful exception
            }
            return modifiers.get(name);
        }

        public Modifier create(String name, EnumSet<BlockType> supported, int order) {
            if (name == null) {
                throw new NullPointerException("Mod name can not be null");
            }
            if (modifiers.containsKey(name)) {
                return get(name);
            }
            Modifier mod = new ModifierImpl(name, order, supported);
            modifiers.put(name, mod);
            return mod;
        }
    }
}
