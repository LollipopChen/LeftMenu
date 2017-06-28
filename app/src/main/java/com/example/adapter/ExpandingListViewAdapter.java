package com.example.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leftmenu.MainActivity;
import com.example.leftmenu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TS-YFZX-CQE on 2017/6/6.
 */

public class ExpandingListViewAdapter extends BaseExpandableListAdapter {
    private  MainActivity mContext;
    private Map<String ,List<String>> mDataset = new HashMap<>();
    /** 第1层 **/
    private List<String> firstMenu = new ArrayList<String>();
    /** 第2层 **/
    private List<String> secondMenu = new ArrayList<String>();
    /** 第3层 **/
    private List<String> thirdMenu = new ArrayList<String>();
    /** 第4层 **/
    private List<String> fouthMenu = new ArrayList<String>();

    /** 父层菜单 **/
    private String[] father_menu = {"通道设置","采集设置","分析设置","显示设置"};
    /** 算法 **/
    private String[] child_menu = {"Signal","FFT","OCT","Overall","AI","Order","Waterfall"};
    /** 图标 **/
    private int[] icon = {R.drawable.ic_icon_chan_setting , R.drawable.ic_icon_acqui_setting ,
            R.drawable.ic_icon_analy_setting , R.drawable.ic_icon_dispaly_setting};

    public ExpandingListViewAdapter(MainActivity mainActivity) {
        this.mContext = mainActivity;
        initDatas();
    }

    /** 设置子菜单 */
    private void initDatas() {
        // 1
        firstMenu.add("通道设置");
        firstMenu.add("标定");
        // 2
        secondMenu.add("采集设置");
        secondMenu.add("预触发");
        secondMenu.add("触发");
        // 3
        thirdMenu.add(child_menu[0]);
        thirdMenu.add(child_menu[1]);
        thirdMenu.add(child_menu[2]);
        thirdMenu.add(child_menu[3]);
        thirdMenu.add(child_menu[4]);
        thirdMenu.add(child_menu[5]);
        thirdMenu.add(child_menu[6]);
        // 4
//        fouthMenu.add("99");
        //将数据存在map中
        mDataset.put(father_menu[0],firstMenu);
        mDataset.put(father_menu[1],secondMenu);
        mDataset.put(father_menu[2],thirdMenu);
        mDataset.put(father_menu[3],fouthMenu);
    }

    @Override
    public int getGroupCount() {//获取父item的数量
        return mDataset.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {//获取某个父item中子item的数量
        return mDataset.get(father_menu[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) { //获取某个父item的所有子item
        return mDataset.get(father_menu[groupPosition]);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {//获取某个子item
        return mDataset.get(father_menu[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPositionId) {//获取某个父item的id
        return groupPositionId;
    }

    @Override
    public long getChildId(int groupPositionId, int childPositionId) {//获取某个父item的某个子item的id
        return childPositionId;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       if(convertView == null){
           LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.explistview_father_item ,null);
       }
        convertView.setTag(R.layout.explistview_father_item , groupPosition);
        convertView.setTag(R.layout.explistview_child_item ,-1);
        TextView tv = (TextView) convertView.findViewById(R.id.father);
        tv.setText(father_menu[groupPosition]);
        Drawable drawable = mContext.getResources().getDrawable(icon[groupPosition]);
        //必须设置图片大小，否则不显示
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(drawable,null , null , null);
        tv.setCompoundDrawablePadding(15);
        tv.setHeight(100);
        return convertView;//获取显示的父view
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.explistview_child_item ,null);
        }
        convertView.setTag(R.layout.explistview_father_item , groupPosition);
        convertView.setTag(R.layout.explistview_child_item ,childPosition);
        TextView tv = (TextView) convertView.findViewById(R.id.child);
        tv.setText(mDataset.get(father_menu[groupPosition]).get(childPosition));
        tv.setHeight(100);
        return convertView;//获取显示的子view
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;//  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    }

    /** 获取父目录的item内容  **/
    public String getCroupContext(int groupPosition){
        return father_menu[groupPosition];
    }
}
