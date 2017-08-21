package com.bydauto.recordvideo;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends Activity implements SurfaceHolder.Callback{
    private static final String TAG = "MainActivity";
    private GridListFragment mGridListFragment;

    IjkMediaPlayer player;
    SurfaceView surface;
    SurfaceHolder surfaceHolder;
//    String path = "/storage/emulated/0/video.mp4";
    //    private String path = "/storage/emulated/0/比亚迪行车记录仪/000002AA.MP4" ;
    private String path = "rtsp://192.168.42.1/live" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: 11111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化播放器
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        surface = (SurfaceView) findViewById(R.id.sv_recordVideo);
        surfaceHolder = surface.getHolder();

        surfaceHolder.addCallback(this);

        if (null == mGridListFragment) {
            mGridListFragment = new GridListFragment();
        }
//        getFragmentManager().beginTransaction().replace(R.id.fl_list, mGridListFragment).commitAllowingStateLoss();
        getFragmentManager().beginTransaction().replace(R.id.fl_list, mGridListFragment).commit();
    }


    public void openVideo(){
        release();

        try {
            player = new IjkMediaPlayer();

            player.setDataSource(path);
            player.setDisplay(surfaceHolder);

            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setScreenOnWhilePlaying(true);
            player.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void release() {
        if (player != null) {
            player.reset();
            player.release();
            player = null;
            AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            am.abandonAudioFocus(null);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        // activity 可见时尝试继续播放
        if (player != null){
            player.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        */
        // activity 不可见时暂停播放
        player.pause();
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) { }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        openVideo();
        player.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) { }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: 11111");
        super.onStart();
    }



    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: 1111111");
        super.onDestroy();

    }

}