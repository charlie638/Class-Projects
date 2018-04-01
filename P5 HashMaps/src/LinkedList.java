//terwi045

public class LinkedList<K, V> {

    private Entry<K,V> head = null;
    private int size = 0;

    public LinkedList() {}

    public void add(K key, V val) {
        Entry<K,V> temp = new Entry<>(key, val);
        Entry<K,V> current = head;
        if (head == null) {
            head = temp;
        } 
        else {
            // find the last Entry node
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // set the last Entry node as the new Entry node (temp)
            current.setNext(temp);
        }
        this.size++;
    }

    public Object getKey(int index) {
        Entry current = head;
        // finds the Entry node at the index
        for (int i = 0; i < index; i++){
            current = current.getNext();
        }
        // returns the key of that Entry node
        return current.getKey();
    }

    public Object getValue(int index) {
        Entry current = head;
        // finds the Entry node at the index
        for (int i = 0; i < index; i++){
            current = current.getNext();
        }
        // returns the value of that Entry node
        return current.getValue();
    }

    public void setValue(int index, V val) {
        Entry current = head;
        // finds the Entry node at the index
        for (int i = 0; i < index; i++){
            current = current.getNext();
        }
        // sets the Entry node to the specified value
        current.setValue(val);
    }

    public void remove(int index) {
        Entry<K,V> current = head;
        if (index == 0) {
            head = head.getNext();
        }
        else {
            for (int i = 1; i < index; i++){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        this.size--;
    }

    public int size() {
        return this.size;
    }
}
