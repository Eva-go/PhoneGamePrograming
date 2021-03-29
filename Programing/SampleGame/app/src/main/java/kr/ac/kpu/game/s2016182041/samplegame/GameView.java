package kr.ac.kpu.game.s2016182041.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;



public class GameView extends View {
    private static final String TAG = "Drawing";
    private Bitmap bitmap;

    private float x;
    private float y;
    //xml 속성 적용가능
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
        startUpdating();
    }

    private void startUpdating(){
        doGameFrame();
    }

    private void doGameFrame() {
        //update();
        x += 0.01;
        y += 0.02;
        //draw();
        invalidate();
        //startUpdating();
        
    }

    private void initResources() {
        Resources res = getResources();
        bitmap= BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);
        x = 100;
        y = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas); << 부모 부르기
        canvas.drawBitmap(bitmap,x,y,null);
        Log.d(TAG,"Drawing at"+x+","+y);
    }
}
