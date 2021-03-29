package kr.ac.kpu.game.s2016182041.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private final Bitmap bitmap;

    private int x;
    private int y;
    //xml 속성 적용가능
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Resources res = getResources();
        bitmap=BitmapFactory.decodeResource(res,R.mipmap.soccer_ball_240);
        x = 100;
        y = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas); << 부모 부르기
        canvas.drawBitmap(bitmap,x,y,null);
    }
}
