package kr.ac.kpu.game.s2016182041.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();
    private Paint paint =new Paint();
    private Rect rect=new Rect();

    public MyView(Context context, AttributeSet set) {
        super(context,set);
        paint.setColor(0xff0055ff);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"onTouch"+event);
        //case사용 해서 제작
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int l =0+getLeftPaddingOffset();
        int t =0+getTopPaddingOffset();
        int w =getWidth()-getRightPaddingOffset();
        int h =getHeight()-getBottomPaddingOffset();
        rect.set(l,t,w,h);
        Log.d(TAG,"drawing"+rect);
        canvas.drawRect(rect,paint);
    }
}
