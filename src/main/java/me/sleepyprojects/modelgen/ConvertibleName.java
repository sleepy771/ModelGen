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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertibleName {

    private final List<String> words;

    private ConvertibleName(final List<String> name) {
        this.words = name;
    }

    public final String toScreamingSnakeCase() {
        return String.join("_", (List<String>) words.stream().map(String::toUpperCase).collect(Collectors.toList()));
    }

    public final String toSnakeCase() {
        return String.join("_", words);
    }

    public final String toCamelCase() {
        return toCamelCase(false);
    }

    public final String toPascalCase() {
        return toCamelCase(true);
    }

    private String toCamelCase(final boolean pascal) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            if (pascal && builder.length() == 0) {
                builder.append(word);
            } else {
                builder.append(Character.toUpperCase(word.charAt(0)));
                builder.append(word.subSequence(1,word.length()));
            }
        }
        return builder.toString();
    }

    public final String toKebabCase() {
        return String.join("-", words);
    }

    public static ConvertibleName fromCamel(final String name) {
        StringBuilder charBuffer = new StringBuilder();
        final List<String> words = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            final char ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (charBuffer.length() == 0) {
                    charBuffer.append(Character.toLowerCase(ch));
                } else {
                    words.add(charBuffer.toString());
                    charBuffer = new StringBuilder().append(Character.toLowerCase(ch));
                }
            } else {
                charBuffer.append(ch);
            }
        }
        words.add(charBuffer.toString());
        return new ConvertibleName(words);
    }

    public static ConvertibleName fromSnake(final String name) {
        return new ConvertibleName(Arrays.asList(name.toLowerCase().split("_")));
    }

    public static ConvertibleName fromKebab(final String name) {
        return new ConvertibleName(Arrays.asList(name.toLowerCase().split("-")));
    }
}
