package battleship.state;

import battleship.Board;
import battleship.Display;
import battleship.input.InputHandler;

public interface GameState {
    GameState update(String input, InputHandler handler, Board board);
    void render(Display display, Board board);
}
