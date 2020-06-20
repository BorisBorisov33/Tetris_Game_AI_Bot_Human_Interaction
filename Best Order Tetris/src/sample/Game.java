package sample;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.event.*;

/**
 * Game
 */
public class Game extends JPanel {

    public static HighScores gameScores;
    public static int[][] field = new int[5][15];
    public static int [][] nextPieceField = new int[5][5];
    public static Piece currentPiece;
    public static Piece nextPiece;
    private static int liveScore =0;
    public static JLabel liveScoreLabel = new JLabel();
    public static UI ui1;
    public static UI nextPieceUI;
    private JPanel mainPanel;
    private JFrame f;
    //public static int[] bestOrderArray = {0, 1, 2, 6, 3, 8, 10, 9, 5, 11, 4, 7};//The best for the bot
    public static int[] bestOrderArray = {11, 9, 6, 1, 5, 4, 2, 7, 10, 8, 0, 3};
    /*public static int[] bestOrderArray = {11, 9, 5, 8, 7, 2, 1, 0, 10, 6, 4, 3}
     *
     */
    //public static int[] arr = {6, 3, 7, 11, 4, 1, 5, 8, 10, 2, 0, 9};
    public static int pieceIndex;

    /**
     * The Constructor starts the game
     */
    public Game(){
        gameScores = new HighScores();
        nextPieceUI = new UI(nextPieceField.length, nextPieceField[0].length,25);
        pieceIndex = 0;
        nextPiece = new Piece(bestOrderArray[pieceIndex]);
        pieceIndex++;


        ui1 = new UI(field.length, field[0].length, 25);
        ui1.setState(field);

        mainPanel = new JPanel();
        mainPanel.add(ui1);
        mainPanel.add(nextPieceUI);
        mainPanel.add(gameScores.getScores());
        mainPanel.add(liveScoreLabel);


        f = new JFrame("Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(350, 500);
        f.setVisible(true);
        f.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        field = currentPiece.rotateClockwise(field);
                        break;
                    case KeyEvent.VK_DOWN:
                        field =currentPiece.rotateAntiClockwise(field);
                        break;
                    case KeyEvent.VK_LEFT:
                        field = currentPiece.moveLeft(field);
                        break;
                    case KeyEvent.VK_RIGHT:
                        field = currentPiece.moveRight(field);
                        break;
                    case KeyEvent.VK_SPACE:
                        field = currentPiece.dropPiece(field);
                        break;
                }
            }
        });
        f.add(mainPanel);
        playGame();

    }

    /**
     * This method is the core of the game.
     * @return the score of the game played
     */
    public void playGame() {

        liveScore = 0;
        liveScoreLabel.setText("Your Current Score is: " + liveScore);
        field = setField(field);
        currentPiece = nextPiece;
        nextPiece = new Piece(bestOrderArray[pieceIndex]);
        pieceIndex++;
        if (pieceIndex>11) {
            pieceIndex = 0;
        }
        nextPieceField = setField(nextPieceField);
        nextPiece.placePiece(nextPieceField);
        nextPiece.fixToField(nextPieceField);
        nextPieceUI.setState(nextPieceField);
        while(currentPiece.allowMove(field)){

            field = currentPiece.placePiece(field);
            ui1.setState(field);
            //The following line of code implements the bot, the bot is free to interact with the game.
            Bot decideMove = new Bot(currentPiece, nextPiece, field);
            while(currentPiece.canMoveDownOne(field)){

                field = currentPiece.downOne(field);
                ui1.setState(field);
            }

            field = currentPiece.fixToField(field);
            liveScore += getScore(field);
            liveScoreLabel.setText("Your Current Score is: " + liveScore);

            field = deleteRows(field);
            currentPiece = nextPiece;

            nextPiece  = new Piece(bestOrderArray[pieceIndex]);
            nextPieceField = setField(nextPieceField);
            nextPiece.placePiece(nextPieceField);
            nextPiece.fixToField(nextPieceField);
            nextPieceUI.setState(nextPieceField);
            pieceIndex++;
            if (pieceIndex>11) {
                pieceIndex = 0;
            }

        }
        currentPiece.placePiece(field);
        currentPiece.fixToField(field);
        ui1.setState(field);
        gameScores.setHighScore(liveScore);

        try{Thread.sleep(1000);
        }catch (Exception e) { }//sleep for a bit

        playGame();


    }
    /**
     * Sets an empty field. -2 represents and empty field. The field is a static instance varibale of the class Game.
     */
    public static int[][] setField(int[][]field) {
        for(int i= 0; i<field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                field[i][j] = -2;
            }
        }
        return field;
    }
    /**
     * Checks how many full rows are in this field and adds a point to the score gained for every full row
     * @param field
     * @return The score gained from the field parsed
     */
    public static int getScore(int[][] field) {
        int scoreGained = 0;
        for(int i = 0; i< field[0].length; i++){
            boolean fullRow = true;
            for(int j = 0 ; j< field.length; j++){
                if(field[j][i]<0){
                    fullRow = false;
                }
            }
            if(fullRow){
                scoreGained++;
            }
        }
        return scoreGained;
    }

    /**
     * Deletes all the full rows and moves the rows above down one for every row removed.
     * @param field
     * @return field with full rows deleted
     */
    public static int[][] deleteRows(int[][]field) {

        for(int i = 0; i< field[0].length; i++){
            boolean canDelete = true;
            for(int j = 0 ; j< field.length; j++){
                if(field[j][i]<0){
                    canDelete = false;
                }
            }
            if(canDelete&&i>0){
                int x = i-1;
                while(x>0){
                    for(int j = 0; j<field.length; j++){
                        field[j][x+1] = field[j][x];
                    }
                    x--;
                }
                for(int j = 0; j<field.length; j++){
                    field[j][0] = -2;
                }
            }

        }
        return field;
    }
    public static void main(String[] args) {
        Game game = new Game();
    }
}