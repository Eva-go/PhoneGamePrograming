package kr.ac.kpu.game.s2016182041.project.game;


import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.project.framework.view.GameView;

public class Selection implements GameObject {
    private static final String TAG = Player.class.getSimpleName();
    public float x,y;
    ArrayList<GameBitmap> selection_list = new ArrayList<>();
    public Selection(float x, float y) {
        this.x=x;
        this.y=y;
        selection_list.add(new GameBitmap(R.mipmap.cost_up));
        selection_list.add(new GameBitmap(R.mipmap.hp_up));
    }

    public void update() {

    }


    public void draw(Canvas canvas){
        for(int i=0;i<selection_list.size();++i){
            selection_list.get(i).draw(canvas,x+(1000*i),y);
        }
    }

}
