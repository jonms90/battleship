package battleship;

import battleship.input.InputHandler;
import battleship.state.GameState;
import battleship.state.Initialization;
import battleship.state.Playing;

public class Battleship {
    private Board _board;
    private boolean _gameOver;
    private GameState _state;
    private Display _display;
    private InputHandler _inputHandler;

    public Battleship(){
        _display = new Display();
        _inputHandler = new InputHandler();
        _board = new Board(_display);
        _gameOver = false;
        _state = new Initialization();
        render();
    }
    public boolean isRunning() {
        return !_gameOver;
    }

    public void update(String input) {
        GameState newState = _state.update(input, _inputHandler, _board);
        if(newState != null){
            _state.render(_display, _board);
            _state = newState;
        }

        if(_state.getClass() == Playing.class){
            _gameOver = true; // TODO: This is not how the game should end.
        }
    }

    public void render() {
        _state.render(_display, _board);
    }
}
