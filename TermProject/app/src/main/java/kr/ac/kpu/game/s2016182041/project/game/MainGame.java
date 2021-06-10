package kr.ac.kpu.game.s2016182041.project.game;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

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
    public float shields = 10;
    public int test =100;
    public int get_cost;
    public int monster_count;
    public int frame_time;
    ArrayList<Monster> monsters =new ArrayList<Monster>();
    private static final String TAG = "MyTag";
    public enum Layer {
        bg, platform,card,cost,monster, player, ui,hp,so, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }
    public void set(Layer layer, GameObject obj) {
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
        get_cost =3;
        monster_count=3;
        initLayers(Layer.LAYER_COUNT.ordinal());

        monsters.add(0,new Monster(w,h-200,100,w-600));
        monsters.add(1,new Monster(w,h-200,100,w-400));
        monsters.add(2,new Monster(w,h-200,100,w-200));
        player = new Player(0, h - 300,200,0);
        //monster = new Monster(w,h-200,50);
        card = new Card(300,200,5);
        cost = new Cost(w/35,(h/35)+100, get_cost);
        turn = new Turn(w-250,h-800);
        add(Layer.player, player);
        add(Layer.monster,monsters.get(0));
        add(Layer.monster,monsters.get(1));
        add(Layer.monster,monsters.get(2));
        add(Layer.cost,cost);
        add(Layer.card,card);
        add(Layer.ui,turn);
//        add(Layer.controller, new EnemyGenerator());
        /*int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
        score.setScore(0);
        add(Layer.ui, score);*/
        add(Layer.bg,new ImageObject(R.mipmap.map1,1000,100));
        add(Layer.platform,new ImageObject(R.mipmap.grass2,1000,1100));
        card.card_draw();


        initialized = true;
        return true;

    }
    public void stage(){
        add(Layer.bg,new ImageObject(R.mipmap.map2,1000,100));
        
    }
    @Override
    public void update() {
        super.update();
        // collision check
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            if(event.getX()<turn.x+turn.bitmap.getWidth()-50/2&&event.getX()>turn.x-turn.bitmap.getWidth()+50/2&&event.getY()<turn.y+turn.bitmap.getHeight()-50/2&&event.getY()>turn.y-turn.bitmap.getHeight()+50/2){
                turn_end_button();
            }
            if(cost.cost_count >=1){
                if(event.getX()<card.x+card.card.get(0).getWidth()-50+(270*0)&&event.getX()>card.x-card.card.get(0).getWidth()+50+(270*0)
                        &&event.getY()<card.y+card.card.get(0).getHeight()-50&&event.getY()>card.y-card.card.get(0).getHeight()+50) {
                    for(int i=0; i<3; ++i){
                        if(card.card_list.get(i)==card.card.get(0)){
                            if(i==0){
                                player.state=Player.State.shield;
                                player.shield+=shields;
                            }
                            else if(i==1){
                                player.state=Player.State.all_attack;
                                for(int k =0; k<monster_count; ++k){
                                    if(monsters.get(k).state!=Monster.State.die) {
                                        monsters.get(k).hp -= player.attack;
                                    }
                                }
                            }
                            else if(i==2){
                                player.state=Player.State.attack;
                                for(int k=0; k<monster_count; ++k) {
                                    if(monsters.get(k).state==Monster.State.sleep) {
                                        monsters.get(k).hp-=player.attack;
                                        break;
                                    }
                                }
                                Log.d(TAG,"m1: "+monsters.get(0).hp+"m2: "+monsters.get(1).hp+"m3: "+monsters.get(2).hp);
                            }

                        }
                    }
                    card.card_remove(0);
                    cost.cost_count-=1;
                }
                else if(event.getX()<card.x+card.card.get(1).getWidth()-50+(270*1)&&event.getX()>card.x-card.card.get(1).getWidth()+50+(270*1)
                        &&event.getY()<card.y+card.card.get(1).getHeight()-50&&event.getY()>card.y-card.card.get(1).getHeight()+50) {
                    for(int i=0; i<3; ++i){
                        if(card.card_list.get(i)==card.card.get(1)){
                            if(i==0){
                                player.state=Player.State.shield;
                                player.shield+=shields;
                            }
                            else if(i==1) {
                                player.state = Player.State.all_attack;
                                for (int k = 0; k < 3; ++k) {
                                    if (monsters.get(k).state != Monster.State.die) {
                                        monsters.get(k).hp -= player.attack;
                                    }
                                }
                            }
                            else if(i==2){
                                player.state=Player.State.attack;
                                for(int k=0; k<monster_count; ++k) {
                                    if(monsters.get(k).state==Monster.State.sleep) {
                                        monsters.get(k).hp-=player.attack;
                                        break;
                                    }
                                }
                                Log.d(TAG,"m1: "+monsters.get(0).hp+"m2: "+monsters.get(1).hp+"m3: "+monsters.get(2).hp);
                            }
                        }
                    }
                    card.card_remove(1);
                    cost.cost_count-=1;
                }
                else if(event.getX()<card.x+card.card.get(2).getWidth()-50+(270*2)&&event.getX()>card.x-card.card.get(2).getWidth()+50+(270*2)
                        &&event.getY()<card.y+card.card.get(2).getHeight()-50&&event.getY()>card.y-card.card.get(2).getHeight()+50) {
                    for(int i=0; i<3; ++i){
                        if(card.card_list.get(i)==card.card.get(2)){
                            if(i==0){
                                player.state=Player.State.shield;
                                player.shield+=shields;
                            }
                            else if(i==1){
                                player.state=Player.State.all_attack;
                                for(int k =0; k<3; ++k){
                                    if(monsters.get(k).state!=Monster.State.die) {
                                        monsters.get(k).hp -= player.attack;
                                    }
                                }
                            }
                            else if(i==2){
                                player.state=Player.State.attack;
                                for(int k=0; k<monster_count; ++k) {
                                    if(monsters.get(k).state==Monster.State.sleep) {
                                        monsters.get(k).hp-=player.attack;
                                        break;
                                    }
                                }
                                Log.d(TAG,"m1: "+monsters.get(0).hp+"m2: "+monsters.get(1).hp+"m3: "+monsters.get(2).hp);
                            }
                        }
                    }
                    card.card_remove(2);
                    cost.cost_count-=1;
                }
                else if(event.getX()<card.x+card.card.get(3).getWidth()-50+(270*3)&&event.getX()>card.x-card.card.get(3).getWidth()+50+(270*3)
                        &&event.getY()<card.y+card.card.get(3).getHeight()-50&&event.getY()>card.y-card.card.get(3).getHeight()+50) {
                    for(int i=0; i<3; ++i){
                        if(card.card_list.get(i)==card.card.get(3)){
                            if(i==0){
                                player.state=Player.State.shield;
                                player.shield+=shields;
                            }
                            else if(i==1){
                                player.state=Player.State.all_attack;
                                for(int k =0; k<3; ++k){
                                    if(monsters.get(k).state!=Monster.State.die) {
                                        monsters.get(k).hp -= player.attack;
                                    }
                                }
                            }
                            else if(i==2){
                                player.state=Player.State.attack;
                                for(int k=0; k<monster_count; ++k) {
                                    if(monsters.get(k).state==Monster.State.sleep) {
                                        monsters.get(k).hp-=player.attack;
                                        break;
                                    }
                                }
                                Log.d(TAG,"m1: "+monsters.get(0).hp+"m2: "+monsters.get(1).hp+"m3: "+monsters.get(2).hp);
                            }
                        }
                    }
                    card.card_remove(3);
                    cost.cost_count-=1;
                }
                else if(event.getX()<card.x+card.card.get(4).getWidth()-50+(270*4)&&event.getX()>card.x-card.card.get(4).getWidth()+50+(270*4)
                        &&event.getY()<card.y+card.card.get(4).getHeight()-50&&event.getY()>card.y-card.card.get(4).getHeight()+50) {
                    for(int i=0; i<3; ++i){
                        if(card.card_list.get(i)==card.card.get(4)){
                            if(i==0){
                                player.state=Player.State.shield;
                                player.shield+=shields;
                            }
                            else if(i==1){
                                player.state=Player.State.all_attack;
                                for(int k =0; k<3; ++k){
                                    if(monsters.get(k).state!=Monster.State.die) {
                                        monsters.get(k).hp -= player.attack;
                                    }
                                }
                            }
                            else if(i==2){
                                player.state=Player.State.attack;
                                for(int k=0; k<monster_count; ++k) {
                                    if(monsters.get(k).state==Monster.State.sleep) {
                                        monsters.get(k).hp-=player.attack;
                                        break;
                                    }
                                }
                                Log.d(TAG,"m1: "+monsters.get(0).hp+"m2: "+monsters.get(1).hp+"m3: "+monsters.get(2).hp);
                            }
                        }
                    }
                    card.card_remove(4);
                    cost.cost_count-=1;
                }
                return true;
            }

        }
        return false;
    }

    public void turn_end_button(){
        for(int i=0; i<monster_count;++i){
            monsters.get(i).state=Monster.State.attack;
            if(player.shield>0){
                Log.d(TAG,"shield: "+player.shield);
                player.shield -= monsters.get(i).attack;
                Log.d(TAG,"shield2: "+player.shield);
                if(player.shield<=0){
                    player.hp -= player.shield;
                    Log.d(TAG,"hp: "+player.hp);
                }
                player.shield=0;
            }
            else if(player.shield<=0){
                player.hp -= monsters.get(i).attack;
                Log.d(TAG,"hp: "+player.hp);
            }
        }
        stage();
        turn.turn_end=true;
        cost.cost_count= get_cost;
        card.card_redraw();
    }

}
