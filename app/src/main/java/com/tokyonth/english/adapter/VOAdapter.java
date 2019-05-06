package com.tokyonth.english.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tokyonth.english.model.ItemVO;
import com.tokyonth.english.R;
import com.tokyonth.english.ui.CartoonTextView;
import com.tokyonth.english.ui.NetImageView;

import java.util.List;

public class VOAdapter extends RecyclerView.Adapter<VOAdapter.ViewHolder> implements View.OnClickListener {

    private List<ItemVO> mList;
    private OnItemClickListener mOnItemClickListener;
    private Context context;

    public VOAdapter(List<ItemVO> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.rv_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ItemVO vo = mList.get(position);
        holder.text.setText(vo.getmName());
        //holder.img.setImageURL(vo.getmImg());
       // holder.img.setImageResource(vo.getmImg());
        Glide.with(context).load(vo.getmImg()).into(holder.img);
        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public CartoonTextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_img);
            text = (CartoonTextView) itemView.findViewById(R.id.item_text);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener = onItemClickListener;
    }

}


