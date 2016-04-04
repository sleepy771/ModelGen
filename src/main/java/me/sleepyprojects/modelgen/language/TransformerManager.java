package me.sleepyprojects.modelgen.language;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TransformerManager {

    private static TransformerManager INSTANCE;

    public static TransformerManager getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("TransformerManager was not instantiated");
        }
        return INSTANCE;
    }

    public static <T, E> Transformer<T, E> getStatic(Class<T> cls) {
        return getInstance().get(cls);
    }

    public static void release() {
        INSTANCE = null;
    }
    private Map<Class<?>, Transformer<?, ?>> transformerMap;

    protected TransformerManager() {
        transformerMap = new HashMap<>();
        INSTANCE = this;
        init();
    }

    @SuppressWarnings("unchecked")
    public <T, E> Transformer<T, E> get(Class<T> cls) {
        if (!transformerMap.containsKey(cls)) {
            throw new RuntimeException("Transformer for class " + cls.getSimpleName() + " does not exist");
        }
        return (Transformer<T, E>) transformerMap.get(cls);
    }

    protected void init() {
    }

    protected void register(final @NotNull Transformer transformer) {
        if (transformerMap.containsKey(transformer.getType())) {
            if (!transformerMap.get(transformer.getType()).equals(transformer)) {
                throw new RuntimeException("Can not register multiple transformers for same class");
            }
        }
        transformerMap.put(transformer.getType(), transformer);
    }
}
