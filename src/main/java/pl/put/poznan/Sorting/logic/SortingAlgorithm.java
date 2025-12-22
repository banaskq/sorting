package pl.put.poznan.Sorting.logic;

import java.util.List;

/**
 * Interfejs definiujący wspólny kontrakt dla wszystkich algorytmów sortowania
 * wykorzystywanych w aplikacji. Każdy algorytm implementuje tę samą metodę
 * sortującą, dzięki czemu kontroler może uruchamiać wiele algorytmów w sposób
 * jednolity i niezależny od ich implementacji.
 *
 * <p>Algorytmy operują na danych w postaci tablicy dwuwymiarowej
 * (lista wierszy), co umożliwia sortowanie zarówno listy liczb, jak i
 * tablic obiektów. Sortowanie odbywa się po wskazanej kolumnie.</p>
 */
public interface SortingAlgorithm {

    /**
     * Zwraca nazwę algorytmu sortowania, wykorzystywaną m.in. do identyfikacji
     * algorytmu w żądaniu REST oraz w wynikach zwracanych użytkownikowi.
     *
     * @return nazwa algorytmu (np. "bubble", "quick")
     */
    String getName();

    /**
     * Sortuje przekazane dane według wskazanej kolumny, z uwzględnieniem limitu
     * kroków oraz kierunku sortowania.
     *
     * @param data        dane wejściowe jako lista wierszy
     * @param sortByIndex indeks kolumny, po której ma odbywać się sortowanie
     * @param limit       maksymalna liczba kroków sortowania; wartości ≤ 0 oznaczają brak limitu
     * @param descending  określa kierunek sortowania: {@code true} – malejąco, {@code false} – rosnąco
     * @return posortowane lub częściowo posortowane dane
     */
    List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending);
}