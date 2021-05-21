package al_2.lab;

import java.util.Comparator;

public class SortingParams {
    private boolean byKey = false;
    private boolean asc = true;
    private Comparator<Long> comparator = (o1, o2) -> 0;

    public SortingParams sortByKey() {
        byKey = true;
        return this;
    }

    public SortingParams ascSorting() {
        asc = true;
        return this;
    }

    public SortingParams sortByValue() {
        byKey = false;
        return this;
    }

    public SortingParams descSorting() {
        asc = false;
        return this;
    }

    Comparator<Long> getComparator() {
        if (asc) {
            comparator = Comparator.reverseOrder();
        } else {
            comparator = Long::compareTo;
        }
        return comparator;
    }

    public boolean isByKey() {
        return byKey;
    }
}
