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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@TemplateId("method")
public class MethodDefinition extends BaseDefinition {
    private static final BlockType TYPE = BlockType.METHOD;
    private final String name;
    private final Set<Modifier> modifiers;
    private final List<ArgumentDefinition> arguments;
    private final Meta returnType;
    private Meta declaringType;

    private MethodDefinition(String name, Set<Modifier> modifiers, List<ArgumentDefinition> args, Meta returnType) {

        this.name = name;
        this.modifiers = modifiers;
        this.arguments = args;
        this.returnType = returnType;
    }

    public Meta getDeclaringType() {
        return declaringType;
    }

    public void setDeclaringType(Meta declaringType) {
        this.declaringType = declaringType;
    }

    public static class Builder implements Block.Builder<MethodDefinition> {

        private final String name;
        private final Set<Modifier> modifiers;
        private final List<ArgumentDefinition> arguments;
        private Meta returnType;

        public Builder(String name) {
            this.name = name;
            this.modifiers = new TreeSet<>();
            this.arguments = new ArrayList<>();

        }

        public void addModifier(Modifier modifier) {
            modifiers.add(modifier);
        }

        public void setReturnType(final @NotNull Meta returnType) {
            this.returnType = returnType;
        }

        public void addArgument(ArgumentDefinition argument) {
            this.arguments.add(argument);
        }

        @Override
        public MethodDefinition build() {
            return new MethodDefinition(name, modifiers, arguments, returnType);
        }
    }
}
