package kr.ac.kpu.game.s2016182041.cookierun.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182041.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.cookierun.framework.view.GameView;


public class imageObject implements GameObject {
    private final Bitmap bitmap;

    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    public imageObject(int resId, float x, float y){
        bitmap= GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float l = x - w/2 * GameView.MULTIPLIER;
        float t = y - h/2 * GameView.MULTIPLIER;
        float r = x + w/2 * GameView.MULTIPLIER;
        float b = y + h/2 * GameView.MULTIPLIER;
        dstRect.set(l,t,r,b);
        srcRect.set(0,0,w,h);
    }
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }
}
