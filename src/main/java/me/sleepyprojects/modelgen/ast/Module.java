package me.sleepyprojects.modelgen.ast;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

public interface Module extends Named, CodeBlock {

    List<Prototype> getPrototypes();

    List<Function> getFunctions();

    List<Variable> getVariables();

    List<Import> getImports();

    Path getPath();
}
