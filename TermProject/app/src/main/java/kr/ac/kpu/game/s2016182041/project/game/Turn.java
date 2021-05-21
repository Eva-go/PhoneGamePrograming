package kr.ac.kpu.game.s2016182041.project.game;


import kr.ac.kpu.game.s2016182041.project.R;

public class Turn{
    public final int bitmap;
    public float x,y;
    public boolean turn_end;


    public Turn(boolean turn_end,float x,float y) {
        this.turn_end=turn_end;
        this.x=x;
        this.y=y;
        bitmap= R.mipmap.turn_end;
    }


    public void update() {

    }
}
