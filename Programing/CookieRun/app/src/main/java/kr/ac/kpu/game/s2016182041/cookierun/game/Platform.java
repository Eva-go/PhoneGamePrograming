package kr.ac.kpu.game.s2016182041.cookierun.game;

import kr.ac.kpu.game.s2016182041.cookierun.R;
import kr.ac.kpu.game.s2016182041.cookierun.framework.object.imageObject;
import kr.ac.kpu.game.s2016182041.cookierun.framework.view.GameView;

public class Platform extends imageObject {
   public enum Type{
       T_10x2,T_2x2,T_3x1
   }
   public Platform (Type type,float x, float y){
//       int resId = type==??
       super(R.mipmap.cookierun_platform_480x48,x,y);
       final float UNIT = 40* GameView.MULTIPLIER;
       float w = UNIT * 10;
       float h = UNIT * 2;
       dstRect.set(x,y,x+w,y+h);
   }
}
