package sample;

/**
 * Bot
 */
public class Bot {

    PossibleMove[] decisions = new PossibleMove[20];
    Piece currentPiece;
    Piece nextPiece;
    int[][] currentField;

    public Bot(Piece currentPiece, Piece nextPiece,int[][]field){
        this.currentPiece = currentPiece.clone();
        this.nextPiece = nextPiece.clone();
        this. currentField = copyField(field);
        generatePossibleMoves();
        makeBestMove();
    }
    /**
     * Places the current piece and the next piece in every combination and 
     * orders the possible moves by fitness.
     */
    public void generatePossibleMoves() {
        for(int i = 0; i<decisions.length; i++){
            int rotation=0;
            int movesRight;
            if(i<=5){
                rotation = 0;
                movesRight = i%5;
            }else if(i<=10){
                rotation=1;
                movesRight = i%5;
            }else if(i<=15){
                rotation = 2;
                movesRight = i%5;
            }else{
                rotation = 3;
                movesRight = i%5;
            }
            decisions[i] = new PossibleMove(rotation, movesRight, currentField, currentPiece, nextPiece);
        }
        HeapSort.sort(decisions);

    }
    public void makeBestMove() {
        try{
            Thread.sleep(100);
        }catch (Exception e) {
        }
        for(int i=0; i<decisions[0].nRotations; i++){
            Game.currentPiece.rotateClockwise(Game.field);
            try{
                Thread.sleep(100);
            }catch (Exception e) {
            }
        }
        for(int i=0; i<decisions[0].mMoveRight; i++){
            Game.currentPiece.moveRight(Game.field);
            try{
                Thread.sleep(100);
            }catch (Exception e) {
            }
        }
        Game.currentPiece.dropPiece(Game.field);
    }
    public static int[][] copyField(int[][]field){
        int[][] copiedField = new int[field.length][field[0].length];
        for(int i=0; i<field.length; i++){
            for(int j=0; j<field[0].length; j++){
                copiedField[i][j] = field[i][j];
            }
        }
        return copiedField;
    }

}