package me.sleepyprojects.modelgen.ast;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface Type extends Modifier {

    boolean isBuildIn();

    Import getImport();
}
