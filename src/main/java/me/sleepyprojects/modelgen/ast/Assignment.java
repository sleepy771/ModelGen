package me.sleepyprojects.modelgen.ast;

import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 6.12.16
 */

public interface Assignment extends Code {
    List<Variable> getTargets();

    Assignable getProducer();
}
