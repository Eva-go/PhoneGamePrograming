package kr.ac.kpu.game.s2016182041.project.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;


import java.util.TimerTask;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182041.project.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;

public class Player implements GameObject, BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f / 7.5f;
    private static final float LASER_DURATION = FIRE_INTERVAL / 3;
    private static final float GRAVITY = 2500;
    private static final float JUMP_POWER = 1200;
    private final IndexedAnimationGameBitmap charBitmap;
    private final float ground_y;
    private float fireTime;
    private float x, y;
    private float tx, ty;
    private float vertSpeed;
    private float speed;
    public int moveTo;
    public float hp;
    public float attack;
    public float all_attack;
    public float shield;
    private Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);
    public enum State {
        sleep, attack, all_attack,shield,move, hit,LAYER_COUNT
    }
    public State state = State.move;

    public Player(float x, float y,float hp) {
        this.attack = 100;
        this.shield = 20;
        this.all_attack = 70;
        this.x = x;
        this.y = y;
        this.ground_y = y;
        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.tengo_alls, 4.5f, 0);
        this.charBitmap.setIndices(4,3,300,311);
        this.hp = hp;
//        this.planeBitmap = new GameBitmap(R.mipmap.fighter);
//        this.fireBitmap = new GameBitmap(R.mipmap.laser_0);
        this.fireTime = 0.0f;
    }



    public void moveTo(float x, float y) {
        this.tx = x;
        //this.ty = this.y;
    }

    public void update() {
        if(state == State.move){
            moveTo+=10;
            if(moveTo>=300){
                state=State.sleep;
                charBitmap.setIndices(2,3,0,11);
            }
        }
        else if(state == State.sleep){
            charBitmap.setIndices(2,3,0,11);
        }

        else if(state==State.attack){
                charBitmap.setIndices(4,3,100,110);
        }
    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, moveTo, y);
        paint.setColor(0xff00ff00);   //color.Green
        paint.setStrokeWidth(30f);
        canvas.drawLine(moveTo-100, y-100, (moveTo+hp),  y-100, paint);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        //planeBitmap.getBoundingRect(x, y, rect);
    }
}
