package fun.qianxiao.rsaencrypt;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Create by Administrator
 * On 2020/4/7
 */
public class RSAEncrypt {
    private Context context;
    private IEncryptCallBack callBack;
    private String c = "";

    public static String getpwd_Js = "function getpwd() {" + "\n"
            + "Android.getPwd(password);"
            + "\n" + "}";

    public RSAEncrypt(Context context) {
        this.context = context;
    }

    public void encrypt(String plaintext, final IEncryptCallBack callBack) {
        this.callBack = callBack;
        WebView webView = new WebView(context);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:"+ getpwd_Js);
                view.loadUrl("javascript:getpwd()");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(TextUtils.isEmpty(c)){
                            callBack.EncryptError(new Throwable("获取密钥超时"));
                        }
                    }
                }, 3000);
            }
        });
        webView.addJavascriptInterface(new JavaInterGetPwd(), "Android");
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://qianxiao.fun/test/rsaencrypt/?p="+plaintext);
    }
    class JavaInterGetPwd {
        @JavascriptInterface
        public void getPwd(String pwd){
            c = pwd;
            Log.i("20200407",pwd);
            callBack.EncryptSuccess(pwd);
        }
    }
}
