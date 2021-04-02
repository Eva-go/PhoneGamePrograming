package kr.ac.kpu.game.s2016182041.samplegame;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class GameView extends View {
    private static final String TAG = "Drawing";
    private Bitmap bitmap;

    private Ball b1,b2;


    public static float frameTime;
    private float lastFrame;
    public static GameView view;
    //xml 속성 적용가능
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        initResources();
        startUpdating();
    }

    private void startUpdating(){
        doGameFrame();
    }

    private void doGameFrame() {
        //update();
        b1.update();
        b2.update();
//        b1.x+=b1.dx*frameTime;
//        b1.y +=b1.dy*frameTime;
//
//        b2.x+=b2.dx *frameTime;
//        b2.y+=b2.dy*frameTime;

        //draw();
        invalidate();
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if(lastFrame== 0){
                    lastFrame=time;
                }
                frameTime = (float) (time-lastFrame)/1000000000;
                doGameFrame();
                lastFrame=time;
            }
        });
       // doGameFrame();
    }

    private void initResources() {
        b1 =new Ball(100,100,500,700);
        b2 =new Ball(500,500,-500,450);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        b1.draw(canvas);
        b2.draw(canvas);
        //super.onDraw(canvas); << 부모 부르기
//        canvas.drawBitmap(bitmap,b1.x,b1.y,null);
//        canvas.drawBitmap(bitmap,b2.x,b2.y,null);
    }
}
