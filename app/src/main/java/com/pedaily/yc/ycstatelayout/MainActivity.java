package com.pedaily.yc.ycstatelayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.ns.yc.ycstatelib.OnNetworkListener;
import com.ns.yc.ycstatelib.OnRetryListener;
import com.ns.yc.ycstatelib.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/7/6
 * 描    述：主页面
 * 修订历史：
 * ================================================
 */

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<String> lists = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initStatusLayout() {
        statusLayoutManager = StateLayoutManager.newBuilder(this)
                .contentView(R.layout.activity_main)
                .emptyDataView(R.layout.activity_emptydata)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading)
                .netWorkErrorView(R.layout.activity_networkerror)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        showContent();
                    }
                })
                .onNetworkListener(new OnNetworkListener() {
                    @Override
                    public void onNetwork() {
                        showLoading();
                    }
                })
                .build();
    }

    @Override
    protected void initView() {
        showLoading();
        initViewContent();
        initData();
        initRecycleView();
    }

    private void initViewContent() {
        Button btn_empty = (Button) findViewById(R.id.btn_empty);
        Button btn_error = (Button) findViewById(R.id.btn_error);
        Button btn_network_error = (Button) findViewById(R.id.btn_network_error);
        Button btn_test = (Button) findViewById(R.id.btn_test);
        Button btn_test2 = (Button) findViewById(R.id.btn_test2);
        Button btn_test3 = (Button) findViewById(R.id.btn_test3);
        btn_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initEmptyDataView();
            }
        });
        btn_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initErrorDataView();
            }
        });
        btn_network_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSettingNetwork();
            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });
        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Test2Activity.class));
            }
        });
        btn_test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Test3Activity.class));
            }
        });
    }

    private void initRecycleView() {
//        showContent();
//        showEmptyData();
//        showError();
//        showLoading();
//        showNetWorkError();
//        statusLayoutManager.showLoading();
//        statusLayoutManager.showContent();


        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(lists,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HhItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(lists.size()>position && position>-1){
                    Toast.makeText(MainActivity.this,"条目"+position+"被点击呢",Toast.LENGTH_SHORT).show();
                }
            }
        });
        showContent();
    }

    protected void initData() {
        lists.clear();
        for(int a=0 ; a<50 ; a++){
            lists.add("这是第"+a+"条数据");
        }
    }


    /**
     * 点击重新刷新数据
     */
    private void initEmptyDataView() {

        statusLayoutManager.showEmptyData();
    }

    /**
     * 点击重新刷新
     */
    private void initErrorDataView() {
        statusLayoutManager.showError();
    }

    /**
     * 点击设置网络
     */
    private void initSettingNetwork() {
        statusLayoutManager.showNetWorkError();
    }

}
