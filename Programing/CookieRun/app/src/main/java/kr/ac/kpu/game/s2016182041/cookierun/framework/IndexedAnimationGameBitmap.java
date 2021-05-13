package kr.ac.kpu.game.s2016182041.cookierun.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182041.cookierun.framework.view.GameView;

public class IndexedAnimationGameBitmap extends AnimationGameBitmap{

    private final int frameHight;

    public IndexedAnimationGameBitmap(int resId, float framesPerSecond, int frameCount){
         super(resId, framesPerSecond, frameCount);
         this.frameWidth = 270;
         this.frameHight = 270;

     }
     protected ArrayList<Rect> srcRects;
     public void setIndices(int... indices){ //가변인자
        srcRects = new ArrayList<Rect>();
        for(int index: indices){
            int x = index %100;
            int y =index / 100;
            int l = 2 + x * 272;
            int t = 2+ y * 272;
            int r = l + 270;
            int b = t+ 270;
            Rect rect = new Rect(r,t,l,b);
            srcRects.add(rect);
        }
        frameCount=indices.length;
     }


    @Override
    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % frameCount;

        int fw = frameWidth;
        int h = frameHight;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;
        //srcRect.set(fw * frameIndex, 0, fw * frameIndex + fw, h);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRects.get(frameIndex), dstRect, null);
    }
}
