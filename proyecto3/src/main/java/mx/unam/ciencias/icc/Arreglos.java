package mx.unam.ciencias.icc;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     *Metodo auxiliar para intercambiar en los arreglos.
    */
    
    private static <T> void intercambia(T[] arreglo, int i, int j) {
    if(i > arreglo.length || j > arreglo.length)
        return;
    if(i < 0 || j < 0)
        return;
    if(i != j) {
        T aux = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = aux;
    }
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        for(int i = 0; i < arreglo.length-1; i++)
            for(int j = i + 1; j < arreglo.length; j++) {
                if(comparador.compare(arreglo[i], arreglo[j]) > 0)
                    intercambia(arreglo, i, j);
        }
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Método auxiliar para QUICKSORT.
     */

    private static <T> void quickSort(T[] arreglo, Comparator<T> comparador, int a, int b) {
        if(a >= b)
          return;
        int i = a + 1, j = b;
        while(i < j) {
          if(comparador.compare(arreglo[i], arreglo[a]) > 0 && comparador.compare(arreglo[j], arreglo[a]) <= 0)
            intercambia(arreglo, i++, j--);
          else if (comparador.compare(arreglo[i], arreglo[a]) <= 0) 
            i++;
          else 
           j--; 

        }
        if (comparador.compare(arreglo[i], arreglo[a]) > 0)
            i--;
        intercambia(arreglo, i, a);
        quickSort(arreglo, comparador, a, --j);
        quickSort(arreglo, comparador, ++i, b);
     }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        quickSort(arreglo, comparador, 0, arreglo.length-1);
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    /**
     * Método auxiliar para BUSQUEDABINARIA.
     */
    
    private static <T> int busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador, int a, int b) {
        if(a <= b){
            int medio = a + ((b - a)/2);
            if(comparador.compare(arreglo[medio], elemento) > 0)
                return busquedaBinaria(arreglo, elemento, comparador, a, medio-1);
            else if(comparador.compare(arreglo[medio], elemento) < 0)
                return busquedaBinaria(arreglo, elemento, comparador, medio+1, b);
            else
                return medio;
        }
        return -1;
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        return busquedaBinaria(arreglo, elemento, comparador, 0, arreglo.length-1);
    }
}
