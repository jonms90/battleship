package battleship.state;

import battleship.Board;
import battleship.Display;
import battleship.input.InputHandler;

public class Playing implements GameState{

    @Override
    public GameState update(String input, InputHandler handler, Board board) {
        return null;
    }

    @Override
    public void render(Display display, Board board) {

    }
}
