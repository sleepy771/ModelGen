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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TemplateId("arguments")
public class ArgumentsDefinition extends BaseDefinition {

    private final List<Argument> arguments;

    ArgumentsDefinition(final @NotNull List<Argument> arguments) {
        this.arguments = arguments;
    }

    @Override
    protected void assign(Language language, Map<String, Part> parts, Map<String, Block> blocks) {
        final Map<String, Block> argumentsMap = new HashMap<>();
        for (int i=0, size = arguments.size(); i < size; i++) {
            argumentsMap.put("L:arguments#" + i, arguments.get(i).create(language));
        }
        blocks.putAll(argumentsMap);
    }

    public static class Builder implements Block.Builder<ArgumentsDefinition> {

        private List<Argument> arguments;

        public Builder() {
            this.arguments = new ArrayList<>();
        }

        public void addArgument(Argument argument) {
            this.arguments.add(argument);
        }

        @Override
        public ArgumentsDefinition build() {
            return new ArgumentsDefinition(arguments);
        }
    }
}
