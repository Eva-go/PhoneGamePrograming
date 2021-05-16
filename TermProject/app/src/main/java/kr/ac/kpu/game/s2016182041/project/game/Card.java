package kr.ac.kpu.game.s2016182041.project.game;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.object.ImageObject;
import kr.ac.kpu.game.s2016182041.project.framework.view.GameView;

public class Card {
    ArrayList<Integer> cardList = new ArrayList<>();
    GameBitmap bitmap;
    public void cardAdd(){
        cardList.add(R.mipmap.attack);
        cardList.add(R.mipmap.all_attack);
        cardList.add(R.mipmap.shield);
    }

    public void cardDraw(){
//        for(int i=0; i<3; ++i){
//            int dValue = (int) Math.random();
//        }

    }


}
