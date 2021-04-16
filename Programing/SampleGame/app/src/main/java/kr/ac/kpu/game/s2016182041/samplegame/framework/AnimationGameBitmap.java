package kr.ac.kpu.game.s2016182041.samplegame.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.HashMap;

import kr.ac.kpu.game.s2016182041.samplegame.ui.view.GameView;

public class AnimationGameBitmap {
    private static final int PIXEL_MULTIPLIER = 4;
    private Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final long createdOn;
    private int frameIndex;
    private final float framesPerSecond;
    private final int frameCount;
    private HashMap<Integer,Bitmap> bitmaps =new HashMap<Integer, Bitmap>();
    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount){
        Resources res = GameView.view.getResources();
        BitmapFactory.Options opts =new BitmapFactory.Options();
        opts.inScaled =false;
        bitmap = bitmaps.get(resId); //한번이라도 불리면 키값 이용 다시사용
        if(bitmap == null){
            bitmap = BitmapFactory.decodeResource(res,resId,opts);
            bitmaps.put(resId,bitmap);
        }
        imageWidth = bitmap.getWidth();
        imageHeight= bitmap.getHeight();
        if(frameCount==0){
            frameCount=imageWidth/imageHeight ;
        }
        frameWidth = imageWidth/frameCount;
        this.framesPerSecond = framesPerSecond;
        this.frameCount =frameCount;
        createdOn = System.currentTimeMillis();
        frameIndex = 0;
    }
//    public void update(){
//        int elapsed =(int)(System.currentTimeMillis()- createdOn);
//        frameIndex=Math.round(elapsed*0.001f*framesPerSecond)%24;
//    }
    public void draw(Canvas canvas,float x, float y){
        int elapsed =(int)(System.currentTimeMillis()- createdOn);
        frameIndex=Math.round(elapsed*0.001f*framesPerSecond)%frameCount;

        int fw = frameWidth;
        int h = imageHeight;
        int hw = fw / 2 * 4;
        int hh = h / 2 * 4;
        Rect src = new Rect(fw*frameIndex,0,fw*frameIndex+fw,h);
        RectF dst = new RectF(x-hw,y-hh,x+hw,y+hh);
        canvas.drawBitmap(bitmap,src,dst,null);
    }
    public int getWidth(){
        return frameWidth * PIXEL_MULTIPLIER;
    }
    public int getHeight(){
        return imageHeight * PIXEL_MULTIPLIER;
    }
}
