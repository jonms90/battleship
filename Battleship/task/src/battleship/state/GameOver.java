package battleship.state;

import battleship.Board;
import battleship.Display;
import battleship.input.InputHandler;

public class GameOver implements GameState{
    @Override
    public GameState update(String input, InputHandler handler, Board board) {
        return null;
    }

    @Override
    public void render(Display display, Board board) {

    }

    @Override
    public void enter(Display display, Board board) {

    }

    @Override
    public void exit(Display display, Board board) {

    }
}
