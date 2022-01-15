package com.example.zerocrossgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //boolean to check if the game is over if it is over then we will not  allow to place more counters
    boolean gameover = false;
    String winnerName = "";
    int noPlaced = 0;
    //Function to check the winner if the games if there is any after a move completed
    //false = not yet
    //true = winner is decided and the winner is who played the last chance so reverse of currP will give winner
    public boolean winner(int crow,int ccol,boolean currPlaced) {
        boolean winnero = true;
        if (currPlaced) {
            // we will check if player 1 wins by this move or not
            //
            for (int i = ccol; i < 3; i++) {
                if (checkedbywho[crow][i] != 1) {
                    winnero = false;
                    break;
                }
            }
            for (int i = ccol; i >= 0; i--) {
                if (checkedbywho[crow][i] != 1) {
                    winnero = false;
                    break;
                }
            }
            if (winnero)
                return winnero;
            winnero = true;
            for (int i = crow; i < 3; i++) {
                if (checkedbywho[i][ccol] != 1) {
                    winnero = false;
                    break;
                }

            }
            for (int i = crow; i >= 0; i--) {
                if (checkedbywho[i][ccol] != 1) {
                    winnero = false;
                    break;
                }
            }
            if (winnero)
                return winnero;

            if(crow == 0 && ccol == 0){
                winnero = true;
                for(int i = crow,j = ccol;j < 3 && i < 3; i++,j++){
                    if(checkedbywho[i][j] != 1){
                        winnero = false;
                    }
                }
            }

             else if(crow == 2 && ccol == 0){
                winnero = true;
                for(int i = crow, j = ccol; i >= 0 && j < 3; i--,j++){
                    if(checkedbywho[i][j] != 1)
                        winnero = false;
                }

            }
            else if(crow == 2 && ccol == 2) {
                winnero = true;
                for (int i = crow, j = ccol; i >= 0 && j >= 0; i--, j--) {
                    if (checkedbywho[i][j] != 1)
                        winnero = false;
                }
            }
            else if(crow == 0 && ccol == 2){
                winnero = true;
                for(int i = crow,j = ccol; i < 3 && j >= 0; i++,j--){
                    if (checkedbywho[i][j] != 1)
                        winnero = false;
                }
            }

            else if(crow == 1 && ccol == 1){
                winnero = true;
                if(checkedbywho[crow - 1][ccol + 1] != 1)
                    winnero = false;
                if(checkedbywho[crow+1][ccol-1] != 1)
                    winnero = false;
                if (winnero)
                    return winnero;

                winnero = true;
                if(checkedbywho[crow-1][ccol-1] != 1){
                    winnero = false;
                }
                if(checkedbywho[crow+1][ccol+1] != 1)
                    winnero = false;
            }

            return winnero;
        } else {
            // we will check if player 2 wins by this move or not
            //
            for (int i = ccol; i < 3; i++) {
                if (checkedbywho[crow][i] != 2) {
                    winnero = false;
                    break;
                }
            }
            for (int i = ccol; i >= 0; i--) {
                if (checkedbywho[crow][i] != 2) {
                    winnero = false;
                    break;
                }
            }
            if (winnero)
                return winnero;
            winnero = true;
            for (int i = crow; i < 3; i++) {
                if (checkedbywho[i][ccol] != 2) {
                    winnero = false;
                    break;
                }

            }
            for (int i = crow; i >= 0; i--) {
                if (checkedbywho[i][ccol] != 2) {
                    winnero = false;
                    break;
                }
            }
            if (winnero)
                return winnero;

            if(crow == 0 && ccol == 0){
                winnero = true;
                for(int i = crow,j = ccol;j < 3 && i < 3; i++,j++){
                    if(checkedbywho[i][j] != 2){
                        winnero = false;
                    }
                }
            }

            else if(crow == 0 && ccol == 0){
                winnero = true;
                for(int i = crow,j = ccol;j < 3 && i < 3; i++,j++){
                    if(checkedbywho[i][j] != 2){
                        winnero = false;
                        break;
                    }
                }
            }

            else if(crow == 2 && ccol == 0){
                winnero = true;
                for(int i = crow, j = ccol; i >= 0 && j < 3; i--,j++){
                    if(checkedbywho[i][j] != 2) {
                        winnero = false;
                        break;
                    }
                }

            }
            else if(crow == 2 && ccol == 2) {
                for (int i = crow, j = ccol; i >= 0 && j >= 0; i--, j--) {
                    if(checkedbywho[i][j] != 2) {
                        winnero = false;
                        break;
                    }
                }
            }
            else if(crow == 0 && ccol == 2){
                winnero = true;
                for(int i = crow,j = ccol; i < 3 && j >= 0; i++,j--){
                    if(checkedbywho[i][j] != 2) {
                        winnero = false;
                        break;
                    }
                }
            }

            else if(crow == 1 && ccol == 1){
                winnero = true;
                if(checkedbywho[crow - 1][ccol + 1] != 2)
                    winnero = false;
                if(checkedbywho[crow+1][ccol-1] != 2)
                    winnero = false;
                if (winnero)
                    return winnero;

                winnero = true;
                if(checkedbywho[crow-1][ccol-1] != 2){
                    winnero = false;
                }
                if(checkedbywho[crow+1][ccol+1] != 2)
                    winnero = false;
            }

            return winnero;

        }


    }
    boolean currP = true; //true = player1 turn and false = player2 turn
    //Take player 1 as Yellow dot
    //Take player 2 as red dot
    //Array to check visited cells
    int[][] checkedbywho = new int[3][3];
    //1 for yellow
    //2 for red
    //variable to check if all slots are filled or not
    public void putCounter(View view){
        if(!gameover) {
            //Firstly take image outside of screen so that you can get back by animation.
            ImageView img = (ImageView) view;
            String toget =img.getTag().toString();
            int row = Integer.parseInt(String.valueOf(toget.charAt(1)));
            int col = Integer.parseInt(String.valueOf(toget.charAt(2)));
            if(checkedbywho[row][col] != 0) {
                Toast.makeText(this, "That cell is already selected", Toast.LENGTH_SHORT).show();
                return;
            }
            img.setTranslationY(-1500);

                if (currP) {
                img.setImageResource(R.drawable.yellow);
                img.animate().alpha(1).translationYBy(1500).setDuration(300);
                checkedbywho[row][col] = 1;
                if(winner(row,col,currP)){
                    gameover = true;
                    winnerName = "Player yellow is the winner!!!";
                }
                currP = false;
            } else {
                img.setImageResource(R.drawable.red);
                checkedbywho[row][col] = 2;
                img.animate().alpha(1).translationYBy(1500).setDuration(300);
                if(winner(row,col,currP)){
                    gameover = true;
                    winnerName = "Player red is the winner!!!";
                }
                currP = true;
            }
            noPlaced++;
            if(noPlaced == 9 && !gameover) {
                gameover = true;
                winnerName = "The match is a draw. \nHow about one more game?";
            }
            if(gameover){
                Button b = (Button)findViewById(R.id.button);
                b.setAlpha(1);
                TextView res = (TextView)findViewById(R.id.result);
                res.setText(winnerName);
            }
        }else
            return;
    }
    public void playAgain(View view){
        Button bu =  (Button)view;
        bu.setAlpha(0);
        ImageView img1 = (ImageView)findViewById(R.id.i00);
        ImageView img2 = (ImageView)findViewById(R.id.i01);
        ImageView img3 = (ImageView)findViewById(R.id.i02);
        ImageView img4 = (ImageView)findViewById(R.id.i10);
        ImageView img5 = (ImageView)findViewById(R.id.i11);
        ImageView img6 = (ImageView)findViewById(R.id.i12);
        ImageView img7 = (ImageView)findViewById(R.id.i20);
        ImageView img8 = (ImageView)findViewById(R.id.i21);
        ImageView img9 = (ImageView)findViewById(R.id.i22);
        img1.animate().alpha(0).setDuration(200);
        img2.animate().alpha(0).setDuration(200);
        img3.animate().alpha(0).setDuration(200);
        img4.animate().alpha(0).setDuration(200);
        img5.animate().alpha(0).setDuration(200);
        img6.animate().alpha(0).setDuration(200);
        img7.animate().alpha(0).setDuration(200);
        img8.animate().alpha(0).setDuration(200);
        img9.animate().alpha(0).setDuration(200);
        noPlaced = 0;
        TextView tv = (TextView)findViewById(R.id.result);
        tv.setText("");
        for(int i = 0; i < 3;i++){
            for(int j = 0; j  <3; j++){
                checkedbywho[i][j] = 0;
            }
        }
        gameover = false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}