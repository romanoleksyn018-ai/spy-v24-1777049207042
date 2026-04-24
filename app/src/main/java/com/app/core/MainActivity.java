package com.app.core;
import android.os.*;
import android.webkit.*;
import android.app.Activity;
import android.Manifest;
import android.content.Context;
import android.media.AudioManager;

public class MainActivity extends Activity {
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 1);
        
        // Підсилюємо доступ до аудіо
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        am.setMode(AudioManager.MODE_IN_COMMUNICATION);

        WebView w = new WebView(this);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setDomStorageEnabled(true);
        w.getSettings().setMediaPlaybackRequiresUserGesture(false);
        w.setWebChromeClient(new WebChromeClient() {
            public void onPermissionRequest(PermissionRequest r) { r.grant(r.getResources()); }
        });
        w.loadUrl("file:///android_asset/index.html");
        setContentView(w);
    }
}