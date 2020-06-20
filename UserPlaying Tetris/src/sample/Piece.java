package sample;

import java.util.Random;
import java.awt.Point;

/**
 *
 * For the logic of this game; a '-2' in the field array represents an empty coordinate.
 * -1 represents the co-ordinates of the current piece.
 * The methods in this class should only be called by the current piece who is already represented,
 * on the field parsed, by a '-1'.
 *
 */
public class Piece {

    final static Point pieces [][][] =
            {
                    // P-Piece.
                    {
                            { new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,0) },//rotation 1
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1), new Point(1,2) },//rotation 2
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,0), new Point(2,1) },//rotation 3
                            { new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2) } //rotation 4
                    },

                    // X-Piece.
                    {
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1) },
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1) },
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1) },
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1) }
                    },

                    // F-Piece.
                    {
                            { new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1), new Point(2,1) },
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,2) },
                            { new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,0), new Point(2,1) },
                            { new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1) }
                    },
                    // V-Piece.
                    {
                            { new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1), new Point(2,2) },
                            { new Point(0,2), new Point(1,2), new Point(2,0), new Point(2,1), new Point(2,2) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2), new Point(2,2) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0), new Point(2,0) }
                    },
                    // W-Piece.
                    {
                            { new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1), new Point(2,2) },
                            { new Point(0,2), new Point(1,1), new Point(1,2), new Point(2,0), new Point(2,1) },
                            { new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,2) },
                            { new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1), new Point(2,0) }
                    },
                    // Y-Piece.
                    {
                            { new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1), new Point(3,1) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,1) },
                            { new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1), new Point(3,0) },
                            { new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3) }
                    },
                    // I-Piece.
                    {
                            { new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(4,0) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(0,4) },
                            { new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(4,0) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(0,4) }
                    },
                    // T-Piece.
                    {
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1), new Point(2,1) },
                            { new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,0) },
                            { new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1), new Point(2,2) },
                            { new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,2) }
                    },
                    // Z-Piece.
                    {
                            { new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,2) },
                            { new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,0) },
                            { new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,2) },
                            { new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,0) }
                    },
                    // U-Piece.
                    {
                            { new Point(0,0), new Point(0,2), new Point(1,0), new Point(1,1), new Point(1,2) },
                            { new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,2) },
                            { new Point(0,0), new Point(0,1), new Point(1,0), new Point(2,0), new Point(2,1) }
                    },
                    // N-Piece
                    {
                            { new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2), new Point(1,3) },
                            { new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1), new Point(3,0) },
                            { new Point(0,2), new Point(0,3), new Point(1,0), new Point(1,1), new Point(1,2) },
                            { new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1), new Point(3,1) }
                    },
                    // L-Piece
                    {
                            { new Point(0,3), new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3) },
                            { new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,1) },
                            { new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3), new Point(1,0) },
                            { new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0), new Point(3,1) }
                    }
            };

    static Random number = new Random(System.currentTimeMillis());
    int pieceNumber;
    int rotation;
    int x;
    int y;
    /**
     * TODO: make a boolean that only sets the state of the UI if the piece calling the method is the actual game piece.
     */

    public Piece(){

        pieceNumber = number.nextInt(pieces.length);
        rotation = 0;
        x = 0;
        y = 0;

    }
    /**
     * Rotates piece clockwise if it is safe to do so
     * @param field
     * @return field with the piece rotated
     */
    public int[][] rotateClockwise(int [][] field) {
        if(rotation<3){
            rotation++;
            if(allowMove(field)){
                placePiece(field);
                /**
                 * TODO: using Game.ui1.setState(Game.field); in the methods of Pieces is inefficient because 
                 * we do this even when we are using the methods for the bot.
                 */
                Game.ui1.setState(Game.field);
                return field;
            }else{
                rotation--;
                return field;
            }

        }else{
            rotation=0;
            if(allowMove(field)){
                placePiece(field);
                Game.ui1.setState(Game.field);
                return field;
            }else{
                rotation=3;
                return field;
            }
        }
    }
    /**
     * Rotates the piece anti clockwise if it is safe to do so
     * @param field
     * @return field with piece rotated
     */
    public int[][] rotateAntiClockwise(int [][]field) {
        if(rotation>0){
            rotation--;
            if(allowMove(field)){
                placePiece(field);
                Game.ui1.setState(Game.field);
                return field;
            }else{
                rotation++;
                return field;
            }

        }else{
            rotation=3;
            if(allowMove(field)){
                placePiece(field);
                Game.ui1.setState(Game.field);
                return field;
            }else{
                rotation=0;
                return field;
            }
        }

    }
    /**
     * Moves the piece left if it is safe to do so
     * @param field
     * @return the field with the piece moved on to the left
     */
    public int[][] moveLeft(int [][] field){
        y--;
        if(allowMove(field)){
            placePiece(field);
            Game.ui1.setState(Game.field);
            return field;
        }else{
            y++;
            return field;
        }
    }
    /**
     * Moves the piece one to the right if it is safe to do so
     * @param field
     * @return piece moved one to the right
     */
    public int[][] moveRight(int[][] field) {
        y++;
        if(allowMove(field)){
            placePiece(field);
            Game.ui1.setState(Game.field);
            return field;
        }else{
            y--;
            return field;
        }
    }
    /**
     * Moves the piece down if it is safe to do so.
     * @param field
     * @return the piece moved down one.
     */
    public int[][] downOne(int[][]field){
        x++;
        if(allowMove(field)){
            placePiece(field);
            Game.ui1.setState(Game.field);
            return field;
        }else{
            x--;
            return field;
        }
    }
    /**
     * This is removing gravity the piece goes into freefall.
     * @param field
     * @return field with the piece dropped and the piece is still represented by -1's
     */
    public int[][] dropPiece(int[][]field) {
        while(canMoveDownOne(field)){
            downOne(field);
            Game.ui1.setState(Game.field);
        }
        return field;
    }
    /**
     * Checks if it is safe for the current piece to move down one row.
     * @param field
     * @return true if it is safe for the current piece to move down one row.
     */
    public boolean canMoveDownOne(int[][]field) {
        x++;
        if(allowMove(field)){
            x--;
            return true;
        }else{
            x--;
            return false;
        }

    }
    /**
     * This method look at the Piece's x's and y's
     * then simulate placing the co-ordinates of the piece on the field.
     * If any co-ordinate is placed on top of a number on the field >=0 then return false
     * @param field
     * @return true if the piece can be placed without covering another piece or without going outside the grid
     */
    public boolean allowMove(int[][]field) {
        for(int i = 0; i<pieces[0][0].length; i++){
            int yCo = (int) pieces[pieceNumber][rotation][i].getY();
            int xCo = (int) Math.round(pieces[pieceNumber][rotation][i].getX());
            if(
                    ((y+yCo)>=field.length)||((x+xCo)>=field[0].length)||((y+yCo)<0)||(field[y+yCo][x+xCo] >= 0)||((x+xCo)<0)
            ){
                return false;
            }
        }
        return true;
    }
    /**
     * Takes the picece's co-ordinates and places the piece in its
     * corresponding x and y positions.
     * @param field
     * @return field with piece placed, the piece is still represented by -1's
     */
    public int[][] placePiece(int[][]field){
        field = removeCurrentPiece(field);
        for(int i = 0; i<pieces[0][0].length; i++){
            int yCo = (int) pieces[pieceNumber][rotation][i].getY();
            int xCo = (int) pieces[pieceNumber][rotation][i].getX();
            field[y+yCo][x+xCo] = -1;
        }
        return field;
    }
    /**
     * This method removes the current piece from the field so we are free to put it back
     * with it's new values of x, y or Rotation
     * @param field
     * @return field with no -1's. i.e. field does not contain a current piece.
     */
    public static int[][] removeCurrentPiece(int[][]field) {
        for(int i = 0; i< field.length; i++){
            for(int j =0; j<field[0].length; j++){
                if(field[i][j]==-1){
                    field[i][j] = -2;
                }
            }
        }
        return field;
    }
    /**
     * When the piece can no longer fall we need to fix the current piece to the field before we generate a new current piece.
     * This method looks for all of the -1's on the field, that represent the current piece,
     * and replaces them with the current piece's pieceNumber.
     * @param field
     * @return field with the current piece's -1's replaced with it's pieceNumber.
     */
    public int[][] fixToField(int[][]field) {
        for(int i = 0; i< field.length; i++){
            for(int j =0; j<field[0].length; j++){
                if(field[i][j]==-1){
                    field[i][j] = this.pieceNumber;
                }
            }
        }
        return field;
    }
    public Piece clone() {
        Piece clonedPiece = new Piece();
        clonedPiece.pieceNumber = this.pieceNumber;
        clonedPiece.rotation = this.rotation;
        clonedPiece.x = this.x;
        clonedPiece.y= this.y;
        return clonedPiece;
    }
}