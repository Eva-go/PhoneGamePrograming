package kr.ac.kpu.game.s2016182041.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox firewallCheckbox;
    private TextView outTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firewallCheckbox = findViewById(R.id.checkbox);
        outTextView =findViewById(R.id.outTextView);
    }

    public void onBtnApply(View view) {
        boolean checked = firewallCheckbox.isChecked();
        String text=checked ? "Using Firewall":"Not using Firewall";
        outTextView.setText(text);
    }

    public void onCheckFirewall(View view) {
        boolean checked = firewallCheckbox.isChecked();
        String text=checked ? "Checkd Firewall":"Un Checkd Firewall";
        outTextView.setText(text);

    }
}