package pl.put.poznan.Sorting.rest;

import java.util.List;

/**
 * Reprezentuje żądanie sortowania przesyłane przez użytkownika
 * do endpointu REST. Zawiera dane wejściowe oraz konfigurację
 * dotyczącą sposobu sortowania.
 *
 * <p>Może obsługiwać dwa typy danych:
 * <ul>
 *     <li>listę liczb (np. {@code [5, 2, 9]})</li>
 *     <li>tablicę obiektów (np. {@code [["Adam", 25], ["Ewa", 30]]})</li>
 * </ul>
 * Typ danych jest rozpoznawany automatycznie.</p>
 */
public class SortingRequest {

    /**
     * Dane wejściowe do sortowania. Mogą być:
     * <ul>
     *     <li>listą liczb,</li>
     *     <li>tablicą obiektów (lista wierszy).</li>
     * </ul>
     */
    private List<Object> data;

    /**
     * Lista nazw algorytmów, które mają zostać uruchomione.
     * Dozwolone wartości to m.in. "bubble", "quick", "merge", "heap".
     */
    private List<String> algorithms;

    /**
     * Indeks kolumny, po której ma odbywać się sortowanie.
     * Wymagany tylko dla danych obiektowych.
     */
    private Integer sortByIndex;

    /**
     * Limit kroków sortowania. Wartości ≤ 0 oznaczają brak limitu.
     */
    private Integer limit;

    /**
     * Kierunek sortowania:
     * <ul>
     *     <li>"asc"  – rosnąco (domyślnie),</li>
     *     <li>"desc" – malejąco.</li>
     * </ul>
     */
    private String order;

    public List<Object> getData() { return data; }
    public void setData(List<Object> data) { this.data = data; }

    public List<String> getAlgorithms() { return algorithms; }
    public void setAlgorithms(List<String> algorithms) { this.algorithms = algorithms; }

    public Integer getSortByIndex() { return sortByIndex; }
    public void setSortByIndex(Integer sortByIndex) { this.sortByIndex = sortByIndex; }

    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }

    public String getOrder() { return order; }
    public void setOrder(String order) { this.order = order; }
}