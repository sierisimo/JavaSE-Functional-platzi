package com.platzi.functional_teacher_theory.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    private Utils() {
    }

    @SafeVarargs
    public static <T> List<T> getListOf(T... items) {
        return Arrays.asList(items);
    }
}
