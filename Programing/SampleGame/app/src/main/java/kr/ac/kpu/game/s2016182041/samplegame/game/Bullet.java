package kr.ac.kpu.game.s2016182041.samplegame.game;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.kpu.game.s2016182041.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182041.samplegame.ui.view.GameView;

public class Bullet implements GameObject {
    private static int imageWidth;
    private static int imageHeight;
    private final float radius;
    private final float angle;
    private float x, y;
    private float dx, dy;

    Paint paint = new Paint();


    public Bullet(float x, float y, float tx, float ty) {
        this.x = x;
        this.y = y;
        this.radius .10 .0f;

        //float speed =1000;
        float delta_x = tx - this.x;
        float delta_y = ty - this.y;
        float move_dist = 1000;
        this.dx = (float) (move_dist * Math.cos(angle));
        this.dy = (float) (move_dist * Math.sin(angle));

        paint.setColor(0xffff0000);
    }

    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;
        int w= GameView.view.getWidth();
        int h= GameView.view.getHeight();
        boolean toBeDeleted=false;
        if(x<0||x>w)
    }


    public void draw(Canvas canvas) {
        canvas.drawCircle(x,y,radius,paint);
    }

}
