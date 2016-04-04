package me.sleepyprojects.modelgen.language;

import java.util.Collection;
import java.util.HashSet;

public interface CanAppend<T> {

    CanAppend UNIQUE = (collection, element) -> !collection.contains(element);
    CanAppend ALL = (collection, element) -> true;
    CanAppend<Significant> UNIQUE_SIGNATURE = new CanAppend<Significant>() {
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
    };

    @SuppressWarnings("unchecked")
    static <T> CanAppend<T> all() {
        return ALL;
    }

    @SuppressWarnings("unchecked")
    static <T> CanAppend<T> unique() {
        return (CanAppend<T>) UNIQUE;
    }

    @SuppressWarnings("unchecked")
    static <T extends Significant> CanAppend<T> uniqueSignature() {
        return (CanAppend<T>) UNIQUE_SIGNATURE;
    }

    boolean canAppend(Collection<? extends T> collection, T element);
}
