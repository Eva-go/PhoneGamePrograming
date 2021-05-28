package kr.ac.kpu.game.s2016182041.project.game;


import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;

public class Card implements GameObject {
    public float x,y;
    public float card_count;
    private Player player;
    Random rnd = new Random();
    ArrayList<GameBitmap> card_list = new ArrayList<>();
    ArrayList<GameBitmap> card = new ArrayList<>();
    public enum State {
        non,attack, shield, all_attack
    }
    public State state = State.non;
    public Card(float x, float y,float card_count) {
        this.x=x;
        this.y=y;
        this.card_count=card_count;
        card_list.add(new GameBitmap(R.mipmap.shield));
        card_list.add(new GameBitmap(R.mipmap.all_attack));
        card_list.add(new GameBitmap(R.mipmap.attack));

    }

    public void update() {

    }

    public void card_draw(){
        for(int i=0;i<card_count;++i) {
            card.add(card_list.get(rnd.nextInt(3)));
        }
    }
    public void card_redraw(){
        for(int i=0;i<card_count;++i){
            card.set(i,card_list.get(rnd.nextInt(3)));
        }
    }
    public void card_remove(int card_number){
        card.set(card_number,new GameBitmap(R.mipmap.non));
    }
     /*for(int i=0; i<5; ++i) {
        Random rnd = new Random();
        add(MainGame.Layer.card, new ImageObject(cardList.get(rnd.nextInt(3)),300+(270*i),200));
    }*/
    public void card_states(State state){
        if(state == State.shield){

            //Log.d("종류", "state"+state);
        }
        else if(state == State.all_attack){

            //Log.d("종류", "state"+state);
        }
        else if(state == State.attack){

            //Log.d("종류", "state"+state);
        }
    }

    public void draw(Canvas canvas){
        for(int i=0;i<card_count;++i){
            card.get(i).draw(canvas,x+(270*i),y);
        }
    }

}
