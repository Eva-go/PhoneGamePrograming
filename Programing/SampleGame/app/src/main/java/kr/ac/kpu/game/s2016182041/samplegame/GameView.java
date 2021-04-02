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
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class GameView extends View {
    private static final String TAG = "Drawing";
    public static final int BALL_COUNT = 10;
    private Bitmap bitmap;

//    private Ball b1,b2;
    Player player;
    ArrayList<Ball> balls =new ArrayList<>(); //ArrayList는 싱글스레드에서 유리 벡터는 멀티 스레드 유리


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
        for(Ball b:balls){
            b.update();
        }
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
        player =new Player(100,100,0,0);
        Random rand =new Random();
        for(int i = 0; i< BALL_COUNT; i++){
            float x= rand.nextInt(1000);
            float y= rand.nextInt(1000);
            float dx = rand.nextFloat() *1000 -500;
            float dy = rand.nextFloat() *1000 -500;
            Ball b= new Ball(x,y,dx,dy);
            balls.add(b);
        }
//        b1 =new Ball(100,100,500,700);
//        b2 =new Ball(500,500,-500,450);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //그리는 순서 중요! 공처음 그 위에 플레이어
        for(Ball b:balls){
            b.draw(canvas);
        }
        player.draw(canvas);
        //super.onDraw(canvas); << 부모 부르기
//        canvas.drawBitmap(bitmap,b1.x,b1.y,null);
//        canvas.drawBitmap(bitmap,b2.x,b2.y,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN||action== MotionEvent.ACTION_MOVE){
            player.moveTo(event.getX(),event.getY());
            return true;
        }
        return false;
    }
}
