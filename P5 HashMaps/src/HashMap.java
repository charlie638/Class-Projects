// terwi045

public class HashMap<K,V> implements Map<K,V> {

    private LinkedList[] array;

    public HashMap(int buckets) {
        if (buckets > 0) {
            this.array = new LinkedList[buckets];
            // initializes the LinkedLists in the array
            for (int i = 0; i < buckets; i++) {
                array[i] = new LinkedList();
            }
        }
    }

    public HashMap() {
        this.array = new LinkedList[64];
        // initializes the LinkedLists in the array
        for (int i = 0; i < 64; i++) {
            array[i] = new LinkedList();
        }
    }

    public String toString() {
        if (isEmpty())
            return "{}";
        else {
            String str = "{";
            // goes through all the LinkedList
            for (int i = 0; i < array.length; i++) {
                // goes through each element in the LinkedList
                for (int j = 0; j < array[i].size(); j++) {
                    str += array[i].getKey(j) + ": " + array[i].getValue(j) + ", ";
                }
            }
            // removes the last comma
            str = str.substring(0, str.length()-2);
            str += "}";
            return str;
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % array.length;
    }

    public void clear() {
        int length = array.length;
        // re-initializes the LinkedList array
        array = new LinkedList[length];
        // re-initializes each LinkedList
        for (int i = 0; i < length; i++) {
            array[i] = new LinkedList();
        }
    }

    public boolean containsKey(K key) {
        // goes through each element in the LinkedList
        for (int i = 0; i < array[hash(key)].size(); i++) {
            // checks whether the keys equal
            if (array[hash(key)].getKey(i).equals(key))
                return true;
        }
        return false;
    }

    public boolean containsValue(V value) {
        // goes through each LinkedList
        for (int i = 0; i < array.length; i++) {
            // goes through each element in the LinkedList
            for (int j = 0; j < array[i].size(); j++) {
                // checks whether the values equal
                if (array[i].getValue(j).equals(value))
                    return true;
            }
        }
        return false;
    }

    public V get(K key) {
        // goes through each element in the LinkedList
        for (int i = 0; i < array[hash(key)].size(); i++) {
            // checks whether the keys equal
            if (array[hash(key)].getKey(i).equals(key))
                return (V) array[hash(key)].getValue(i);
        }
        return null;
    }

    public boolean isEmpty() {
        // checks whether the size method returns 0
        return size()==0;
    }

    public V put(K key, V value) {
        // checks whether the key exists
        if (get(key)==null) {
            // if the key doesn't exist, it adds a new Entry to the correct LinkedList
            array[hash(key)].add(key, value);
            return value;
        } else {
            // returns the value that is associated with the key
            return get(key);
        }
    }

    public V remove(K key) {
        // goes through each element in the LinkedList
        for (int i = 0; i < array[hash(key)].size(); i++) {
            // checks whether the keys equal
            if (array[hash(key)].getKey(i).equals(key)) {
                // creates temporary variable for value
                V val = (V) array[hash(key)].getValue(i);
                // removes the Entry node
                array[hash(key)].remove(i);
                return val;
            }
        }
        return null;
    }

    public V replace(K key, V value) {
        // goes through each element in the LinkedList
        for (int i = 0; i < array[hash(key)].size(); i++) {
            // checks whether the keys equal
            if (array[hash(key)].getKey(i).equals(key))
                // checks whether the value exists
                if (array[hash(key)].getValue(i) != null) {
                    // creates temporary variable for value
                    V val = (V) array[hash(key)].getValue(i);
                    // sets new value
                    array[hash(key)].setValue(i, value);
                    return val;
                }
                else return null;
        }
        return null;
    }

    public int size() {
        int size = 0;
        // goes through each LinkedList
        for (int i = 0; i < array.length; i++) {
            // adds up the size of each one
            size += array[i].size();
        }
        return size;
    }

}
