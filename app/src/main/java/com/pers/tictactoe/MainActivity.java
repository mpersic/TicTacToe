package com.pers.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Boolean player1turn;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button resetButton;
    private int roundCount;
    private int player1points;
    private int player2points;
    private TextView playerOneTw;
    private TextView playerTwoTw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1turn=true;
        roundCount=0;
        player1points=0;
        player2points=0;

        findViews();

        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1points=0;
                player2points=0;
                updatePointsText();
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player1turn)
        {
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        roundCount++;

        if(checkForWin()){
            if(player1turn){
                player1wins();
            }
            else{
                player2wins();
            }
        }
        else if(roundCount==9){
            draw();
        }
        else{
            player1turn=!player1turn;
        }
    }

    private Boolean checkForWin(){
        return (checkHorizontal()||checkVertical()||checkDiagonal());
    }

    private void player1wins(){
        player1points++;
        Toast.makeText(this,"player 1 wins",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2wins(){
        player2points++;
        Toast.makeText(this,"player 2 wins",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText(){
        playerOneTw.setText("Player 1: "+player1points);
        playerTwoTw.setText("Player 2: "+player2points);
    }
    private void resetBoard(){
        oneButton.setText("");
        twoButton.setText("");
        threeButton.setText("");
        fourButton.setText("");
        fiveButton.setText("");
        sixButton.setText("");
        sevenButton.setText("");
        eightButton.setText("");
        nineButton.setText("");
        roundCount=0;
        player1turn=true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1turn",player1turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount=savedInstanceState.getInt("roundCount");
        player1turn=savedInstanceState.getBoolean("player1turn");
        player1points=savedInstanceState.getInt("player1points");
        player2points=savedInstanceState.getInt("player2points");
    }
    private void findViews(){
        oneButton=findViewById(R.id.one);
        twoButton=findViewById(R.id.two);
        threeButton=findViewById(R.id.three);
        fourButton=findViewById(R.id.four);
        fiveButton=findViewById(R.id.five);
        sixButton=findViewById(R.id.six);
        sevenButton=findViewById(R.id.seven);
        eightButton=findViewById(R.id.eight);
        nineButton=findViewById(R.id.nine);
        resetButton=findViewById(R.id.resetButton);
        playerOneTw=findViewById(R.id.player1Tw);
        playerTwoTw=findViewById(R.id.player2Tw);
    }

    private Boolean checkHorizontal(){
        if((oneButton.getText().toString()==twoButton.getText().toString())
                &&(oneButton.getText().toString()==threeButton.getText().toString())&&(oneButton.getText().toString()=="X"||oneButton.getText().toString()=="O"))
            return true;
        else if((fourButton.getText().toString()==fiveButton.getText().toString())&&(
                fourButton.getText().toString()==sixButton.getText().toString())&&(fourButton.getText().toString()=="X"||fourButton.getText().toString()=="O"))
            return true;
        else if((sevenButton.getText().toString()==eightButton.getText().toString())&&(
                sevenButton.getText().toString()==nineButton.getText().toString())&&(sevenButton.getText().toString()=="X"||sevenButton.getText().toString()=="O"))
            return true;
        else return false;
    }

    private Boolean checkVertical(){
        if((oneButton.getText().toString()==fourButton.getText().toString())&&(
                oneButton.getText().toString()==sevenButton.getText().toString())&&(oneButton.getText().toString()=="X"||oneButton.getText().toString()=="O"))
            return true;
        else if((twoButton.getText().toString()==fiveButton.getText().toString())&&(
                twoButton.getText().toString()==eightButton.getText().toString())&&(twoButton.getText().toString()=="X"||twoButton.getText().toString()=="O"))
            return true;
        else if((threeButton.getText().toString()==sixButton.getText().toString())&&(
                threeButton.getText().toString()==nineButton.getText().toString())&&(threeButton.getText().toString()=="X"||threeButton.getText().toString()=="O"))
            return true;
        else return false;
    }

    private Boolean checkDiagonal(){
        if((oneButton.getText().toString()==fiveButton.getText().toString())&&(
                oneButton.getText().toString()==nineButton.getText().toString())&&(oneButton.getText().toString()=="X"||oneButton.getText().toString()=="O"))
            return true;
        else if((threeButton.getText().toString()==fiveButton.getText().toString())&&(
                threeButton.getText().toString()==sevenButton.getText().toString())&&(threeButton.getText().toString()=="X"||threeButton.getText().toString()=="O"))
            return true;
        else return false;
    }
}