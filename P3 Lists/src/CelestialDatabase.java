//terwi045

public class CelestialDatabase {

    private List<Star> list;

    public CelestialDatabase(String type) {
        if (type.equals("array"))
            list = new ArrayList<Star>();
        //else list = new LinkedList<Star>();
    }

    public boolean add(Star s) {
        return list.add(s);
    }

    public Star find(String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(name))
                return (list.get(i));
        }
        return null;
    }

    public Star findSun() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSun())
                return list.get(i);
        }
        return null;
    }

    public Star remove(int index) {
        return list.remove(index);
    }

    public Star get(int index) {
        return list.get(index);
    }

    public void sort() {
        list.sort(true);
    }

    public Star[] getStarsByType(String type) {
        if (list.size() == 0)
            return null;
        Star[] starArray = new Star[0];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Sequence && type.equals("sequence"))
                starArray = arrayLengthen(starArray, list.get(i));
            else if (list.get(i) instanceof RedGiant && type.equals("redgiant"))
                starArray = arrayLengthen(starArray, list.get(i));
            else if (list.get(i) instanceof WhiteDwarf && type.equals("whitedwarf"))
                starArray = arrayLengthen(starArray, list.get(i));
        }
        return starArray;
    }

    public Star getHeaviestStar() {
        if (list.size() == 0)
            return null;
        Star largestMass = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(largestMass) > 0)
                largestMass = list.get(i);
        }
        return largestMass;
    }

    public Star getHeaviestRedGiant() {
        if (list.size() == 0)
            return null;
        Star largestMass = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof RedGiant)
                if (largestMass == null)
                    largestMass = list.get(i);
                else if (list.get(i).compareTo(largestMass) > 0)
                    largestMass = list.get(i);
        }
        return largestMass;
    }

    public Star getLargestStar() {
        if (list.size() == 0)
            return null;
        Star largestStar = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getSize() > largestStar.getSize())
                largestStar = list.get(i);
        }
        return largestStar;
    }

    public Star[] listBlackHoles() {
        if (list.size() == 0)
            return null;
        Star[] blackHoleArray = new Star[0];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isBlackHole())
                blackHoleArray = arrayLengthen(blackHoleArray, list.get(i));
        }
        return blackHoleArray;
    }

    public Star[] getTopKHeaviestStar(int k) {
        if (list.size() == 0)
            return null;
        int arrayLength = k;
        if (list.size() < k)
            arrayLength = list.size();
        Star[] heaviest = new Star[0];
        list.sort(false);
        for (int i = 0; i < arrayLength; i ++) {
            heaviest = arrayLengthen(heaviest, list.get(i));
        }
        return heaviest;

    }

    public Star[] getTopKLargestStar(int k) {
        // switch size and mass
        for (int i = 0; i < list.size(); i++) {
            double mass = list.get(i).getMass();
            double size = list.get(i).getSize();
            list.get(i).setMass(size);
            list.get(i).setSize(mass);
        }
        // get largest mass (but it is really size)
        Star[] largest = getTopKHeaviestStar(k);
        // switch back
        for (int i = 0; i < list.size(); i++) {
            double mass = list.get(i).getMass();
            double size = list.get(i).getSize();
            list.get(i).setMass(size);
            list.get(i).setSize(mass);
        }

        return largest;

    }

    public int[] countStars() {
        int[] r = {getStarsByType("sequence").length, getStarsByType("redgiant").length, getStarsByType("whitedwarf").length};
        return r;
    }


    public Star[] arrayLengthen(Star[] array, Star star) { // helper function
        Star[] newArray = new Star[array.length+1];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        newArray[array.length] = star;
        return newArray;
    }


    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += list.get(i).toString() + "\n";
        }
        return str;

    }

    public String toString(String attr) {
        String str = "";
        if (attr.equals("name")) {
            for (int i = 0; i < list.size() - 1; i++) {
                str += list.get(i).getName() + ", ";
            }
            str += list.get(list.size() - 1).getName();
        }
        else if (attr.equals("mass")) {
            for (int i = 0; i < list.size() - 1; i++) {
                str += list.get(i).getMass() + ", ";
            }
            str += list.get(list.size() - 1).getMass();
        }
        else if (attr.equals("size")) {
            for (int i = 0; i < list.size() - 1; i++) {
                str += list.get(i).getSize() + ", ";
            }
            str += list.get(list.size() - 1).getSize();
        }
        return str;
    }

    public static void main(String[] args) {
        CelestialDatabase c = new CelestialDatabase("array");
        Star star1 = new Sequence("Sun",2, 430);
        Star star2 = new Sequence("NotTheSun",100, 100);
        Star star3 = new Sequence("Charlie",150, 50);
        Star star4 = new RedGiant("Dan",50, 300);
        Star star5 = new RedGiant("Ellie",150, 320);
        Star star6 = new WhiteDwarf("Freddie",10, 450);
        Star star7 = new WhiteDwarf("George",580, 920);
        Star star8 = new WhiteDwarf("Harry",225, 105);
        Star star9 = new Sequence("Ian",1200, 45);
        c.add(star1);
        c.add(star2);
        c.add(star3);
        c.add(star4);
        c.add(star5);
        c.add(star6);
        c.add(star7);
        c.add(star8);
        c.add(star9);
        System.out.println("Testing add and toString: \n" + c.toString());
        System.out.println("Testing find:\n"
                + c.find("Charlie").getName().equals("Charlie")
                + ": Found Charlie with key 'Charlie'\n"
                + c.find("harl").getName().equals("Charlie")
                + ": Found Charlie with key 'harl'\n"
                + !c.find("Dan").getName().equals("Charlie")
                + ": Charlie != Dan with key 'Dan'\n");
        System.out.println("Testing findSun:\n"
                + c.findSun().getName().equals("Sun")
                + ": Star = Sun\n"
                + !c.findSun().getName().equals("NotTheSun")
                + ": Star != NotTheSun\n");
        System.out.println("Testing remove:\n"
                + c.toString("name") + "\n"
                + c.remove(2).equals(star3)
                + ": index 2 removed " + star3.getName() + "\n"
                + c.toString("name") + "\n");
        c.add(star3); // added Charlie back
        System.out.println("Testing get:\n"
                + c.get(1).equals(star2)
                + ": index 1 returned 2nd entry\n");
        System.out.println("Testing sort:\n"
                + c.toString("mass"));
                c.sort();
        System.out.println(c.toString("mass"));
                Star[] seq = c.getStarsByType("sequence");
                Star[] red = c.getStarsByType("redgiant");
                Star[] whi = c.getStarsByType("whitedwarf");
        System.out.println("\nTesting getStarsByType:");
        System.out.println("\tTesting Sequence:");
        for (int i = 0; i < seq.length; i++) {System.out.println("\t" + seq[i].getClass());}
        System.out.println("\n\tTesting RedGiant:");
        for (int i = 0; i < red.length; i++) {System.out.println("\t" + red[i].getClass());}
        System.out.println("\n\tTesting WhiteDwarf:");
        for (int i = 0; i < whi.length; i++) {System.out.println("\t" + whi[i].getClass());}

        System.out.println("\nTesting getHeaviestStar:\n"
                + c.toString("mass") + "\n"
                + "Heaviest: " + c.getHeaviestStar().getMass());
        System.out.println("\nTesting getHeaviestRedGiant\n"
                + "RedGiant 1: " + red[0].getMass()
                + "\nRedGiant 2: " + red[1].getMass() + "\n"
                + "Heaviest RedGiant: " + c.getHeaviestRedGiant().getMass());

        System.out.println("\nTesting getLargestStar:\n"
                + c.toString("size") + "\n"
                + "Largest Star: " + c.getLargestStar().getSize());
                Star[] blackHoles = c.listBlackHoles();
        System.out.println("\nTesting listBlackHoles:");
        for (int i = 0; i < blackHoles.length; i++) {
            System.out.println(blackHoles[i].getClass() + " " + blackHoles[i].getMass() + " " + blackHoles[i].getSize());
        }
        System.out.println("\nTesting getTopKHeaviestStar:\n"
                + "Masses: " + c.toString("mass"));
        Star[] heaviest = c.getTopKHeaviestStar(4);
        for (int i = 0; i < heaviest.length; i++) {
            System.out.print(heaviest[i].getMass() + ", ");
        }
        System.out.println("\n\nTesting getTopKLargestStar:\n"
                + "Sizes: " + c.toString("size"));
        Star[] largest = c.getTopKLargestStar(5);
        for (int i = 0; i < largest.length; i++) {
            System.out.print(largest[i].getSize() + ", ");
        }
        int[] types = c.countStars();
        System.out.println("\n\nTesting countStars:");
        for (int i = 0; i < types.length; i++) {
            System.out.print(types[i] + ", ");
        }
        System.out.println();
    }
}
