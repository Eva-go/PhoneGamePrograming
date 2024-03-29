package kr.ac.kpu.game.s2016182041.pairgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int [] buttonIds={
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33,
            R.id.card_40, R.id.card_41, R.id.card_42, R.id.card_43,
    };

    private int[] cards={
            R.mipmap.card_1,R.mipmap.card_1,R.mipmap.card_2,R.mipmap.card_2,
            R.mipmap.card_3,R.mipmap.card_3,R.mipmap.card_4,R.mipmap.card_4,
            R.mipmap.card_5,R.mipmap.card_5,R.mipmap.card_6,R.mipmap.card_6,
            R.mipmap.card_7,R.mipmap.card_7,R.mipmap.card_8,R.mipmap.card_8,
            R.mipmap.card_9,R.mipmap.card_9,R.mipmap.card_10,R.mipmap.card_10,
    };
    private ImageButton prevButton;
    private int visibleCardCount;
    private TextView scoreTextView;

    public void setScore(int score) {
        this.score = score;
        scoreTextView.setText("Flips: "+score);
    }

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTextView = findViewById(R.id.scoreTextView);
        Log.d(TAG,"onCreate");
        startGame();

    }

    public void onBtnCard(View view) {
        if(view==prevButton){
            scoreTextView.getResources().getColor(R.color.purple_700);
            return;
        }
        scoreTextView.getResources().getColor(R.color.gray);
        int prevCard=0;
        if(prevButton!=null){
            prevButton.setImageResource(R.mipmap.card_back);
            prevCard=(Integer)prevButton.getTag();

        }
        int buttonIndex=getButtonIndex((view.getId()));
        Log.d(TAG,"onBtnCard() has been called ID="+view.getId()+"buttonIndex="+buttonIndex);

        int card =cards[buttonIndex];
        ImageButton imageButton =(ImageButton)view;
        imageButton.setImageResource(card);

        if(card==prevCard){
            imageButton.setVisibility(View.INVISIBLE);
            prevButton.setVisibility(View.INVISIBLE);
            prevButton=null;
            visibleCardCount -= 2;
            if(visibleCardCount==0){
                akRestartGame();
            }
            return;
        }
        if(prevButton != null){
            setScore(score+1);
        }

        prevButton = imageButton;
    }



    private int getButtonIndex(int resId){
        for(int i=0; i<buttonIds.length;i++){
            if(buttonIds[i]==resId){
                return i;
            }
        }
       return -1;
    }

    public void onBtnRestart(View view) {
        akRestartGame();
    }

    private void akRestartGame(){
        // 알람 다이어그램을 사용해서 팜업질문을 할 수 있다.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.restart_dialog_title);
        builder.setMessage(R.string.restart_dialog_message);
        builder.setPositiveButton(R.string.common_Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startGame();
            }
        });

        builder.setNegativeButton(R.string.Common_No, null);
        AlertDialog alert=builder.create();
        alert.show();
    }
    private void startGame() {
        Random random=new Random();
        for(int i=0; i<cards.length; i++){
            int ri =random.nextInt(cards.length);
            int t= cards[i];
            cards[i]=cards[ri];
            cards[ri]=t;
        }
        for(int i=0; i<buttonIds.length; i++){
            ImageButton b = findViewById(buttonIds[i]);
            b.setTag(cards[i]);
            b.setVisibility(View.VISIBLE);
            b.setImageResource(R.mipmap.card_back);

        }
        prevButton=null;
        visibleCardCount= cards.length;
        setScore(0);
    }
}