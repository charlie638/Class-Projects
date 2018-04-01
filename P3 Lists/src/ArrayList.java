//terwi045

public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] a;

    public ArrayList() {
        a = (T[]) new Comparable[2];
    }

    public boolean add(T element) {
        int ind = 0;
        while (a[ind] != null && ind < a.length) ind += 1;  //finds next empty spot
        if (ind == a.length-1) {
            a = lengthen(a);
        }                                                   // makes a larger if full
        if (element == null) {
            return false;
        } else a[ind] = element;
        return true;
    }

    public boolean add(int index, T element) {
        int ind = 0;
        while (a[ind] != null && ind < a.length) ind += 1;  //finds next empty spot
        if (ind == a.length-1) {
            a = lengthen(a);
        }
        if (element == null) {
            return false;
        }
        if (index > size() || index < 0) {
            return false;
        } else {
            for (int i = a.length-2; i >= index; i --) {
                a[i+1] = a[i];
            }
            a[index] = element;
        }
        return true;
    }

    public T[] lengthen(T[] list) {
        T[] temp = (T[]) new Comparable[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            temp[i] = list[i];
        }
        return temp;
    }

    public void clear() {
        a = (T[]) new Comparable[2];
    }

    public boolean contains(T element) {
        for (int i = 0; i < a.length; i ++) {
            if (a[i] == element)
                return true;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index > a.length) {
            return null;
        }
        return a[index];
    }

    public int indexOf(T element) {
        if (element == null) return -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == element) return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        for (int i = 0; i < a.length; i ++) {
            if (a[i] != null) return false;
        }
        return true;
    }

    public int lastIndexOf(T element) {
        if (element == null) return -1;
        for (int i = a.length-1; i > 0; i--) {
            if (a[i] == element) return i;
        }
        return -1;
    }

    public T set(int index, T element) {
        if (element == null || index > a.length || index < 0) return null;
        T prev = a[index];
        a[index] = element;
        return prev;
    }

    public int size() {
        int counter = 0;
        for (int i = 0; i < a.length; i ++) {
            if (a[i] != null) counter += 1;
        }
        return counter;
    }

    public void sort(boolean order) {
        int ind = 0;
        int counter = 0;
        while (counter < size()-1) {
            if (a[ind+1] == null) {
                ind = 0;
                counter = 0;
            }
            if (order) {
                if (a[ind].compareTo(a[ind + 1]) > 0) {
                    T temp = a[ind];
                    a[ind] = a[ind + 1];
                    a[ind + 1] = temp;
                } else counter += 1;
            }
            else {
                if (a[ind].compareTo(a[ind + 1]) < 0) {
                    T temp = a[ind];
                    a[ind] = a[ind + 1];
                    a[ind + 1] = temp;
                } else counter += 1;
            }
            if (ind == size()-1) {
                ind = 0;
                counter = 0;
            }
            ind += 1;
        }

    }

    public boolean remove(T element) {
        int remove = indexOf(element);
        if (remove == -1)
            return false;
        for (int i = remove; i < a.length-1; i++) {
            a[i] = a[i+1];
        }
        return true;
    }

    public T remove(int index) {
        if (index < 0 || index > a.length) {
            return null;
        }
        T temp = a[index];
        for (int i = index; i < a.length-1; i++) {
            a[i] = a[i+1];
        }
        return temp;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                if (i == a.length - 1 || a[i+1] == null)
                    str += a[i];
                else {
                    str += a[i] + ", ";
                }
            }
        }
        return str;
    }

    public boolean binarySearch(T element) {
        int lowIndex = 0;
        int highIndex = size()-1;
        while ((lowIndex!=highIndex)) {
            System.out.println(lowIndex + ", " + highIndex);
            if ((highIndex - lowIndex) % 2 == 1) { // even length list
                if (a[((highIndex - lowIndex + 1) / 2) + lowIndex].compareTo(element) >= 0) {
                    if (a[((highIndex - lowIndex + 1) / 2) + lowIndex].equals(element) || a[((highIndex - lowIndex - 1) / 2) + lowIndex].equals(element))
                        return true;
                    highIndex = (highIndex-1)/2;
                } else {
                    lowIndex = ((highIndex-lowIndex+1)/2) + lowIndex;
                }
            } else {
                if (a[((highIndex - lowIndex) / 2) + lowIndex].compareTo(element) >= 0) {
                    if (a[((highIndex - lowIndex) / 2 ) + lowIndex].equals(element)) {
                        return true;
                    }
                    highIndex/=2;
                } else {
                    lowIndex = ((highIndex-lowIndex)/2) + lowIndex;
                }
            }
        }
        return false;
    }

    public void intersect(List<T> other) {
        T last = this.get(this.size()-1);
        boolean end = false;
        int a = 0;
        while (!end) {
            if (this.get(a).equals(last)) {
                end = true;
            }
            boolean keep = false;
            for (int i = 0; i < other.size(); i++) {
                if (this.get(a).equals(other.get(i))) {
                    keep = true;
                }
            }
            if (!keep) {
                this.remove(a);
            } else {
                a++;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        //a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");
        a.add("f");
        a.add("g");
        a.add("h");

        b.add("a");
        b.add("e");
        b.add("h");
        System.out.println(a.toString());
        System.out.println(a.binarySearch("a"));

        //a.intersect(b);
        //System.out.println(a.toString());

        /*
        ArrayList<Integer> a = new ArrayList<>();
        a.add(4);
        a.add(3);
        a.add(2);
        a.add(1);
        a.add(5);
        System.out.println("Testing add:");
        System.out.println(a.toString());
        System.out.println("\nTesting add at index:");
        a.add(1,9);
        System.out.println(a.toString());
        System.out.println("Added a '9' at index 1");
        System.out.println("\nTesting clear:");
        a.clear();
        System.out.println(a.toString());
        // adding everything back
        a.add(4);
        a.add(3);
        a.add(2);
        a.add(1);
        a.add(5);
        System.out.println("\nTesting contains:");
        System.out.println(a.contains(3) + ": does contain a '3'");
        System.out.println(!a.contains(7) + ": does not contain a '7'");
        System.out.println("\nTesting get:");
        System.out.println(a.toString());
        System.out.println("3rd index is: " + a.get(3));
        System.out.println("\nTesting isEmpty");
        System.out.println(!a.isEmpty() + ": It is not empty");
        a.clear();
        System.out.println("cleared list...");
        System.out.println(a.isEmpty() + ": It is empty");
        a.add(4);
        a.add(3);
        a.add(2);
        a.add(1);
        a.add(5);
        System.out.println("\nTesting set:");
        System.out.println(a.toString());
        System.out.println(a.set(2,9).equals(2) + ": returned 2nd index");
        System.out.println(a.toString());
        System.out.println("Set 2nd index to '9'");
        System.out.println("\nTesting size:");
        System.out.println(a.toString());
        System.out.print(a.size() == 5);
        System.out.println(": There are 5 items in the list");
        System.out.println("\nTesting sort:");
        System.out.println(a.toString() + "\toriginal order");
        a.sort(true);
        System.out.println(a.toString() + "\torder = true");
        a.sort(false);
        System.out.println(a.toString() + "\torder = false");
        System.out.println("\nTesting remove:");
        System.out.println(a.toString());
        a.remove(3);
        System.out.println(a.toString());
        System.out.println("Removed 3rd index\n");


        ArrayList<String> b = new ArrayList<>();
        b.add("a");
        b.add("b");
        b.add("c");
        b.add("d");
        System.out.println("===================================");
        System.out.println("\nTesting again but with Strings");
        System.out.println("\nTesting add");
        System.out.println(b.toString());
        System.out.println("\nTesting add at index");
        b.add(1,"g");
        System.out.println(b.toString());
        System.out.println("added 'g' at 1st index");
        System.out.println("\nTesting clear and isEmpty:");
        System.out.println(b.toString());
        System.out.println(!b.isEmpty() + ": It is not empty");
        b.clear();
        System.out.println(b.isEmpty() + ": It is empty");
        b.add("a");
        b.add("b");
        b.add("g");
        b.add("d");
        b.add("c");
        System.out.println("\nTesting contains:");
        System.out.println(b.toString());
        System.out.println(b.contains("b") + ": Does contain a 'b'");
        System.out.println(!b.contains("f") + ": Does not contain a 'f'");
        System.out.println("\nTesting get:");
        System.out.println(b.get(2).equals("g") + ": Second index is a 'g'");
        b.add(4,"b");
        System.out.println("\nTesting indexOf:");
        System.out.println(b.toString());
        System.out.print(b.indexOf("b") == 1);
        System.out.println(": The index of 'b' is 1");
        System.out.println("\nTesting lastIndexOf");
        System.out.println(b.toString());
        System.out.print(b.lastIndexOf("b") == 4);;
        System.out.println(": The last index of 'b' is 4");
        System.out.println("\nTesting set:");
        System.out.println(b.toString());
        b.set(3,"f");
        System.out.println(b.toString());
        System.out.println("Set 3rd index to 'f'");
        System.out.println("\nTesting size:");
        System.out.print(b.size() == 6);
        System.out.println(": The size of the list is 6");
        System.out.println("\nTesting sort:");
        System.out.println(b.toString() + "\toriginal order");
        b.sort(true);
        System.out.println(b.toString() + "\torder = true");
        b.sort(false);
        System.out.println(b.toString() + "\torder = false");
        System.out.println("\nTesting remove:");
        System.out.println(b.toString());
        b.remove(3);
        System.out.println(b.toString());
        System.out.println("Removed the 3rd index");
        System.out.println("\n" + b.toString());
        b.remove("c");
        System.out.println(b.toString());
        System.out.println("Removed 'c'");
        */
    }
}
