package com.example.project;

public class SinglyLinkedList<T extends Comparable> {
    private Node<T> first; // Primero nodo de la lista
    private int size; // Tamano de la lista

    // Constructor (crea lista vacia)
    SinglyLinkedList() {
        first = null;
        size = 0;
    }

    // Retorna el tamano de la lista
    public int size() {
        return size;
    }

    // Devuelve true si la lista esta vazia o false caso contrario
    public boolean isEmpty() {
        return (size == 0);
    }

    // Adiciona v al inicio de la lista
    public void addFirst(T v) {
        Node<T> newNode = new Node<T>(v, first);
        first = newNode;
        size++;
    }

    // Adiciona v al final de la lista
    public void addLast(T v) {
        Node<T> newNode = new Node<T>(v, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            Node<T> cur = first;
            while (cur.getNext() != null)
                cur = cur.getNext();
            cur.setNext(newNode);
        }
        size++;
    }

    // Retorna el primer valor de la lista (o null si la lista esta vacia)
    public T getFirst() {
        if (isEmpty())
            return null;
        return first.getValue();
    }

    // Retorna el ultimo valor de la lista (o null si la lista esta vazia)
    public T getLast() {
        if (isEmpty())
            return null;
        Node<T> cur = first;
        while (cur.getNext() != null)
            cur = cur.getNext();
        return cur.getValue();
    }

    // Elimina el primer elemento de la lista (si esta vacia no hara nada)
    public void removeFirst() {
        if (isEmpty())
            return;
        first = first.getNext();
        size--;
    }

    // Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
    public void removeLast() {
        if (isEmpty())
            return;
        if (size == 1) {
            first = null;
        } else {
            // Ciclo con for y uso de size para mostrar alternativa al while
            Node<T> cur = first;
            for (int i = 0; i < size - 2; i++)
                cur = cur.getNext();
            cur.setNext(cur.getNext().getNext());
        }
        size--;
    }

    // Convierte la lista para um String
    public String toString() {
        String str = "{";
        Node<T> cur = first;
        while (cur != null) {
            str += cur.getValue();
            cur = cur.getNext();
            if (cur != null)
                str += ",";
        }
        str += "}";
        return str;
    }

    // NUEVOS METODOS

    // Elimina aquellos nodos de la lista que esten duplicados
    public void deleteDuplicates() {
    	Node<T> aux = this.first; //Creando nodo auxiliar
    	while (aux != null) {  //iterando la lista 
    		int pos = 1;
    		for(Node<T> aux2 = aux.getNext(); aux2 != null; aux2 = aux2.getNext()) { //iterando nodo por nodo despues del nodo aux
    			if(aux.getValue().compareTo(aux2.getValue()) == 0) {    //Si algun nodo despues del aux su valor es igual se eliminara
    				deletePorValor(aux2.getValue()); //Invocando al metodo para que borre el valor repetido que se le manada por parametro
    			}
    			
    		}
    		aux = aux.getNext();

    	}

    }
    public void deletePorValor(T x) { //Creando nuevo metodo delete que ahora borrara por valor
		Node<T> aux = this.first; //nodo auxiliar
		while(aux.getNext() != null && !(aux.getNext().getValue().compareTo(x) == 0)) {
			aux = aux.getNext();    //Encontrando el nodo, uno antes del nodo que tiene el valor
		}
		aux.setNext(aux.getNext().getNext()); //Ya apuntara al nodo del valor que queremos eliminar
		
	}
    

    // Inserta un nuevo nodo en una posicion especifica de la lista
    public void insertNth(T data, int position) {
    	if (position == this.size + 1) { //Cuando se pasa como posicion el numero de elemtos se agregara al final
    		//Es un caso especial ya que no se cuenta el primer elemnto desde 0 sino desde 1 y por eso lo evaluamos primero
      		 addLast(data);
      	 }
    	else if(verificarRango(position - 1, this.size)) { //Asegurando que la posicion no sea mayor que la longitud de la lista
    		//Como esta vez podemos pasarnos por 1 la posicion para añadir al final restamos 1 para que el metodo funcione correctamente
    		//‘a’ ‘b’ ‘d’
    		//insertNth (‘c’, 3)
    		//{a,b,d,c}
     		System.out.println("Fuera de rango.");
     		return;
    	 }
    	 else if (position == 0) { //Si la pocision es 0 se agregara al inicio
    		 addFirst(data);
    	 }
    	 else { //para los demas casos
    		 Node<T> aux = this.first; //creamos nodo auxiliar
    			for(int i = 0; i < position - 1; i++) {
    				aux = aux.getNext(); //iteramos hasta llegar un nodo antes de la posicion que queremos
    			}
    			aux.setNext(new Node<T>(data, aux.getNext()));  //Modificamos para que el nodo de la posicion anterior apunte al nuevo nodo a insertar
    			this.size++;
    	 }
     	
    }

    // Elimina el nodo de una posicion especifica de la lista
    public void deleteNth(int position) {
        if(verificarRango(position, this.size)) { //Asegurando que la posicion no sea mayor que la longitud de la lista
    		System.out.println("Fuera de rango.");
    		return;
    	}
    	else if(position == 0) {  //si se quiere eliminar el primer elemento
    		removeFirst();
    	}
    	else { //para los demas casos
    		Node<T> aux = this.first; //creamos nodo auxiliar
    		for(int i = 0; i < position - 1; i++) {
    			aux = aux.getNext(); //Iteramos hasta llegar una posicion antes del nodo que queremos eliminar
    		}
    		aux.setNext(aux.getNext().getNext());	//Modificamos para que ya no apunte al nodo que queremos eliminar, sino al que le sigue
    		 //Como la posicion empieza en 0 restamos 1 al numero de elemntos de la lista
    		this.size--;
    	}  	
    }
    public boolean verificarRango(int position, int length) {  //metodo para verificar que la posicion no se pase
    	return position > length - 1;                                     //tambien funcionara si la lista esta vacia
    }


    public static void main(final String[] args) {

        // testExercicio1();
         testExercicio2();
        //testExercicio3();       

    }

    public static void testExercicio1(){

        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        list.addLast(47);
        list.addLast(89);
        list.addLast(56);
        list.addLast(89);
        list.addLast(56);

        System.out.println(list);

        list.deleteDuplicates();

        System.out.println(list);
    }

    public static void testExercicio2(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.insertNth('c', 2);

        System.out.println(list);
    }

    public static void testExercicio3(){

        SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

        list.addLast('a');
        list.addLast('b');
        list.addLast('d');

        System.out.println(list);

        list.deleteNth(2);

        System.out.println(list);
    }

}
