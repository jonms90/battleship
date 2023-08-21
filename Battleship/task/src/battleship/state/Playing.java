package battleship.state;

import battleship.Board;
import battleship.Coordinate;
import battleship.Display;
import battleship.input.InputHandler;

import java.util.LinkedList;

public class Playing implements GameState{

    private boolean _gameOver;
    private boolean _lastShotWasHit;
    private boolean _shotsFired;
    private final LinkedList<String> _errors;

    public Playing(){
        _gameOver = false;
        _shotsFired = false;
        _lastShotWasHit = false;
        _errors = new LinkedList<>();
    }

    @Override
    public GameState update(String input, InputHandler handler, Board board) {
        try{
            Coordinate target = handler.getTarget(input);
            _lastShotWasHit = board.isHit(target);
            board.shoot(target);
            _shotsFired = true;
            _gameOver = true; // TODO: Fix win/loss condition.
            return _gameOver ? new GameOver() : null;
        } catch(Exception ex){ // TODO: More specific error handling
            _errors.add("Error! You entered the wrong coordinates! Try again:");
            return null;
        }
    }

    @Override
    public void render(Display display, Board board) {
        if(_errors.isEmpty()){
            if(_shotsFired){
                board.render();
                display.shot(_lastShotWasHit);
            }
        } else{
            display.error(_errors.pop());
        }
    }

    @Override
    public void enter(Display display, Board board) {
        display.gameStart();
        board.renderWithFog();
        display.takeShot();
    }

    @Override
    public void exit(Display display, Board board) {
        if(_shotsFired){
            board.renderWithFog();
            display.shot(_lastShotWasHit);
            board.render();
        }
    }
}
