package kr.ac.kpu.game.s2016182041.project.game;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;

public class Cost implements GameObject {
    public GameBitmap bitmap;
    public float x,y;
    public float cost_count;
    private float cost;


    public Cost(float x, float y,float cost_count) {
       this.x=x;
       this.y=y;
       this.cost_count=cost_count;
       this.bitmap = new GameBitmap(R.mipmap.cost_image);
    }

    public void update() {

    }

    public void draw(Canvas canvas){
        for(int i=0; i<cost_count;++i){
            bitmap.draw(canvas,x,y+(100*i));
        }
    }

}
