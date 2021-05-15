package kr.ac.kpu.game.s2016182041.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.kpu.game.s2016182041.project.game.MainGame;

public class MainActivity extends AppCompatActivity {
    private MainGame mainGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainGame = new MainGame();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}