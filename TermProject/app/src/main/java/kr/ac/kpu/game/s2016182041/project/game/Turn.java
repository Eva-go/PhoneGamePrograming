package kr.ac.kpu.game.s2016182041.project.game;


import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;

public class Turn implements GameObject {
    public GameBitmap bitmap;
    public float x,y;
    public boolean turn_end;


    public Turn(float x,float y) {
        this.turn_end=true;
        this.x=x;
        this.y=y;
        this.bitmap=new GameBitmap(R.mipmap.turn_end);
    }


    public void update() {

    }
    public void draw(Canvas canvas){
        bitmap.draw(canvas,x,y);
    }
}
