
package Messages;

import chess_game.Pieces.Coordinate;


public class MovementMessage implements java.io.Serializable{
    public boolean isPieceKilled;
    public Coordinate destinationCoordinate;
    public Coordinate currentCoordinate;
}
