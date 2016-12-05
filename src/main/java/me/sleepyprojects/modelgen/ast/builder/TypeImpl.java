package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.Import;
import me.sleepyprojects.modelgen.ast.Type;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

public class TypeImpl implements Type {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isBuildIn() {
        return false;
    }

    @Override
    public Import getImport() {
        return null;
    }
}
