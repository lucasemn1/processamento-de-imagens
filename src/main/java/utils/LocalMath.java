package utils;

import java.util.Arrays;

public class LocalMath {
    public static int getMedian(int[] elements) {
        Arrays.sort(elements);

        if (elements.length % 2 == 0) {
            return (elements[(elements.length / 2) - 1] + elements[elements.length / 2]) / 2;
        }

        return elements[elements.length / 2];
    }
}
