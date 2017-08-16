package com.tianjin.MobileInspection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tianjin.MobileInspection.R;
import com.tianjin.MobileInspection.entity.HiddenType;

import java.util.List;

/**
 * Created by wuchang on 2016-12-16.
 */
public class PlanContentAdapter extends BaseAdapter{

    private Context context;
    private List<HiddenType> data;

    public PlanContentAdapter(Context context){
        this.context=context;
    }

    public void updata(List<HiddenType> data){
        this.data=data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=null;
        if(convertView==null){
            holder=new Holder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_plan_content,null);
            holder.name=(TextView)convertView.findViewById(R.id.tv_name);
            holder.content=(TextView)convertView.findViewById(R.id.tv_num);
            holder.unit=(TextView)convertView.findViewById(R.id.tv_unit);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }
        HiddenType ht=data.get(position);
        holder.name.setText(ht.getTypeName());
        holder.content.setText(ht.getNum()+"");
        holder.unit.setText(ht.getTypeUnit());
        return convertView;
    }

    private class Holder{
        TextView name;
        TextView content;
        TextView unit;
    }
}
