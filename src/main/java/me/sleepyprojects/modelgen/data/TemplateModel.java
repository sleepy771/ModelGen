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

import java.util.Set;

import com.sun.istack.internal.NotNull;

public class TemplateModel {
  private String id;
  private String template;
  private Set<VariableModel> variables;
  private Set<String> scopes;

  public String getId() {
    return id;
  }

  public void setId(final @NotNull String id) {
    this.id = id;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(final @NotNull String template) {
    this.template = template;
  }

  public Set<VariableModel> getVariables() {
    return variables;
  }

  public void setVariables(final @NotNull Set<VariableModel> variables) {
    this.variables = variables;
  }

  public Set<String> getScopes() {
    return scopes;
  }

  public void setScopes(final @NotNull Set<String> scopes) {
    this.scopes = scopes;
  }

  @Override
  public int hashCode() {
    return 17 * 31 + (this.id != null ? this.id.hashCode() : 0);
  }

  @Override
  public boolean equals(Object obj) {
    return this == obj || (obj instanceof TemplateModel) &&
        Utils.isEqual(((TemplateModel) obj).id, this.id);
  }
}
