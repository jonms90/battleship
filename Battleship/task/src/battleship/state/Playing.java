package battleship.state;

import battleship.Board;
import battleship.Coordinate;
import battleship.Display;
import battleship.Ship;
import battleship.input.InputHandler;

import java.util.LinkedList;

public class Playing implements GameState{

    private boolean _gameOver;
    private boolean _lastShotWasHit;
    private boolean _shotsFired;
    private boolean _shipDestroyed;
    private final LinkedList<String> _errors;
    private final LinkedList<Ship> _fleet;
    private final LinkedList<Ship> _destroyedFleet;

    public Playing(LinkedList<Ship> fleet){
        _gameOver = false;
        _shotsFired = false;
        _lastShotWasHit = false;
        _shipDestroyed = false;
        _errors = new LinkedList<>();
        _fleet = fleet;
        _destroyedFleet = new LinkedList<>();
    }

    @Override
    public GameState update(String input, InputHandler handler, Board board) {
        try{
            _shipDestroyed = false;
            Coordinate target = handler.getTarget(input);
            _lastShotWasHit = board.isHit(target);
            board.shoot(target);
            _shotsFired = true;

            for(Ship s : _fleet){
                if(s.getCoordinates().contains(target)){
                    if(board.isDestroyed(s)){
                        _shipDestroyed = true;
                        if(!_destroyedFleet.contains(s)){
                            _destroyedFleet.add(s);
                        }
                    }
                }
            }

            if(_destroyedFleet.size() == _fleet.size()){
                _gameOver = true;
            }

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
                if(_shipDestroyed){
                    display.destroyed();
                }else{
                    display.shot(_lastShotWasHit);
                }

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

    }
}
