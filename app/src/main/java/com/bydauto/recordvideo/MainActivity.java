package com.bydauto.recordvideo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private GridListFragment mGridListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: 11111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == mGridListFragment) {
            mGridListFragment = new GridListFragment();
        }
//        getFragmentManager().beginTransaction().replace(R.id.fl_list, mGridListFragment).commitAllowingStateLoss();
        getFragmentManager().beginTransaction().replace(R.id.fl_list, mGridListFragment).commit();
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: 11111");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: 111111");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: 1111111");
        super.onDestroy();
        // 退出程序时结束所有的下载任务
//        adapter.cancelAllTasks();
    }

}