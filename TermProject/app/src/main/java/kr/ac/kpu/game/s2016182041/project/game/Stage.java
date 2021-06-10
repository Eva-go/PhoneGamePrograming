package kr.ac.kpu.game.s2016182041.project.game;

import kr.ac.kpu.game.s2016182041.project.R;
import kr.ac.kpu.game.s2016182041.project.framework.game.BaseGame;
import kr.ac.kpu.game.s2016182041.project.framework.iface.GameObject;
import kr.ac.kpu.game.s2016182041.project.framework.object.ImageObject;

public class Stage extends BaseGame {
    public MainGame mainGame;
    public ImageObject map;
    public Stage(){
        map = new ImageObject(R.mipmap.map1,1000,100);
    }
    public ImageObject Stage_Update(){       map=new ImageObject(R.mipmap.map2,1000,100);
        return map;
    }
}
