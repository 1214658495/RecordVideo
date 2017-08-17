package com.bydauto.recordvideo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by byd_tw on 2017/8/11.
 */

public class GridListFragment extends Fragment{
    private static final String TAG = "GridListFragment";
    private GridViewAdapter adapter;

    public GridListFragment() {
        adapter = null;
    }

    @Override
    public void onAttach(Activity context) {
        Log.e(TAG, "onAttach:加载11111 " );
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView1111");
        View view = inflater.inflate(R.layout.gridview_layout, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        Log.e(TAG, "initView: 111111");
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sr_refresh);
        GridView mGridView = (GridView) view.findViewById(R.id.gv_list);
        GridViewAdapter adapter = new GridViewAdapter(getActivity(), 0, Images.imageThumbUrls, mGridView);
        mGridView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }
}
