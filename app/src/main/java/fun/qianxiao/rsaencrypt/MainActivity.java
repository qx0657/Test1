package fun.qianxiao.rsaencrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private final String TAG = "_______________20200407_________________";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        new RSAEncrypt(context).encrypt("123", new IEncryptCallBack() {
            @SuppressLint("LongLogTag")
            @Override
            public void EncryptSuccess(String c) {
                Log.i(TAG,"加密成功："+c);
                Toast.makeText(context,c,Toast.LENGTH_SHORT).show();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void EncryptError(Throwable throwable) {
                Log.e(TAG,throwable.toString());
            }
        });
    }
}
