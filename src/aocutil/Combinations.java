package aocutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {

    private static List<int[]> combinationUtil(List<Integer> arr, int[] data, int start,
                                       int end, int index, int r, List<int[]> combos) {
        if (index == r) {
            combos.add(data.clone());
            return combos;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr.get(i);
            combinationUtil(arr, data, i + 1, end, index + 1, r, combos);
        }
        return combos;
    }

    public static List<int[]> getCombination(List<Integer> arr, int n, int r) {
        int[] data = new int[r];
        List<int[]> combos = new ArrayList<>();
        return  combinationUtil(arr, data, 0, n - 1, 0, r, combos);
    }
}

