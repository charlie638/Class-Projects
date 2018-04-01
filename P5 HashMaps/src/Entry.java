// terwi045

public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V val) {
        this.key = key;
        this.value = val;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V val) {
        this.value = val;
    }

    public Entry<K,V> getNext() {
        return this.next;
    }

    public void setNext(Entry<K,V> nextNode) {
        this.next = nextNode;
    }

}

