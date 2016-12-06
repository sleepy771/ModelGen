package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.Argument;
import me.sleepyprojects.modelgen.ast.Modifier;
import me.sleepyprojects.modelgen.ast.Scope;
import me.sleepyprojects.modelgen.ast.ScopeType;

import java.util.Set;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 24.11.16
 */

@Scope({ScopeType.FUNCTION, ScopeType.METHOD})
public class ArgumentImpl extends VariableImpl implements Argument {
    public ArgumentImpl(String name, Set<Modifier> modifiers) {
        super(name, modifiers);
    }
}
