package me.sleepyprojects.modelgen.data;

public final class Utils {

    private Utils() {
        throw new RuntimeException("Don't instantiate this!");
    }

    public static boolean isEqual(final Object o1, final Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }

    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static boolean isEmptyTrimmed(String value) {
        return value == null || "".equals(value.trim());
    }
}
