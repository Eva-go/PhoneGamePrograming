package kr.ac.kpu.game.s2016182041.project.game;

import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.provider.ContactsContract;
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
    public ImageObject map;
    private Player player;
    private Cost cost;
    private Turn turn;
    private Card card;
    public  Selection selection;
    private Monster monster;
    public int bgspeed = -30;
    public Rect rect;
    public float shields = 20;
    public int test =100;
    public int get_cost=3;
    public int monster_count=3;
    public int frame_time;
    public int stage_count=0;
    public ImageObject selection_map;
    public ImageObject re_game_butten;
    public int monster_move=200;
    public boolean regame = false;
    public ImageObject do_die;
    public ImageObject in_game_clear;
    public boolean map_set=false;

    private MediaPlayer mainBgmMediaPlayer;
    private MediaPlayer attackBgmMediaPlayer;
    private MediaPlayer lastPlayingMediaPlayer;

    ArrayList<Monster> monsters =new ArrayList<Monster>();
    private static final String TAG = "MyTag";
    public enum Layer {
        bg, platform,card,cost,monster, player, ui,selection,event,game_over,regame, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }

    @Override
    public boolean initResources() {

        if (initialized) {
            return false;
        }

        re_game_butten = new ImageObject(R.mipmap.re_game,1000,500);
        map = new ImageObject(R.mipmap.map1,1000,100);
        selection_map =new ImageObject(R.mipmap.selection,1000,500);
        do_die =new ImageObject(R.mipmap.you_died,1000,200);
        in_game_clear = new ImageObject(R.mipmap.game_clear,1000,200);
        initLayers(Layer.LAYER_COUNT.ordinal());
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        monsters.add(0,new Monster(w,h-200,100,w-monster_move));
        monsters.add(1,new Monster(w,h-200,100,w-monster_move*2));
        monsters.add(2,new Monster(w,h-200,100,w-monster_move*3));
        player = new Player(0, h - 300,200,0);
        selection = new Selection(w/4,(h/2));
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

        add(Layer.bg,map);
        add(Layer.platform,new ImageObject(R.mipmap.grass2,1000,1200));
        card.card_draw();


        initialized = true;
        return true;

    }
    public void Selection(){
        if(stage_count<2){
            player.turn=false;
            add(Layer.selection,selection_map);
            add(Layer.event, selection);
            for(int i=0; i<monster_count; ++i)
            {
                monsters.get(i).hp=100;
                monsters.get(i).state=Monster.State.sleep;
            }
        }
        //Log.d(TAG,"get(0) x: "+selection.selection_list.get(0).getWidth()+"get(0) y: "+selection.selection_list.get(0).getHeight());

    }
    public void remove_selection(){
        remove(selection_map);
        remove(selection);
        player.turn=true;
        cost.cost_count= get_cost;
        card.card_redraw();
        //selection_map= new ImageObject(R.mipmap.non,1000,100);
        //add(Layer.selection,selection_map);

    }
    public void stage(){
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        if(stage_count==0&&map_set==true){
            map = new ImageObject(R.mipmap.map1,1000,100);
            add(Layer.bg,map);
            stage_count -=1;
            for(int i=0; i<monster_count;++i){
                monsters.get(i).moveTo=GameView.view.getWidth()+(i*-200);
            }

        }
        else if(stage_count==0&&map_set==false){
            remove(map);
            map = new ImageObject(R.mipmap.map2,1000,100);
            add(Layer.bg,map);
            monster_count+=1;
            regame=true;
            monsters.add(monster_count-1,new Monster(w,h-200,100,w-monster_move*monster_count));
            add(Layer.monster,monsters.get(monster_count-1));

        }
        else if(stage_count==1){
            remove(map);
            map = new ImageObject(R.mipmap.map3,1000,100);
            add(Layer.bg,map);
            monster_count+=1;
            regame=true;
            monsters.add(monster_count-1,new Monster(w,h-200,100,w-monster_move*monster_count));
            add(Layer.monster,monsters.get(monster_count-1));
        }
        regame=false;
        map_set=false;
        stage_count+=1;
        //player.turn=true;
    }
    @Override
    public void update() {
        super.update();

        if(stage_count==2&&monsters.get(monster_count-1).state==Monster.State.die&&regame==false){
            player.turn=false;
            regame=true;
            add(Layer.game_over,in_game_clear);
            add(Layer.regame,re_game_butten);
        }
        else if(monsters.get(monster_count-1).state==Monster.State.die){
            Selection();
        }
        else if(player.hp<=0&&regame==false){
            player.turn=false;
            regame=true;
            add(Layer.game_over,do_die);
            add(Layer.regame,re_game_butten);

        }
        // collision check
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN&&player.turn==true&&regame==false) {
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
                                for (int k = 0; k < monster_count; ++k) {
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
                    card.card_remove(4);
                    cost.cost_count-=1;
                }

                return true;
            }

        }
        else if(action==MotionEvent.ACTION_DOWN&&player.turn==false&&regame==false){
             if(event.getX()> selection.selection_list.get(0).getWidth()-100&&event.getX()<selection.selection_list.get(0).getWidth()+500&&
                     event.getY()>selection.selection_list.get(0).getHeight()-500&&event.getY()<selection.selection_list.get(0).getHeight()+500) {
                 get_cost+=1;

                 remove_selection();
                 stage();

             }
            else if(event.getX()> selection.selection_list.get(1).getWidth()-100+(1000*1)&&event.getX()<selection.selection_list.get(1).getWidth()+500+(1000*1)&&
                    event.getY()>selection.selection_list.get(1).getHeight()-500&&event.getY()<selection.selection_list.get(1).getHeight()+500){
                player.hp += 70;
                 remove_selection();
                 stage();
             }

            return true;

        }
        else if(action==MotionEvent.ACTION_DOWN&&regame==true){
            if(event.getX()>1000-200&&event.getX()<1000+200&&event.getY()>500-80&&event.getY()<500+80){
                for(int i=0; i<monster_count;++i)
                    remove(monsters.get(i));
                get_cost =3;
                stage_count =0;
                monster_count=3;
                regame= false;
                player.turn=true;
                player.hp=200;
                map_set =true;
                for(int i=0; i<monster_count;++i)
                {
                    monsters.get(i).state=Monster.State.sleep;
                    monsters.get(i).hp=100;
                }
                cost.cost_count= get_cost;
                card.card_redraw();
                remove(re_game_butten);
                remove(in_game_clear);
                remove(do_die);
                stage();
               for(int i=0; i<monster_count;++i)
               {
                   add(Layer.monster,monsters.get(i));
               }


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
        turn.turn_end=true;
        cost.cost_count= get_cost;
        card.card_redraw();
    }

}
