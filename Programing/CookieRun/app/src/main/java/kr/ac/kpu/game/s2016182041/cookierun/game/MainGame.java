package kr.ac.kpu.game.s2016182041.cookierun.game;

import android.view.MotionEvent;

import kr.ac.kpu.game.s2016182041.cookierun.R;
import kr.ac.kpu.game.s2016182041.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182041.cookierun.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.cookierun.framework.object.VerticalScrollBackground;
import kr.ac.kpu.game.s2016182041.cookierun.framework.view.GameView;

public class MainGame extends BaseGame {
    private Player player;
    private Score score;
    private boolean initialized;

    public enum Layer{
        bg,platform,player,ui,controller, LAYER_COUNT
    }

    public void add(Layer layer, GameObject obj){
        add(layer.ordinal(),obj);
    }
    @Override
    public boolean initResources() {
        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.LAYER_COUNT.ordinal());

        player = new Player(w/2, h - 300);
        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player,player);

        int margin = (int)(20*GameView.MULTIPLIER);

        VerticalScrollBackground bg = new VerticalScrollBackground(R.mipmap.cookie_run_bg_1,10);
        add(Layer.bg,bg);


        float tx=0,ty=h-500;
        while(tx < w){
            Platform platform = new Platform(Platform.Type.T_10x2,tx,ty);
            add(Layer.platform,new Platform(Platform.Type.T_10x2,tx,ty));
//            tx += platform.getDstWidth();
        }

        initialized = true;
        return true;
    }


    @Override
    public void update() {
        super.update();

        // 충돌체크
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }
}
