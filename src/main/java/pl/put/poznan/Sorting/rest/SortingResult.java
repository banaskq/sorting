package pl.put.poznan.Sorting.rest;

/**
 * Reprezentuje wynik działania pojedynczego algorytmu sortowania.
 * Zawiera nazwę algorytmu, czas wykonania oraz posortowane dane.
 *
 * <p>Format danych wynikowych zależy od typu danych wejściowych:
 * <ul>
 *     <li>lista liczb → wynik to lista liczb,</li>
 *     <li>tablica obiektów → wynik to tablica dwuwymiarowa.</li>
 * </ul>
 * </p>
 */
public class SortingResult {

    private String algorithm;
    private long timeMillis;
    private Object sortedData;

    /**
     * Tworzy nowy obiekt reprezentujący wynik sortowania.
     *
     * @param algorithm  nazwa algorytmu
     * @param timeMillis czas wykonania w milisekundach
     * @param sortedData posortowane dane
     */
    public SortingResult(String algorithm, long timeMillis, Object sortedData) {
        this.algorithm = algorithm;
        this.timeMillis = timeMillis;
        this.sortedData = sortedData;
    }

    public String getAlgorithm() { return algorithm; }
    public long getTimeMillis() { return timeMillis; }
    public Object getSortedData() { return sortedData; }
}