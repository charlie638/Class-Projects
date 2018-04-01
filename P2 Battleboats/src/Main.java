//terwi045

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int turns = 0;
        int shots = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Debug Mode (Y/N): ");
        String debug = sc.nextLine();
        boolean debugbool = false;
        while (!debug.equals("Y") && !debug.equals("y") && !debug.equals("yes") &&
                !debug.equals("N") && !debug.equals("n") && !debug.equals("no"))
        {
            Scanner s = new Scanner(System.in);
            System.out.println("Debug Mode (Y/N): ");
            debug = s.nextLine();
        }
        if (debug.equals("Y") || debug.equals("y") || debug.equals("yes"))
            debugbool = true;
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter Size of Board (between 3 & 12) \nX Y: ");
        int x = sc1.nextInt();
        int y = sc1.nextInt();
        while (x>12 || x<3 || y<3 || y>12) {
            System.out.println("Not a valid size!");
            Scanner sc2 = new Scanner(System.in);
            System.out.print("X Y: ");
            x = sc2.nextInt();
            y = sc2.nextInt();
        }
        Battleboats board = new Battleboats(x, y);
        board.generateBoard();
        board.generateBoats();
        System.out.println("There are " + board.boatQuantity() + " boats on the board");
        System.out.println("Enter a coordinate to attack");
        System.out.println("or say X Y and then 'drone' to scan a 3X3 square (costs 4 turns)");

        while (checkDone(board)) {
            if (debugbool)
                board.showBoard();
            else board.showBoardUser();
            Scanner sc3 = new Scanner(System.in);
            System.out.print("X Y: ");
            int yAttack = sc3.nextInt() - 1;
            int xAttack = sc3.nextInt() - 1;
            String next = sc3.nextLine();
            if (next.equals("drone") || next.equals("Drone") || next.equals(" drone") || next.equals(" Drone")){
                if (yAttack > x-1 || yAttack < 0 || xAttack > y-1 || xAttack < 0)
                    System.out.println("Penalty (Out of bounds)");
                else {
                    System.out.println("O = 'nothing'");
                    System.out.println("+ = 'boat'");
                    if (yAttack < y || yAttack > 0 || xAttack < x || xAttack > 0)
                        board.changeHiddenBoard(xAttack, yAttack, "reveal");
                    if (yAttack < y || yAttack > 0 || xAttack + 1 < x || xAttack + 1 > 0)
                        board.changeHiddenBoard(xAttack + 1, yAttack, "reveal");
                    if (yAttack < y || yAttack > 0 || xAttack - 1 < x || xAttack - 1 > 0)
                        board.changeHiddenBoard(xAttack - 1, yAttack, "reveal");
                    if (yAttack + 1 < y || yAttack + 1 > 0 || xAttack < x || xAttack > 0)
                        board.changeHiddenBoard(xAttack, yAttack + 1, "reveal");
                    if (yAttack - 1 < y || yAttack - 1 > 0 || xAttack < x || xAttack > 0)
                        board.changeHiddenBoard(xAttack, yAttack - 1, "reveal");
                    if (yAttack + 1 < y || yAttack + 1 > 0 || xAttack + 1 < x || xAttack + 1 > 0)
                        board.changeHiddenBoard(xAttack + 1, yAttack + 1, "reveal");
                    if (yAttack - 1 < y || yAttack - 1 > 0 || xAttack - 1 < x || xAttack - 1 > 0)
                        board.changeHiddenBoard(xAttack - 1, yAttack - 1, "reveal");
                    if (yAttack - 1 < y || yAttack - 1 > 0 || xAttack + 1 < x || xAttack + 1 > 0)
                        board.changeHiddenBoard(xAttack + 1, yAttack - 1, "reveal");
                    if (yAttack + 1 < y || yAttack + 1 > 0 || xAttack - 1 < x || xAttack - 1 > 0)
                        board.changeHiddenBoard(xAttack - 1, yAttack + 1, "reveal");
                }
                turns += 3;
            }
            else if (yAttack > y || yAttack < 0 || xAttack > x || xAttack < 0){
                System.out.println("Penalty (Out of bounds)");
                turns += 1;
            }
            else if (board.getBoard()[xAttack][yAttack] == 2) {
                System.out.println("Penalty (Already guessed)");
                turns += 1;
            }
            else if (board.getBoard()[xAttack][yAttack] == 1) {
                shots += 1;
                board.changeHiddenBoard(xAttack, yAttack, "hit");
                board.getBoard()[xAttack][yAttack] = 2; // already guessed
                boolean sunk = false;
                for (int i = 0; i < board.getBoatNum() - 1; i++) {
                    board.getBoats()[i].hit(xAttack, yAttack);
                    if (board.getBoats()[i].sunk()) {
                        sunk = true;
                        board.changeHiddenBoard(board.getBoats()[i].getX1(), board.getBoats()[i].getY1(), "sunk");
                        board.changeHiddenBoard(board.getBoats()[i].getX2(), board.getBoats()[i].getY2(), "sunk");
                        board.changeHiddenBoard(board.getBoats()[i].getX3(), board.getBoats()[i].getY3(), "sunk");
                    }
                }
                if (sunk)
                    System.out.println("Sunk");
                else System.out.println("Hit");

            }
            else {
                shots += 1;
                System.out.println("Miss");
                board.changeHiddenBoard(xAttack, yAttack, "miss");
                board.getBoard()[xAttack][yAttack] = 2; // already guessed
            }
            turns += 1;
        }
        board.showBoardUser();
        System.out.print("It took " + turns + " turns to sink every ship");
        if (shots < turns)
            System.out.println(", but the total number of cannon shots is only " + shots);
        else System.out.println("and it took " + shots + " cannon shots");

    }
    public static boolean checkDone(Battleboats board) {
        int boatsSunk = 0;
        for (int i = 0; i < board.getBoatNum()-1; i++) {
            if (board.getBoats()[i].sunk())
                boatsSunk += 1;
        }
        return boatsSunk != board.getBoatNum()-1;
    }
}
