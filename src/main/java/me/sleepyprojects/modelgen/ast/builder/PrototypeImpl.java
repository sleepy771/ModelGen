package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 5.12.16
 */

@Scope(ScopeType.DECLARATION)
public class PrototypeImpl implements Prototype {

    private final List<Code> codes;
    private final Type type;
    private final Set<Modifier> modifiers;

    PrototypeImpl(
            Type type,
            Set<Modifier> modifiers,
            List<Code> codes
    ) {
        this.codes = codes;
        this.type = type;
        this.modifiers = modifiers;
    }


    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public Set<Modifier> getModifiers() {
        return modifiers;
    }

    public List<Code> getCodeLines() {
        return codes;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public List<Method> getMethods() {
        return codes.stream()
                .filter(Method.class::isInstance)
                .map(possession -> (Method) possession)
                .collect(Collectors.toList());
    }

    @Override
    public List<Field> getFields() {
        return codes.stream()
                .filter(Field.class::isInstance)
                .map(possession -> (Field) possession)
                .collect(Collectors.toList());
    }
}
