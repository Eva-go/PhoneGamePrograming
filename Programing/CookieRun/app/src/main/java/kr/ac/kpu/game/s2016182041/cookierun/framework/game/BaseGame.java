package kr.ac.kpu.game.s2016182041.cookierun.framework.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182041.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.cookierun.framework.iface.Recyclable;
import kr.ac.kpu.game.s2016182041.cookierun.framework.view.GameView;



public class BaseGame {
    private static final String TAG = BaseGame.class.getSimpleName();
    // singleton
    private static BaseGame instance;
    private Object vertSpeed;

    private enum  State{
        running,jump,doubleJump,slide,hit
    }

    private State state = State.running;


    public static BaseGame get() {
//        if (instance == null) {
//            instance = new BaseGame();
//        }
        return instance;
    }
    public float frameTime;


    protected BaseGame(){
        instance = this;
    }
    //    Player player;
    ArrayList<ArrayList<GameObject>> layers;
    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null) {
            array = new ArrayList<>();
            recycleBin.put(clazz, array);
        }
        array.add(object);
    }
    public GameObject get(Class clazz) {
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null || array.isEmpty()) return null;
        return array.remove(0);
    }


    public boolean initResources() {
        return false;
    }

    protected void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for(int i=0; i< layerCount;i++){
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
        //if (!initialized) return;
        for (ArrayList<GameObject>objects:layers){
            for (GameObject o : objects) {
                o.update();
            }
        }

    }

    public void draw(Canvas canvas) {
        //if (!initialized) return;

        for (ArrayList<GameObject>objects:layers){
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void add(int layerIndex,GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject>objects = layers.get(layerIndex);
                objects.add(gameObject);
            }
        });
//        Log.d(TAG, "<A> object count = " + objects.size());
    }

    public void remove(GameObject gameObject) {
        if (gameObject instanceof Recyclable) {
            ((Recyclable) gameObject).recycle();
            recycle(gameObject);
        }
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject>objects:layers) {
                    boolean removed = objects.remove(gameObject);
                    if(removed){
                        break;
                    }
                }
//                Log.d(TAG, "<R> object count = " + objects.size());
            }
        });
    }
    public void jump(){
       if(State.jump ==state){
           float y = this.y *vertSpeed;
           
       }
    }
}