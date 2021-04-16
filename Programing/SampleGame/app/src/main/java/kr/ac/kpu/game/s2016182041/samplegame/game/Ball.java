package kr.ac.kpu.game.s2016182041.samplegame.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2016182041.samplegame.R;
import kr.ac.kpu.game.s2016182041.samplegame.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182041.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182041.samplegame.ui.view.GameView;

public class Ball implements GameObject {
    private float x, y;
    private float dx, dy;
    private AnimationGameBitmap bitmap;
    private static float FRAME_RATE = 8.5f;
    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        Random r = new Random();
        float frameRate = FRAME_RATE * (r.nextFloat()* 0.4f+ 0.8f);
        bitmap=new AnimationGameBitmap(R.mipmap.fireball_128_24f,frameRate,0);
        }

    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        int frameWidth = bitmap.getWidth();
        int frameHeight =bitmap.getHeight();
        if (x < 0 || x > w - frameWidth) {
            dx *= -1;
        }
        if (y < 0 || y > h - frameHeight) {
            dy = -dy;
        }
    }

    public void draw(Canvas canvas) {
        bitmap.draw(canvas,x,y);

    }
}
