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
import kr.ac.kpu.game.s2016182041.project.framework.view.GameView;

public class Player implements GameObject, BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f / 7.5f;
    private static final float LASER_DURATION = FIRE_INTERVAL / 3;
    private static final float GRAVITY = 2500;
    private static final float JUMP_POWER = 1200;
    private final IndexedAnimationGameBitmap charBitmap;
    private final float ground_y;
    private float frame_time;
    private float x, y;
    private float tx, ty;
    private float vertSpeed;
    private float speed;
    public float shield;
    public  GameView gameView;
    public int moveTo;
    public float hp;
    public float attack;
    public float all_attack;
    private Paint paint_hp =new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint_shield =new Paint(Paint.ANTI_ALIAS_FLAG);
    public boolean turn;
    public enum State {
        sleep, attack, all_attack,shield,move, hit,LAYER_COUNT
    }
    public State state = State.move;

    public Player(float x, float y,float hp,float shield) {
        this.attack = 100;
        this.shield = shield;
        this.all_attack = 70;
        this.x = x;
        this.y = y;
        this.ground_y = y;
        this.tx = x;
        this.ty = 0;
        this.turn = true;
        this.speed = 800;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.tengo_alls, 4.5f, 0);
        this.charBitmap.setIndices(4,3,300,311);
        this.hp = hp;
//        this.planeBitmap = new GameBitmap(R.mipmap.fighter);
//        this.fireBitmap = new GameBitmap(R.mipmap.laser_0);
        this.frame_time = 0.0f;
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
        else if(state == State.all_attack){
            frame_time+=GameView.MULTIPLIER;
            charBitmap.setIndices(5,3,200,220);
            if(frame_time>=80)
            {
                state=State.sleep;
                frame_time=0;
            }
        }

        else if(state==State.attack){
            frame_time+=GameView.MULTIPLIER;
            charBitmap.setIndices(4,3,100,110);

            if(frame_time>=40){
                state=State.sleep;
                frame_time=0;
            }
        }

    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, moveTo, y);
        paint_hp.setColor(0xff00ff00);   //color.Green
        paint_hp.setStrokeWidth(30f);
        paint_shield.setColor(0xffffffff);   //color.White
        paint_shield.setStrokeWidth(30f);
        //hp = 200
        if(hp>=0){
            canvas.drawLine(moveTo-100, y-100, (moveTo-100+hp),  y-100, paint_hp);
        }
        canvas.drawLine(moveTo-100, y-130, (moveTo-100+shield),  y-130, paint_shield);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        //planeBitmap.getBoundingRect(x, y, rect);
    }
}
