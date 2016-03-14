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

import org.apache.velocity.Template;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class FooManager implements TemplateManager {
    @Override
    public Template loadTemplate(Block block) {
        final String id = block.useTemplate();
        List<String> available = Arrays.asList("class", "class-code", "method", "arguments", "argument");
        if (id == null || !available.contains(id)) {
            return null;
        }
        final String javaClassTemplate =
                "#set($extends=\"\")\n" +
                        "#if ($superclass)\n" +
                        "#set($extends=\" extends ${superclass}\")\n" +
                        "#end\n" +
                        "#set($mods=\"\")\n" +
                        "#if($modifiers)\n" +
                        "#foreach($mod in $modifiers)\n" +
                        "#set($mods=$mods + $mod + \" \")\n" +
                        "#end\n" +
                        "#end\n" +
                        "#set($implements=\"\")\n" +
                        "#if ($interfaces)\n" +
                        "#foreach ($meta in $interfaces)\n" +
                        "#if ($implements.isEmpty())\n" +
                        "#set($implements=\" implements ${meta}\")\n" +
                        "#else\n" +
                        "#set($implements=$implements+\", \"+$meta)\n" +
                        "#end\n" +
                        "#end\n" +
                        "#end\n" +
                        "${mods}class ${name}${extends}${implements} {${LN}${TAB}${code}${LN}}${LN}";
        RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
        StringReader classReader = new StringReader(javaClassTemplate);

        final String arguments =
                "#set($arguments=\"\")\n" +
                        "#if($args)\n" +
                        "#foreach($arg in $args)\n" +
                        "#if($arguments.isEmpty())\n" +
                        "#set($arguments=$arg)\n" +
                        "#else\n" +
                        "#set($arguments=$arguments + \", \" + $arg)\n" +
                        "#end\n" +
                        "#end\n" +
                        "#end\n" +
                        "$arguments";
        StringReader argsReader = new StringReader(arguments);

        final String argument =
                "#set($modifiers=\"\")\n" +
                        "#if($mods)\n" +
                        "#foreach($mod in $mods)\n" +
                        "#set($modifiers=$modifiers + $mod + \" \")\n" +
                        "#end\n" +
                        "#end\n" +
                        "${modifiers}$type $name";
        StringReader argReader = new StringReader(argument);

        final String classCode =
                "#if ($fields)\n" +
                        "#foreach($field in $fields)\n" +
                        "$field\n" +
                        "#end\n" +
                        "#end\n" +
                        "#if ($methods)\n" +
                        "\n" +
                        "#foreach ($method in $methods)\n" +
                        "$method\n\n" +
                        "#end\n" +
                        "#end\n";
        StringReader classCodeReader = new StringReader(classCode);

        final String methodCode =
                "#set($modifiers=\"\")\n" +
                        "#if($mods)\n" +
                        "#foreach($mod in $mods)\n" +
                        "#set($modifiers=$modifiers + $mod + \" \")\n" +
                        "#end\n" +
                        "#end\n" +
                        "${modifiers}$type $name($arguments){${LN}${TAB}$code${LN}}${LN}${LN}";
        StringReader methodReader = new StringReader(methodCode);

        SimpleNode node;
        try {
            switch (id) {
                case "class":
                    node = runtimeServices.parse(classReader, "java-class-template");
                    break;
                case "arguments":
                    node = runtimeServices.parse(argsReader, "java-arguments-template");
                    break;
                case "argument":
                    node = runtimeServices.parse(argReader, "java-argument-template");
                    break;
                case "class-code":
                    node = runtimeServices.parse(classCodeReader, "java-class-code-template");
                    break;
                case "method":
                    node = runtimeServices.parse(methodReader, "java-method-template");
                    break;
                default:
                    throw new RuntimeException("NOPE 2");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("NOPE");
        }
        Template template = new Template();
        template.setRuntimeServices(runtimeServices);
        template.setData(node);
        template.initDocument();
        return template;
    }
}
