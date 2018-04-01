//terwi045

public class Battleboats {
    private int[][] board;
    private String[][] hiddenBoard;
    private int width = 0;
    private int height = 0;
    private int boatNum = 1;
    private Boat[] boats = new Boat[6];

    public Battleboats(int w, int h) {
        width = w;
        height = h;
    }

    public int getBoatNum() {
        return boatNum;
    }

    public Boat[] getBoats() {
        return boats;
    }

    public int[][] getBoard() {
        return board;
    }

    public void generateBoard() {
        board = new int[height][width];
        hiddenBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                hiddenBoard[i][j] = "-";
            }
        }
    }

    public void changeHiddenBoard(int x, int y, String kind) {
        if (kind.equals("hit"))
            hiddenBoard[x][y] = "X";
        else if (kind.equals("miss"))
            hiddenBoard[x][y] = "O";
        else if (kind.equals("sunk"))
            hiddenBoard[x][y] = "#";
        else if (kind.equals("reveal"))
            if (board[x][y] == 0)
                hiddenBoard[x][y] = "O";
            else if (board[x][y] == 1)
                hiddenBoard[x][y] = "+";
    }

    public void createBoat(int x1, int y1, int x2, int y2, int x3, int y3) {
        if (boatNum == 1) {
            Boat boat1 = new Boat(x1, y1, x2, y2, x3, y3);
            boats[0] = boat1;
        } else if (boatNum == 2) {
            Boat boat2 = new Boat(x1, y1, x2, y2, x3, y3);
            boats[1] = boat2;
        } else if (boatNum == 3) {
            Boat boat3 = new Boat(x1, y1, x2, y2, x3, y3);
            boats[2] = boat3;
        } else if (boatNum == 4) {
            Boat boat4 = new Boat(x1, y1, x2, y2, x3, y3);
            boats[3] = boat4;
        } else if (boatNum == 5) {
            Boat boat5 = new Boat(x1, y1, x2, y2, x3, y3);
            boats[4] = boat5;
        } else if (boatNum == 6) {
            Boat boat6 = new Boat(x1, y1, x2, y2, x3, y3);
            boats[5] = boat6;
        }
        boatNum += 1;
    }

    public void showBoard() {
        // print out header with numbers
        System.out.print("*  ");
        for (int i = 1; i < width + 1; i++) {
            if (i / 10 < 1)
                System.out.print(i + "  ");
            else System.out.print(i + " ");
        }
        System.out.println();
        for (int j = 0; j < height; j++) {
            // print side headers with numbers
            if (j / 9 < 1)
                System.out.print(j + 1 + "  ");
            else System.out.print(j + 1 + " ");
            // print content of board
            for (int k = 0; k < width; k++)
                System.out.print(board[j][k] + "  ");
            if (j == 0)
                System.out.println("\t 0 = 'nothing'");
            else if (j == 1)
                System.out.println("\t 1 = 'boat'");
            else if (j == 2)
                System.out.println("\t 2 = 'already guessed'");
            else System.out.println();
        }
    }


    public void showBoardUser() {
        // print out header with numbers
        System.out.print("   ");
        for (int i = 1; i < width + 1; i++) {
            if (i / 10 < 1)
                System.out.print(i + "  ");
            else System.out.print(i + " ");
        }
        System.out.println("\t - = 'unrevealed'");
        for (int j = 0; j < height; j++) {
            // print side headers with numbers
            if (j / 9 < 1)
                System.out.print(j + 1 + "  ");
            else System.out.print(j + 1 + " ");
            // print content of board
            for (int k = 0; k < width; k++)
                System.out.print(hiddenBoard[j][k] + "  ");
            if (j == 0)
                System.out.println("\t O = 'missed'");
            else if (j == 1)
                System.out.println("\t X = 'hit'");
            else if (j == 2)
                System.out.println("\t # = 'sunk'");
            else System.out.println();
        }

    }

    public int boatQuantity() {
        if (width == 3 || height == 3)
            return 1;
        else if ((width > 3 && width <= 5) || (height > 3 && height <= 5))
            return 2;
        else if ((width > 5 && width <= 7) || (height > 5 && height <= 7))
            return 3;
        else if ((width > 7 && width <= 9) || (height > 7 && height <= 9))
            return 4;
        else if ((width > 9 && width <= 12) || (height > 9 && height <= 12))
            return 6;
        else return 0;
    }

    public void generateBoats() {
        for (int i = 0; i < boatQuantity(); i++) {

            int horizontal;
            int vertical;
            if ((Math.floor(Math.random() * 10) % 10) % 2 == 0) {
                horizontal = 1;
                vertical = 3;
            } else {
                horizontal = 3;
                vertical = 1;
            }
            int randomHeight = (int) Math.floor((Math.random() * 100) % 12);
            int randomWidth = (int) Math.floor((Math.random() * 100) % 12);

            while (randomHeight > height - vertical || randomWidth > width - horizontal) {
                randomHeight = (int) Math.floor((Math.random() * 100) % 12);
                randomWidth = (int) Math.floor((Math.random() * 100) % 12);
            }

            if (horizontal == 3) {
                while ((randomHeight > height - vertical || randomWidth > width - horizontal) ||
                        (board[randomHeight][randomWidth] == 1 ||
                                board[randomHeight][randomWidth + 1] == 1 ||
                                board[randomHeight][randomWidth + 2] == 1)) {
                    randomHeight = (int) Math.floor(Math.random() * 100) % 12;
                    randomWidth = (int) Math.floor(Math.random() * 100) % 12;
                }
                createBoat(randomHeight, randomWidth, randomHeight, randomWidth + 1, randomHeight, randomWidth + 2);
                board[randomHeight][randomWidth] = 1;
                board[randomHeight][randomWidth + 1] = 1;
                board[randomHeight][randomWidth + 2] = 1;

            } else {
                while ((randomHeight > height - vertical || randomWidth > width - horizontal) ||
                        (board[randomHeight][randomWidth] == 1 ||
                                board[randomHeight + 1][randomWidth] == 1 ||
                                board[randomHeight + 2][randomWidth] == 1)) {
                    randomHeight = (int) Math.floor(Math.random() * 100) % 12;
                    randomWidth = (int) Math.floor(Math.random() * 100) % 12;
                }
                createBoat(randomHeight, randomWidth, randomHeight + 1, randomWidth, randomHeight + 2, randomWidth);
                board[randomHeight][randomWidth] = 1;
                board[randomHeight + 1][randomWidth] = 1;
                board[randomHeight + 2][randomWidth] = 1;

            }
        }
    }
}
