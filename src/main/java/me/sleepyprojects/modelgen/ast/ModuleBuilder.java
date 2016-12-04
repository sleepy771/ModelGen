package me.sleepyprojects.modelgen.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * modelgen
 *
 * @author filip
 * @version 0.0.1
 * @since 4.12.16
 */

public class ModuleBuilder {

    private static class Entry<T> {

        private int order;
        private T occupier;

        Entry(int order, T occupier) {
            this.order = order;
            this.occupier = occupier;
        }

        int getOrder() {
            return order;
        }

        T getOccupier() {
            return occupier;
        }
    }

    private List<Entry<Prototype>> protoypes;
    private List<Entry<Variable>> variables;
    private List<Entry<Function>> functions;
    private List<Entry<Import>> imports;

    private int index;
    private String path;

    ModuleBuilder(String path) {
        this.path = path;
        this.protoypes = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.imports = new ArrayList<>();
    }

    private int getIndex() {
        try {
            return this.index;
        } finally {
            this.index++;
        }
    }

    private <T extends Owned> Entry<T> createEntry(T occupier) {
        return new Entry<>(this.getIndex(), occupier);
    }

    public final ModuleBuilder add(Prototype prototype) {
        this.protoypes.add(this.createEntry(prototype));
        return this;
    }

    public final ModuleBuilder add(Variable variable) {
        this.variables.add(this.createEntry(variable));
        return this;
    }

    public final ModuleBuilder add(Function function) {
        this.functions.add(this.createEntry(function));
        return this;
    }

    public final ModuleBuilder add(Import imp) {
        this.imports.add(this.createEntry(imp));
        return this;
    }

    public final Module build() {
        return null;
    }
}
