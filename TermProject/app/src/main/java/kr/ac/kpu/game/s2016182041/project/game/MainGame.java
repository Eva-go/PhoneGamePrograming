package kr.ac.kpu.game.s2016182041.project.game;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016182041.project.R;
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
    private Cost cost;
    private Turn turn;
    private Card card;
    private Monster monster;
    public int bgspeed = -30;
    public Rect rect;
    public int test =100;

    private static final String TAG = "MyTag";
    public enum Layer {
        bg, platform,card,cost,monster, player, ui,hp, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }
    public void remove(GameObject obj){
        remove(obj);
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
        card = new Card(300,200,5);
        cost = new Cost(w/35,(h/35)+100,5);
        turn = new Turn(true,w-250,h-800);
        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player, player);
        add(Layer.monster,monster);
        add(Layer.cost,cost);
        add(Layer.card,card);
//        add(Layer.controller, new EnemyGenerator());
        /*int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);*/
        add(Layer.bg, new HorizontalScrollBackground(R.mipmap.map1, bgspeed,0));
        add(Layer.platform,new HorizontalScrollBackground(R.mipmap.grass2,bgspeed,-1700));
        add(Layer.ui,new ImageObject(turn.bitmap,w-250,h-800));
        card.card_draw();
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

    @Override
    public void update() {
        super.update();
        // collision check
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();


//        if (action == MotionEvent.ACTION_DOWN) {
        if (action == MotionEvent.ACTION_DOWN) {
            if(event.getX()>1550&&event.getX()<1900&&event.getY()>130&&event.getY()<265){
                turn_end_button();
            }
            if(event.getX()<card.x+card.card.get(0).getWidth()-50+(270*0)&&event.getX()>card.x-card.card.get(0).getWidth()+50+(270*0)
                    &&event.getY()<card.y+card.card.get(0).getHeight()-50&&event.getY()>card.y-card.card.get(0).getHeight()+50) {
                for(int i=0; i<3; ++i){
                    if(card.card_list.get(i)==card.card.get(0)){
                        if(i==0)
                            card.card_states(Card.State.shield);
                        else if(i==1)
                            card.card_states(Card.State.all_attack);
                        else if(i==2)
                            card.card_states(Card.State.attack);
                    }
                }
                card.card_remove(0);
                cost.cost_count-=1;
            }
            else if(event.getX()<card.x+card.card.get(1).getWidth()-50+(270*1)&&event.getX()>card.x-card.card.get(1).getWidth()+50+(270*1)
                    &&event.getY()<card.y+card.card.get(1).getHeight()-50&&event.getY()>card.y-card.card.get(1).getHeight()+50) {
                for(int i=0; i<3; ++i){
                    if(card.card_list.get(i)==card.card.get(1)){
                        if(i==0)
                            card.card_states(Card.State.shield);
                        else if(i==1)
                            card.card_states(Card.State.all_attack);
                        else if(i==2)
                            card.card_states(Card.State.attack);
                    }
                }
                card.card_remove(1);
                cost.cost_count-=1;
            }
            else if(event.getX()<card.x+card.card.get(2).getWidth()-50+(270*2)&&event.getX()>card.x-card.card.get(2).getWidth()+50+(270*2)
                    &&event.getY()<card.y+card.card.get(2).getHeight()-50&&event.getY()>card.y-card.card.get(2).getHeight()+50) {
                for(int i=0; i<3; ++i){
                    if(card.card_list.get(i)==card.card.get(2)){
                        if(i==0)
                            card.card_states(Card.State.shield);
                        else if(i==1)
                            card.card_states(Card.State.all_attack);
                        else if(i==2)
                            card.card_states(Card.State.attack);
                    }
                }
                card.card_remove(2);
                cost.cost_count-=1;
            }
            else if(event.getX()<card.x+card.card.get(3).getWidth()-50+(270*3)&&event.getX()>card.x-card.card.get(3).getWidth()+50+(270*3)
                    &&event.getY()<card.y+card.card.get(3).getHeight()-50&&event.getY()>card.y-card.card.get(3).getHeight()+50) {
                for(int i=0; i<3; ++i){
                    if(card.card_list.get(i)==card.card.get(3)){
                        if(i==0)
                            card.card_states(Card.State.shield);
                        else if(i==1)
                            card.card_states(Card.State.all_attack);
                        else if(i==2)
                            card.card_states(Card.State.attack);
                    }
                }
                card.card_remove(3);
                cost.cost_count-=1;
            }
            else if(event.getX()<card.x+card.card.get(4).getWidth()-50+(270*4)&&event.getX()>card.x-card.card.get(4).getWidth()+50+(270*4)
                    &&event.getY()<card.y+card.card.get(4).getHeight()-50&&event.getY()>card.y-card.card.get(4).getHeight()+50) {
                for(int i=0; i<3; ++i){
                    if(card.card_list.get(i)==card.card.get(4)){
                        if(i==0)
                            card.card_states(Card.State.shield);
                        else if(i==1)
                            card.card_states(Card.State.all_attack);
                        else if(i==2)
                            card.card_states(Card.State.attack);
                    }
                }
                card.card_remove(4);
                cost.cost_count-=1;
            }

            //if(event.getX()==card.card_list.get(0).getHeight())
            /*og.d(TAG, "x:"+event.getX());
            Log.d(TAG, "y:"+event.getY());*/
//            layer.moveTo(event.getX(), event.getY());
            //player.jump();
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

    public void turn_end_button(){
        turn.turn_end=true;
        cost.cost_count=5;
        card.card_redraw();

    }

}