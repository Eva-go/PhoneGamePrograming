package kr.ac.kpu.game.s2016182041.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182041.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182041.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182041.dragonflight.ui.view.GameView;

public class HorizontalScrollBackground implements GameObject {
    private final Bitmap bitmap;
    private final float speed;
    private float scroll;

    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    public HorizontalScrollBackground(int resId, int speed){
        this.speed = speed * GameView.MULTIPLIER;
        bitmap= GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float l = 0;//x - w/2 * GameView.MULTIPLIER;
        float t = 0;//y - h/2 * GameView.MULTIPLIER;

        float r = GameView.view.getWidth();;
        float b = r*h/w;//w:r=h:b;
        dstRect.set(l,t,r,b);
        srcRect.set(0,0,w,h);
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        float amount = speed*game.frameTime;
        scroll +=amount;
    }

    @Override
    public void draw(Canvas canvas) {
        int vw = GameView.view.getWidth();
        int vh = GameView.view.getHeight();
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        int dh = vw * ih / iw;

        int curr = (int)scroll % dh;
        if(curr > 0) curr -= dh;

        while (curr < vh){
            dstRect.set(curr,0,curr + dh,vw);
            //l,t,r,b
            canvas.drawBitmap(bitmap,srcRect,dstRect,null);
            curr += dh;
        }
    }
}
