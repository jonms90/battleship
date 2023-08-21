package battleship;

public class Display {
    public void columnHeader() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
    }

    public void row(int i, char[] chars) {
        System.out.printf("%c ", getRowName(i));
        for(char c : chars){
            System.out.printf("%c ", c);
        }
        System.out.println();
    }

    private char getRowName(int i) {
        return (char)(i + 65); // 65 = A
    }

    public void deployment(Ship nextShip) {
        System.out.printf("%nEnter the coordinates of the %s (%d cells):%n", nextShip.getName(), nextShip.getLength());
    }

    public void error(String error){
        System.out.println(error);
    }
}
