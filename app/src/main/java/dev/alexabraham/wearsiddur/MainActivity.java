package dev.alexabraham.wearsiddur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import dev.alexabraham.wearsiddur.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private ScrollView mScrollView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Support scroll with crown
        mScrollView = binding.scrollView;
        mScrollView.requestFocus();
    }

    public void onClickButton(View view, String tefilla) {
        Intent i = new Intent(MainActivity.this, TefillaDisplayer.class);
        i.putExtra("tefilla", tefilla);
        startActivity(i);
    }

    public void onClickMincha(View view) {
        onClickButton(view, "mincha");
    }

    public void onClickMaariv(View view) {
        onClickButton(view, "maariv");
    }

    public void onClickKriyatShema(View view) {
        onClickButton(view, "kriyat_shema");
    }
    public void onClickBirkatHamazon(View view) {
        onClickButton(view, "birkat_hamazon");
    }
}