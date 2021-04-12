package kr.ac.kpu.game.s2016182041.samplegame.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182041.samplegame.R;
import kr.ac.kpu.game.s2016182041.samplegame.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182041.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182041.samplegame.ui.view.GameView;

public class Bullet implements GameObject {
    private final float radius;
    private final float angle;
    private float x, y;
    private float dx, dy;
    private static float FRAME_RATE = 8.5f;
    private static AnimationGameBitmap bitmap;

//    Paint paint = new Paint();

    public Bullet(float x, float y, float tx, float ty) {
        this.x = x;
        this.y = y;
        this.radius = 10.0f;

        //float speed = 1000;
        float delta_x = tx - this.x;
        float delta_y = ty - this.y;
        angle = (float) Math.atan2(delta_y, delta_x);
        float move_dist = 500;
        this.dx = (float) (move_dist * Math.cos(angle));
        this.dy = (float) (move_dist * Math.sin(angle));

//        paint.setColor(0xFFFF0000);
        if (bitmap == null) {
            bitmap = new AnimationGameBitmap(R.mipmap.bullet_hadoken, FRAME_RATE, 6);
        }
    }

    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        int frameWidth = bitmap.getWidth();
        int frameHeight = bitmap.getHeight();
        boolean toBeDeleted = false;
        if (x < 0 || x > w - frameWidth) {
            toBeDeleted = true;
        }
        if (y < 0 || y > h - frameHeight) {
            toBeDeleted = true;
        }
        if (toBeDeleted) {
            game.remove(this);
        }
    }

    public void draw(Canvas canvas) {
        float degree = (float) (angle *180/Math.PI) + 90;
        canvas.save();
        canvas.rotate(degree, x, y);
        bitmap.draw(canvas,x,y);
        canvas.restore();
    }
}
