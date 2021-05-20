package kr.ac.kpu.game.s2016182041.project.game;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.bitmap.GameBitmap;
import kr.ac.kpu.game.s2016182041.project.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.project.framework.object.HorizontalScrollBackground;
import kr.ac.kpu.game.s2016182041.project.framework.object.ImageObject;
import kr.ac.kpu.game.s2016182041.project.framework.view.GameView;

public class MainGame extends BaseGame{
    private boolean initialized;
    ArrayList<Integer> cardList = new ArrayList<>();
    private Player player;
    private Score score;
    private Monster monster;
    public int bgspeed = -30;
    public int cardHand;
    public Rect rect;
    public enum Layer {
        bg, platform,card,monster, player, ui, controller, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }

    @Override
    public boolean initResources() {
        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        player = new Player(0, h - 300);
        monster = new Monster(w,h-200,100);
        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player, player);
        add(Layer.monster,monster);
//        add(Layer.controller, new EnemyGenerator());
        int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);
        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.map1, bgspeed,0));
        add(Layer.platform,new HorizontalScrollBackground(R.mipmap.grass2,bgspeed,-1700));
        cardList.add(R.mipmap.all_attack);
        cardList.add(R.mipmap.attack);
        cardList.add(R.mipmap.shield);

//        float tx = 0, ty = h - 150;
//        while (tx < w) {
//            Platform platform = new Platform(Platform.Type.T_10x2, tx, ty);
//            add(Layer.platform, platform);
//            tx += platform.getDstWidth();
////        VerticalScrollBackground clouds = new VerticalScrollBackground(R.mipmap.clouds, 20);
////        add(Layer.bg2, clouds);
//        }

        initialized = true;
        return true;

    }
    public void cardhand(){
        for(int i=0; i<5; ++i) {
            Random rnd = new Random();
            add(Layer.card, new ImageObject(cardList.get(rnd.nextInt(3)),400+(300*i),200));
        }
    }

    @Override
    public void update() {
        super.update();

        // collision check
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
//            player.moveTo(event.getX(), event.getY());
            player.jump();
            monster.hp-=10;
            cardhand();
//            int li = 0;
//            for (ArrayList<GameObject> objects: layers) {
//                for (GameObject o : objects) {
//                    Log.d(TAG, "L:" + li + " " + o);
//                }
//                li++;
//            }
            return true;
        }
        return false;
    }
}
