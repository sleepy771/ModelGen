package me.sleepyprojects.modelgen.ast;

import me.sleepyprojects.modelgen.ast.build.FieldImpl;
import me.sleepyprojects.modelgen.ast.build.MethodImpl;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

public interface Prototype extends Named, Modified, Owner, Possession {

    Type getType();

    List<Method> getMethods();

    List<Field> getFields();
}
