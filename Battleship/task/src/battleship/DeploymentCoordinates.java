package battleship;

import java.util.ArrayList;

public class DeploymentCoordinates {
    private final Coordinate _start;
    private final Coordinate _end;
    private final int _length;

    public DeploymentCoordinates(Coordinate start, Coordinate end){
        if(start.equals(end)){
            throw new IllegalArgumentException("Start and end coordinates can't be the same for deployment.");
        }

        if(!isVertical(start, end) && !isHorizontal(start, end)){
            throw new IllegalArgumentException("Start and end coordinates can't be diagonal.");
        }

        _start = getStart(start, end);
        _end = getEnd(start, end);

        _length = initializeLength();
    }

    private int initializeLength() {
        if(isHorizontal()){
            return 1 + Math.abs(_start.getColumn() - _end.getColumn());
        }
        else{
            return 1 + Math.abs(_start.getRow() - _end.getRow());
        }
    }

    public boolean isVertical(){
        return _start.getColumn() == _end.getColumn();
    }

    private boolean isVertical(Coordinate start, Coordinate end){
        return start.getColumn() == end.getColumn();
    }

    public boolean isHorizontal(){
        return _start.getRow() == _end.getRow();
    }

    private boolean isHorizontal(Coordinate start, Coordinate end){
        return start.getRow() == end.getRow();
    }

    public Coordinate getStart(){
        return _start;
    }

    private Coordinate getStart(Coordinate start, Coordinate end){
        if(isHorizontal(start,end)){
            return start.getColumn() < end.getColumn() ? start : end;
        }
        else{
            return start.getRow() < end.getRow() ? start : end;
        }
    }

    public Coordinate getEnd(){
        return _end;
    }

    private Coordinate getEnd(Coordinate start, Coordinate end){
        if(isHorizontal(start,end)){
            return end.getColumn() > start.getColumn() ? end : start;
        }
        else{
            return end.getRow() > start.getRow() ? end : start;
        }
    }

    public int getLength(){
        return _length;
    }

    public ArrayList<Coordinate> getCoordinates(){
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>(_length);
        Coordinate current = _start;
        coordinates.add(current);
        boolean isHorizontal = isHorizontal();
        for(int i = 1; i < _length; i++){
            if(isHorizontal){
                current = new Coordinate(current.getRow(), current.getColumn() + 1);
            }else{
                current = new Coordinate(current.getRow() + 1, current.getColumn());
            }
            coordinates.add(current);
        }

        return coordinates;
    }
}
