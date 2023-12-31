package battleship;

import java.util.Arrays;

public class Board {
    private char[][] _board;
    private Display _display;

    public Board(Display display){
        _board = new char[10][10];
        for(char[] row : _board){
            Arrays.fill(row, '~');
        }

        _display = display;
    }

    public void placeShip(DeploymentCoordinates deployment){
        for(Coordinate c : deployment.getCoordinates()){ // extract to method on Board for checking if available?
            if(_board[c.getRow() - 1][c.getColumn() - 1] != '~'){
                throw new IllegalArgumentException("Illegal ship deployment"); // TODO: Better error handling;
            }
        }

        for(Coordinate c : deployment.getCoordinates()){
            _board[c.getRow() - 1][c.getColumn() - 1] = 'O';
        }
    }

    public void render() {
        render(false);
    }

    public void renderWithFog() {
        render(true);
    }

    private void render(boolean fogOfWar) {
        _display.columnHeader();
        for(int i = 0; i < _board.length; i++){
            _display.row(i, _board[i], fogOfWar);
        }
    }

    public boolean isValidDeployment(DeploymentCoordinates deployment) {
        for(Coordinate c : deployment.getCoordinates()){
            if(_board[c.getRow() - 1][c.getColumn() - 1] != '~'){
                return false;
            }

            for(Coordinate n : c.getNeighbours()){
                if(_board[n.getRow() - 1][n.getColumn() - 1] != '~'){
                    return false;
                }
            }
        }

        return true;
    }

    public void shoot(Coordinate target) {
        int targetRow = target.getRow() - 1;
        int targetColumn = target.getColumn() - 1;
        if(isHit(target)){
            _board[targetRow][targetColumn] = 'X';
        }else{
            _board[targetRow][targetColumn] = 'M';
        }
    }

    public boolean isHit(Coordinate target) {
        return _board[target.getRow() - 1][target.getColumn() - 1] != '~';
    }

    public boolean isDestroyed(Ship s) {
        for(Coordinate c : s.getCoordinates()){
            if(_board[c.getRow() - 1][c.getColumn() - 1] != 'X'){
                return false;
            }
        }

        return true;
    }
}
