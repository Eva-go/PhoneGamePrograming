package kr.ac.kpu.game.s2016182041.project.framework.bitmap;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182041.project.framework.view.GameView;
import kr.ac.kpu.game.s2016182041.project.game.Player;

public class    IndexedAnimationGameBitmap extends AnimationGameBitmap {

    private static final String TAG = IndexedAnimationGameBitmap.class.getSimpleName();

    public int FrameX;
    public int FrameY;
    public int FrameCount;
    public IndexedAnimationGameBitmap(int resId, float framesPerSecond, int frameCount) {
        super(resId, framesPerSecond,frameCount);
        FrameCount=frameCount;
    }

    protected ArrayList<Rect> srcRects;
    public void setIndices(int indexFrameX,int indexFrameY,int... indices) {
        this.FrameX=indexFrameX*100;
        this.FrameY=indexFrameY*100;
        srcRects = new ArrayList<>();
        for (int index: indices) {
            int x = index % 100;
            int y = index / 100;
            int l =  x * 100*indexFrameX;
            int t = y * 100*indexFrameY;
            int r = l + 100*indexFrameX;
            int b = t + 100*indexFrameY;
            Rect rect = new Rect(l, t, r, b);
            //Log.d(TAG, "Adding rect: " + rect);
            srcRects.add(rect);
        }
        frameCount = indices.length;
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createdOn);
        frameIndex = Math.round(elapsed * 0.001f * framesPerSecond) % frameCount;
        //Log.d(TAG, "frameIndex=" + frameIndex + " frameCount=" + frameCount);

        int fw = FrameX;
        int h = FrameY;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;
        dstRect.set(x - hw, y - hh, x + hw, y + hh);
        canvas.drawBitmap(bitmap, srcRects.get(frameIndex), dstRect, null);
    }
}
