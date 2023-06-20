package mx.unam.ciencias.icc;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
            return this.siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            // Aquí va su código.
            if(siguiente == null)
                throw new NoSuchElementException();
            T elemento = siguiente.elemento;
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            // Aquí va su código.
            return this.anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            // Aquí va su código.
            if(anterior == null)
                throw new NoSuchElementException();
            T elemento = anterior.elemento;
            siguiente = anterior;
            anterior = anterior.anterior;
            return elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            // Aquí va su código.
            anterior = null;
            siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            // Aquí va su código.
            anterior = rabo;
            siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if(elemento == null){
            throw new IllegalArgumentException("El elemento es null");
        } Nodo nuevoNodo = new Nodo(elemento);
        if(esVacia()){
            cabeza = rabo = nuevoNodo;
        } else{
            rabo.siguiente = nuevoNodo;
            nuevoNodo.anterior = rabo;
            rabo = nuevoNodo;
        } 
        longitud ++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");
        Nodo n = new Nodo(elemento);
        if (esVacia())
            cabeza = rabo = n;
        else{
            cabeza.anterior = n;
            n.siguiente = cabeza;
            cabeza = n;
        }
        longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        if (elemento == null)
            throw new IllegalArgumentException("El elemento es null");;
        if (i < 1)
            agregaInicio(elemento);
        else if (i > longitud-1){
            agregaFinal(elemento);
        } else {
            Nodo nuevoNodo = new Nodo(elemento);
            Nodo nodo = buscaNodo(get(i));
            nuevoNodo.anterior = nodo.anterior;
            nodo.anterior.siguiente = nuevoNodo;
            nuevoNodo.siguiente = nodo;
            nodo.anterior = nuevoNodo;
            longitud++;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
        Nodo n = buscaNodo(elemento);
        if (n == null)
            return;
        if (n == cabeza){
            eliminaPrimero();
            return;
        }
        if (n == rabo){
            eliminaUltimo();
            return;
        }
        Nodo a = n.anterior;
        Nodo b = n.siguiente;
        a.siguiente = b;
        b.anterior = a;
        longitud = longitud-1;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if (longitud > 1){
            T temporal = cabeza.elemento;
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud = longitud-1;
            return temporal;
        }
        if (longitud == 1){
            T elemento = cabeza.elemento;
            limpia();
            return elemento;
        }
        throw new NoSuchElementException("La lista es vacía");
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if (longitud > 1){
            T temporal = rabo.elemento;
            rabo = rabo.anterior;
            rabo.siguiente = null;
            longitud = longitud-1;
        return temporal;
        }
        if (longitud == 1){
            T elemento = cabeza.elemento;
            limpia();
            return elemento;
        }
        throw new NoSuchElementException("La lista es vacía");
    }

    /**
     *Método para buscar Nodos.
    */

    private Nodo buscaNodo(T elemento){
      Nodo n = cabeza;
      if(elemento == null) return null;
      while(n != null){
        if(n.elemento.equals(elemento)) return n;
        n = n.siguiente;
      }
      return null;
     }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
        return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        Lista<T> reversa = new Lista<T>();
        Nodo n = cabeza;
        while (n != null){
            reversa.agregaInicio(n.elemento);
            n = n.siguiente;
        }
        return reversa; 
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        Lista<T> copia = new Lista<T>();
        Nodo n = cabeza;
        while (n != null){
            copia.agregaFinal(n.elemento);
            n = n.siguiente;
        }
        return copia;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if (cabeza != null)
            return cabeza.elemento;
        throw new NoSuchElementException("La lista es vacía");
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if(rabo != null)
            return rabo.elemento;
        throw new NoSuchElementException("La lista es vacía");
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        if (i < 0 || i >= getLongitud())
            throw new ExcepcionIndiceInvalido("el indice es menor que cero o mayor o igual que el número de elementos en la lista");
        Nodo n = cabeza;
        int c = 0;
        while (n != null) {
            if (c == i)
                return n.elemento;
            n = n.siguiente;
            c++;
        }
        return null;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        Nodo nodo = cabeza;
        int ind = 0;
        while(nodo != null) {
          if(nodo.elemento.equals(elemento))
              return ind;
          nodo = nodo.siguiente;
          ind++;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if(esVacia()) {
        return "[]";
        }else{
            String s = "[";
            for (int i = 0; i < longitud-1; i++)
            s += String.format("%s, ", get(i));
            s += String.format("%s]", get(longitud-1));
            return s;
      }
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
        if(lista == null) {
            return false;
        }else if(lista.getLongitud() != longitud) {
            return false;
        }else if (lista.getLongitud() == 0 && longitud == 0) {
            return true;
        }
        Nodo n = cabeza;
        int i = 0;
        while(n != null) {
        if(n.elemento.equals(lista.get(i)) == false)
        return false;
        n = n.siguiente;
        i++;
    }
    return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Método auxiliar para MERGESORT (MEZCLA)
    */

     private Lista<T> mezcla(Lista<T> a, Lista<T> b, Comparator<T> comparador){
        Lista<T> listaOrdenada = new Lista<T>();
        while(a.cabeza != null && b.cabeza != null){
            int i = comparador.compare(a.cabeza.elemento, b.cabeza.elemento);
            if(i <= 0){
              listaOrdenada.agregaFinal(a.getPrimero());
              a.eliminaPrimero();
            }else{
              listaOrdenada.agregaFinal(b.getPrimero());
              b.eliminaPrimero();
            }
        }
        while(a.cabeza != null){
          listaOrdenada.agregaFinal(a.getPrimero());
          a.eliminaPrimero();
        }
        while(b.cabeza != null){
          listaOrdenada.agregaFinal(b.getPrimero());
          b.eliminaPrimero();
        }
        return listaOrdenada;
    }

    /**
     * Método auxiliar para MERGESORT.
    */

    private Lista<T> mergeSort(Lista<T> l, Comparator<T> comparador){
      if(l.esVacia() || l.getLongitud() == 1) return l;
      int mitad = l.getLongitud() / 2 ;
      Lista<T> l1 = new Lista<T>();
      Lista<T> l2;
      while(l.getLongitud() != mitad){
        l1.agregaFinal(l.getPrimero());
        if(l.getLongitud() != 0)
           l.eliminaPrimero();
      }
      l2 = l.copia();
      return mezcla(mergeSort(l1, comparador), mergeSort(l2, comparador), comparador);
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        // Aquí va su código.
        return mergeSort(this.copia(), comparador);
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        Nodo n = cabeza;
        while(n != null) {
            if(comparador.compare(elemento, n.elemento) == 0) return true;
            n = n.siguiente;
        }
        return false;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
