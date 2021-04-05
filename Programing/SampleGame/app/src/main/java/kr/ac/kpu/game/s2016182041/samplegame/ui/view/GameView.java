package kr.ac.kpu.game.s2016182041.samplegame.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182041.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182041.samplegame.game.Ball;
import kr.ac.kpu.game.s2016182041.samplegame.game.Player;
import kr.ac.kpu.game.s2016182041.samplegame.game.MainGame;


public class GameView extends View {
    private static final String TAG = "Drawing";
    private Bitmap bitmap;

//    private Ball b1,b2;
//    ArrayList<Ball> balls =new ArrayList<>(); //ArrayList는 싱글스레드에서 유리 벡터는 멀티 스레드 유리
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
        for(GameObject o:objects){
            o.update();
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
                MainGame game = MainGame.get();
                game.update();
                if(lastFrame== 0){
                    lastFrame=time;
                }
                game.frameTime = (float) (time-lastFrame)/1000000000;
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
            objects.add(b);
        }
        objects.add(player);
//        b1 =new Ball(100,100,500,700);
//        b2 =new Ball(500,500,-500,450);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //그리는 순서 중요! 공처음 그 위에 플레이어
        for(GameObject o:objects){
            o.draw(canvas);
        }
//        player.draw(canvas);
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