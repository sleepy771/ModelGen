/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */
package me.sleepyprojects.modelgen.ast;


/**
 * Type meta class node. This is kept separately from Prototype class
 * because several languages does not have concept of Type meta class object.
 * This interface is only intended to use for static typing in languages that supports it.
 */
public interface Type extends Modifier, Named, Assignable {

    /**
     * Returns whether the type is builder-in type and does not require imports or not.
     * @return true iff this Type is builder-in and doesn't require imports.
     */
    boolean isBuildIn();

    /**
     * Returns import object that contains all important information for language transformer and Path optimizer.
     * @return import object.
     */
    Import getImport();
}
