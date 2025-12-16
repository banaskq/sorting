package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.List.copyOf;

@Component
public class BubbleSort extends AbstractLimitedSortingAlgorithm {

    @Override
    public String getName() {
        return "bubble";
    }

    @Override
    public List<Integer> sort(List<Integer> data, int limit) {
        List<Integer> list = copyOf(data);
        int n = list.size();
        int stepsLeft = normalizeLimit(limit);

        boolean swapped;
        for (int i = 0; i < n - 1 && stepsLeft > 0; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1 && stepsLeft > 0; j++) {
                stepsLeft--; // jeden krok (porównanie / operacja)
                if (list.get(j) > list.get(j + 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return list;
    }
}