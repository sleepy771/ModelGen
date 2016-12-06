package me.sleepyprojects.modelgen.ast.builder;

import me.sleepyprojects.modelgen.ast.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

@Scope(ScopeType.DECLARATION)
public class ModuleImpl implements Module {

    private final Path path;
    private final List<Code> codes;
    private final String name;

    ModuleImpl(String name, Path path, List<Code> codes) {
        this.path = path;
        this.codes = codes;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Prototype> getPrototypes() {
        return this.codes.stream()
                .filter(Prototype.class::isInstance)
                .map(possession -> (Prototype) possession)
                .collect(Collectors.toList());
    }

    @Override
    public List<Function> getFunctions() {
        return this.codes.stream()
                .filter(Function.class::isInstance)
                .map(possession -> (Function) possession)
                .collect(Collectors.toList());
    }

    @Override
    public List<Variable> getVariables() {
        return this.codes.stream()
                .filter(Variable.class::isInstance)
                .map(possession -> (Variable) possession)
                .collect(Collectors.toList());
    }

    @Override
    public List<Import> getImports() {
        return this.codes.stream()
                .filter(Import.class::isInstance)
                .map(possession -> (Import) possession)
                .collect(Collectors.toList());
    }

    @Override
    public Path getPath() {
        return path;
    }

    public List<Code> getCodeLines() {
        return codes;
    }
}
