package me.sleepyprojects.modelgen.language;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface CanAppend<T> {

    CanAppend UNIQUE = (collection, element) -> !collection.contains(element);
    CanAppend ALL = (collection, element) -> true;

    class CanAppendSignature implements CanAppend<Significant> {
        private HashSet<Signature> signatures = new HashSet<>();

        @Override
        public boolean canAppend(Collection<? extends Significant> collection, Significant element) {
            final Signature sgn = element.createSignature();
            if (!signatures.contains(sgn)) {
                signatures.add(sgn);
                return true;
            }
            return false;
        }
    }

    class CanAppendName implements CanAppend<BaseNamedType> {

        private final Set<String> usedNames = new HashSet<>();

        @Override
        public boolean canAppend(Collection<? extends BaseNamedType> collection, BaseNamedType element) {
            if (usedNames.contains(element.getName())) {
                return false;
            }
            usedNames.add(element.getName());
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    static <T> CanAppend<T> all() {
        return ALL;
    }

    @SuppressWarnings("unchecked")
    static <T> CanAppend<T> unique() {
        return (CanAppend<T>) UNIQUE;
    }

    @SuppressWarnings("unchecked")
    static <T extends BaseNamedType> CanAppend<T> uniqueName() {
        return (CanAppend<T>) new CanAppendName();
    }

    @SuppressWarnings("unchecked")
    static <T extends Significant> CanAppend<T> uniqueSignature() {
        return (CanAppend<T>) new CanAppendSignature();
    }

    boolean canAppend(Collection<? extends T> collection, T element);
}
