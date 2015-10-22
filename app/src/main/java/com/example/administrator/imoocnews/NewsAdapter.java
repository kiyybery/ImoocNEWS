package com.example.administrator.imoocnews;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22 0022.
 */
public class NewsAdapter extends BaseAdapter{

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    public NewsAdapter(Context ctx,List<ItemBean> list){

        mInflater = LayoutInflater.from(ctx);
        mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(view == null){

            view = mInflater.inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            view.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.iv_icon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.tv_title.setText(mList.get(i).newstitle);
        viewHolder.tv_content.setText(mList.get(i).newscontent);
        return view;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_content;
    }
}
