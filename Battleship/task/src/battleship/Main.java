package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Battleship game = new Battleship();
        try(Scanner scanner = new Scanner(System.in)){
            while(game.isRunning()){
                String input = scanner.nextLine();
                game.update(input);
                game.render();
            }
        }

    }
}
