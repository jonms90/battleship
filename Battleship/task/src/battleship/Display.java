package battleship;

public class Display {
    public void columnHeader() {
        System.out.println();
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
    }

    public void row(int i, char[] chars, boolean fogOfWar) {
        System.out.printf("%c ", getRowName(i));
        for(char c : chars){
            if(c == 'O' && fogOfWar){
                System.out.printf("%c ", '~');
            }
            else{
                System.out.printf("%c ", c);
            }
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

    public void gameStart() {
        System.out.println();
        System.out.println("The game starts!");
    }

    public void takeShot() {
        System.out.println();
        System.out.println("Take a shot!");
    }

    public void shot(boolean lastShotWasHit) {
        System.out.println();
        System.out.println(lastShotWasHit ? "You hit a ship!" : "You missed!");
    }
}
