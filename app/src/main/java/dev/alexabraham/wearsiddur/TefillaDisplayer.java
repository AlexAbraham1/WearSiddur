package dev.alexabraham.wearsiddur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dev.alexabraham.wearsiddur.databinding.ActivityTefillaDisplayerBinding;

public class TefillaDisplayer extends Activity {

    private ScrollView mScrollView;
    private TextView mTextView;
    private ActivityTefillaDisplayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String tefilla = intent.getStringExtra("tefilla");

        binding = ActivityTefillaDisplayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.textView;
        String tefillaText = loadTefilla(tefilla);
        mTextView.setText(tefillaText);

        // Support scroll with crown
        mScrollView = binding.scrollView;
        mScrollView.requestFocus();
    }

    private String loadTefilla(String tefilla) {
        BufferedReader in = null;
        try {
            String fileName = "tefillot/" + tefilla + ".txt";
            StringBuilder buf = new StringBuilder();
            InputStream is = getAssets().open(fileName);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ( (str = in.readLine()) != null ) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e(this.getLocalClassName(), "Error opening tefilla " + tefilla);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(this.getLocalClassName(), "Error closing tefilla " + tefilla);
                }
            }
        }

        return null;
    }
}