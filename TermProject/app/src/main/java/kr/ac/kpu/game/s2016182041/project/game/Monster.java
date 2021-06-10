package kr.ac.kpu.game.s2016182041.project.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;


import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.IndexedAnimationGameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182041.project.framework.iface.BoxCollidable;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.project.framework.view.GameView;

public class Monster implements GameObject, BoxCollidable {
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
    public float monster_count;
    public float move;
    public float set_hp;
    public float frame_time;
    private Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);
    public enum State {
        sleep, attack, move, hit,die,LAYER_COUNT
    }


    public State state = State.move;

    public Monster(float x, float y,float hp,float move) {
        float attack =10;
        float shield =10;
        this.x = x;
        this.y = y;
        this.move =move;
        this.ground_y = y;
        this.tx = x;
        this.ty = 0;
        this.speed = 800;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.monster, 4.5f, 0);
        this.charBitmap.setIndices(2,2,0,6);
//        this.planeBitmap = new GameBitmap(R.mipmap.fighter);
//        this.fireBitmap = new GameBitmap(R.mipmap.laser_0);
        this.fireTime = 0.0f;
        this.hp=hp;
        moveTo=GameView.view.getWidth();
        paint.setStrokeWidth(14);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void update() {
        if(state == State.move){
            moveTo-=10;
            if(moveTo<move){
                state=State.sleep;
                charBitmap.setIndices(2,2,0,6);
            }
        }
        BaseGame game = BaseGame.get();
        if (state == State.attack) {
            //vertSpeed * game.frameTime;
            frame_time= vertSpeed* game.frameTime;
            charBitmap.setIndices(2,2,100,119);
        }
        hit(hp);
    }

    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, moveTo, y);
        paint.setColor(0xff00ff00);   //color.Green
        paint.setStrokeWidth(30f);
        canvas.drawLine((moveTo-100), y-50, (moveTo-50+hp),  y-50, paint);

    }

    public void hit(float get_hp){
        set_hp=get_hp;
        if(set_hp<=-50){
            frame_time+=GameView.MULTIPLIER;
            hp=-50;
            state = State.die;
            charBitmap.setIndices(2,2,300,330);
            Log.d(TAG,"frame_time: "+frame_time);
            if(frame_time>=80){
                charBitmap.setIndices(2,4,300,330);
                frame_time=81;
            }

        }
    }


    @Override
    public void getBoundingRect(RectF rect) {
        //planeBitmap.getBoundingRect(x, y, rect);
    }

    public void jump() {
        //if (state != State.running && state != State.jump && state != State.slide) {
        if (state != State.sleep) {
            //Log.d(TAG, "Not in a state that can jump: " + state);
            return;
        }
        state = State.attack;
        charBitmap.setIndices(2,2, 0,6);
        vertSpeed = -JUMP_POWER;
    }
}
