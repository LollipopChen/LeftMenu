package com.example.leftmenu;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ExpandingListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnGroupClickListener,
        ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupExpandListener {
    /**可扩展的listview**/
    private ExpandableListView expand_listview;
    /**左侧拉 **/
    private DrawerLayout left_drawer;
    /**可扩展listview的适配器**/
    private ExpandingListViewAdapter ev_adapter;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.left_layout);

        initView();
        initEvent();

    }

    /** 初始化控件 **/
    private void initView() {
        left_drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        expand_listview = (ExpandableListView) findViewById(R.id.expand_listview);
        textview = (TextView) findViewById(R.id.textview);
        ev_adapter = new ExpandingListViewAdapter(this);
        expand_listview.setAdapter(ev_adapter);
        //将左侧拉的阴影去掉
        left_drawer.setScrimColor(Color.TRANSPARENT);
        //去掉可扩展listview的自带的箭头
        expand_listview.setGroupIndicator(null);
    }

    /** 交互事件处理 **/
    private void initEvent() {
        //设置父菜单的点击事件
        expand_listview.setOnGroupClickListener(this);
        //设置子菜单的点击事件
        expand_listview.setOnChildClickListener(this);
        //监听菜单是否展开
        expand_listview.setOnGroupExpandListener(this);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        //一级菜单(父菜单)的点击事件
//        Toast.makeText(MainActivity.this,"点击："+groupPosition,Toast.LENGTH_SHORT).show();
        switch (groupPosition) {
            case 0:
                textview.setText("" + ev_adapter.getGroup(groupPosition));
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                textview.setText("" + ev_adapter.getCroupContext(groupPosition));
                break;
        }
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        //子菜单的点击事件
//        Toast.makeText(MainActivity.this,"点击子菜单："+childPosition,Toast.LENGTH_SHORT).show();
        switch (groupPosition) {
            case 0:
                selectFirstChildItem(groupPosition, childPosition);
                break;

            case 1:
                selectSecondChildItem(groupPosition, childPosition);
                break;

            case 2:
                selectThirdChildItem(groupPosition, childPosition);
                break;

            case 3:

                break;
        }
        return false;
    }

    /**
     * 第一层子目录的点击事件
     * @param groupPosition
     * @param childPosition
     */
    private void selectFirstChildItem(int groupPosition, int childPosition) {
        switch (childPosition) {
            case 0:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 1:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;
        }
    }

    /**
     * 第2层子目录的点击事件
     *
     * @param groupPosition
     * @param childPosition
     */
    private void selectSecondChildItem(int groupPosition, int childPosition) {
        switch (childPosition) {
            case 0:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 1:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 2:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;
        }
    }

    /**
     * 第3层子目录的点击事件
     *
     * @param groupPosition
     * @param childPosition
     */
    private void selectThirdChildItem(int groupPosition, int childPosition) {
        switch (childPosition) {
            case 0:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 1:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 2:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 3:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;
            case 4:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 5:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;

            case 6:
                textview.setText("" + ev_adapter.getChild(groupPosition, childPosition));
                break;
        }

    }

    @Override
    public void onGroupExpand(int groupPosition) {
        for (int i = 0, size = ev_adapter.getGroupCount(); i < size; i++) {
            if (groupPosition != i) {
                expand_listview.collapseGroup(i);  //关闭没选中的分组
            }
        }
    }

}
