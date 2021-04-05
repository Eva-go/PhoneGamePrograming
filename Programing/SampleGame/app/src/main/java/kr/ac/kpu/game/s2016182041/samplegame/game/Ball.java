package kr.ac.kpu.game.s2016182041.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182041.samplegame.ui.view.GameView;
import kr.ac.kpu.game.s2016182041.samplegame.R;
import kr.ac.kpu.game.s2016182041.samplegame.framework.GameObject;

public class Ball implements GameObject {
    private static int imameWidth;
    private static int imageHeight;
    private float x,y;
    private float dx,dy;
    private static Bitmap bitmap;

    public Ball(float x,float y,float dx,float dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        if(bitmap == null) {
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
            imameWidth=bitmap.getWidth();
            imageHeight=bitmap.getHeight();
        }
    }

    public void update() {
        MainGame game = MainGame.get();
        this.x += this.dx * game.frameTime;
        this.y += this.dy * game.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        if(x<0||x>w-imameWidth){
            dx*=-1;
        }
        if(y<0||y>h-imageHeight){
            dy = -dy;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,this.x,this.y,null);

    }
}
