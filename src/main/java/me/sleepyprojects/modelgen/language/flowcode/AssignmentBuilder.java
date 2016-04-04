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
package me.sleepyprojects.modelgen.language.flowcode;

import com.sun.istack.internal.NotNull;
import me.sleepyprojects.modelgen.FieldDefinition;
import me.sleepyprojects.modelgen.FunctionProxy;
import me.sleepyprojects.modelgen.VariableDefinition;
import me.sleepyprojects.modelgen.VariableProxy;
import me.sleepyprojects.modelgen.data.types.DefaultType;

public class AssignmentBuilder {
    private Assigner assigner;
    private Assignee assignee;

    private VariableBuilderFactory builderFactory;
    private VariableBuilder varBuilder;

    public static AssignmentBuilder assignField(FieldDefinition definition) {
        return null;
    }

    public static AssignmentBuilder assignVariable(VariableDefinition definition) {
        return null;
    }

    public static AssignmentBuilder assignVariable(final @NotNull String name) {
        return null;
    }

    public void setAssigner(final @NotNull Assigner assigner) {
        this.assigner = assigner;
    }

    public void setAssignee(final @NotNull Assignee assignee) {
        this.assignee = assignee;
    }

    public void setFromCall(final @NotNull String provider, final @NotNull FunctionProxy<DefaultType> definition) {

    }

    public void setFromField(final @NotNull String provider, final @NotNull VariableProxy<DefaultType> proxy) {

    }


}
