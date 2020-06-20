package sample;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;

/**
 * ScoreTracker
 *
 */
public class HighScores extends JPanel {

    int[] highScores = new int[5];
    JPanel scoreBoard = new JPanel();;
    JLabel title  = new JLabel("HIGHSCORES:");;
    JLabel first = new JLabel("First " + highScores[0]);
    JLabel second = new JLabel("Second " + highScores[1]);
    JLabel third = new JLabel("Third " + highScores[2]);

    JLabel fourth = new JLabel("Fourth" + highScores[2]);
    JLabel fifth = new JLabel("Fifth " + highScores[2]);

    public HighScores() {
        for (int i = 0; i < highScores.length; i++) {
            setHighScore(0);
        }
        scoreBoard.add(title);
        scoreBoard.add(first);
        scoreBoard.add(second);
        scoreBoard.add(third);
        scoreBoard.add(fourth);

        scoreBoard.add(fifth);

    }
    /**
     * This method sets the game just played score in the high score array if it's greater than the scores already in there
     * Then sets the live score to 0
     * Then the method should repaint the highscore component/panelon the UI.
     */
    public void setHighScore(int score) {
        for(int i = 0; i<highScores.length; i++){
            if(score>highScores[i]){

                for(int j = highScores.length-1; j>i; j-- ){
                    highScores[j]=highScores[j-1];
                }
                highScores[i]=score;
                break;
            }
        }
        first.setText("First " + highScores[0]);
        second.setText("Second " + highScores[1]);
        third.setText("Third " + highScores[2]);

        fourth.setText("Fourth " + highScores[3]);
        fifth.setText("Fifth " + highScores[4]);

    }
    private void setLabel(){
        //scoreBoard.setText("HIGHSCORES:\n 1. " + this.highScores[0] +"\n2. " + this.highScores[1]+
        // "\n 3. " + this.highScores[2] +"\n 4. " + this.highScores[3] +"\n 5. " + this.highScores[4] );
        first = new JLabel("First " + highScores[0]);
        second = new JLabel("Second " + highScores[1]);
        third = new JLabel("Third " + highScores[2]);


    }

    public JPanel getScores(){
        return scoreBoard;
    }

}