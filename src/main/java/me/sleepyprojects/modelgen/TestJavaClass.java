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

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.sleepyprojects.modelgen.language.BaseMeta;
import me.sleepyprojects.modelgen.language.SuperTypeAssigner;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestJavaClass {

    private static TemplateManager manager;
    private static Map<String, String> javaDefaults;
    private static Language language;

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Modifier.Factory.getInstance().create("public", EnumSet.allOf(BlockType.class), 0);
        Modifier.Factory.getInstance().create("protected", EnumSet.allOf(BlockType.class), 0);
        Modifier.Factory.getInstance().create("", EnumSet.allOf(BlockType.class), 0);
        Modifier.Factory.getInstance().create("private", EnumSet.allOf(BlockType.class), 0);
        Modifier.Factory.getInstance().create("final", EnumSet.allOf(BlockType.class), 2);
        Modifier.Factory.getInstance().create("static", EnumSet.allOf(BlockType.class), 1);
        Modifier.Factory.getInstance().create("volatile", EnumSet.of(BlockType.FIELD), 3);
        Modifier.Factory.getInstance().create("synchronized", EnumSet.of(BlockType.METHOD, BlockType.BLOCK), 3);

        Meta iBar = new BaseMeta("IBar", "base", Meta.Type.INTERFACE);
        Meta iBaz = new BaseMeta("IBaz", "other.pkg", Meta.Type.INTERFACE);
        Meta aBax = new BaseMeta("Bax", "pkg", Meta.Type.CLASS);
        Meta string = new BaseMeta("String", "java.lang", Meta.Type.CLASS);
        Meta longTime = new BaseMeta("long", "-", Meta.Type.TYPE);

        Argument.Builder argument = new Argument.Builder("time");
        argument.addModifier(Modifier.Factory.getInstance().get("final"));
        argument.setType(longTime);

        Argument.Builder argument2 = new Argument.Builder("something");
        argument2.addModifier(Modifier.Factory.getInstance().get("final"));
        argument2.setType(string);

        MethodDefinition.Builder methodBuilder = new MethodDefinition.Builder("getName");
        methodBuilder.setReturnType(string);
        methodBuilder.addModifier(Modifier.Factory.getInstance().get("public"));
        methodBuilder.addArgument(argument.build());
        methodBuilder.addArgument(argument2.build());


        ClassDefinition.Builder builder = new ClassDefinition.Builder();
        builder.setName("Foo");
        builder.setPackage("base");
        builder.setClassType(Meta.Type.CLASS);
        builder.addSuperClass(iBar);
        builder.addSuperClass(iBaz);
        builder.addSuperClass(aBax);
        builder.addModifier(Modifier.Factory.getInstance().get("final"));
        builder.addModifier(Modifier.Factory.getInstance().get("public"));
        builder.addMethod(methodBuilder.build());


        Generator generator = new Generator(getLanguage());
        generator.setBlock(builder.build().create(getLanguage()));
        generator.flatten();
        final String built = generator.getCode();
        time = System.currentTimeMillis() - time;
        System.out.println(built);
        System.out.println("It took : " + time + " [ms]");
    }

    public static Language getLanguage() {
        if (language == null) {
            language = new Language() {
                @Override
                public Map<String, String> getPredefined() {
                    if (TestJavaClass.javaDefaults == null) {
                        TestJavaClass.initDefaults();
                    }
                    return TestJavaClass.javaDefaults;
                }

                @Override
                public TemplateManager getManager() {
                    if (TestJavaClass.manager == null) {
                        TestJavaClass.manager = new FooManager();
                    }
                    return TestJavaClass.manager;
                }

                @Override
                @SuppressWarnings("unchecked")
                public <T extends Assigner> T getAssigner(Class<T> type) {
                    if (type == SuperTypeAssigner.class) {
                        return (T) new SuperTypeAssigner();
                    }
                    throw new RuntimeException("NOPE 3");
                }
            };
        }
        return language;
    }

    private static void initDefaults() {
        javaDefaults = new HashMap<>();
        javaDefaults.put("LN", "\n");
        javaDefaults.put("TAB", "    ");
        javaDefaults.put("S", " ");
    }
}
