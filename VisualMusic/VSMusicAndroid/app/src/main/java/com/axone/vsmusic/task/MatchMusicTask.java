package com.axone.vsmusic.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.axone.vsmusic.activity.LoadingActivity;
import com.axone.vsmusic.activity.WebMusicActivity;
import com.axone.vsmusic.socket.MyClientSocket;
import com.axone.vsmusic.util.Config;

import com.axone.vsmusic.R;
/**
 * Created by 秋水 on 2017/9/12.
 */

public class MatchMusicTask extends AsyncTask<String, Integer, String>{

    private WebView webView;
    private ProgressBar progressBar;
    private TextView webLabel;
    private WebMusicActivity activity;

    public MatchMusicTask(WebMusicActivity activity) {
        this.activity = activity;
        this.webView = (WebView)activity.findViewById(R.id.web_view);
        this.progressBar = (ProgressBar) activity.findViewById(R.id.web_progress);
        this.webLabel = (TextView) activity.findViewById(R.id.web_label);
    }

    @Override
    protected String doInBackground(String... strings) {

        System.out.println("MatchTask数据：" + strings[0] + "," + strings[1] + "," + strings[2]);
        return MyClientSocket.createClient().matchMusic(strings[0], strings[1], strings[2], activity);
    }

    @Override
    protected void onPostExecute(String s) {
        progressBar.setVisibility(View.GONE);
        if(s.equals("")){
            webLabel.setText("匹配失败");
        }else{
            webLabel.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            initData(s);
            System.out.println(s);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    private void initData(String videoUrl) {

        //下面三行设置主要是为了待webView成功加载html网页之后，我们能够通过webView获取到具体的html字符串
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(videoUrl), "local_obj");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        webView.loadUrl(Config.HTML_INDEX_URL);
    }

    final class InJavaScriptLocalObj {
        private String videoUrl;

        public InJavaScriptLocalObj(String url){
            this.videoUrl = url;
        }


        @JavascriptInterface
        public void showSource(String html) {
            refreshHtmlContent(html, videoUrl);
        }
    }


    private void refreshHtmlContent(final String html, final String videoUrl){
        Log.i("网页内容",html);
        Log.i("视频路径",videoUrl);
        webView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //解析html字符串为对象
                Document document = Jsoup.parse(html);

                //通过ID获取到element并设置其src属性
                Element element = document.getElementById("myVideo");
                element.attr("src",videoUrl);

                //获取进行处理之后的字符串并重新加载
                String body = document.toString();
                webView.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
            }
        },5000);
    }
}


