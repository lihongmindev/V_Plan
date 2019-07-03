package com.mycompany.vplan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mycompany.vplan.ExaminationActivity;
import com.mycompany.vplan.R;
import com.mycompany.vplan.bean.CardList;
import com.mycompany.vplan.model.DeskModel;

import java.util.List;

public class DeskRecyclerAdapter extends RecyclerView.Adapter<DeskRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<CardList> mCardList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView difficultyLevel;
        TextView subject;
        Button collect;
        Button more;
        TextView title;
        TextView complete;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            difficultyLevel = view.findViewById(R.id.difficulty_level);
            subject =  view.findViewById(R.id.subject);
            collect = view.findViewById(R.id.collect_button);
            more = view.findViewById(R.id.more);
            title = view.findViewById(R.id.title);
            complete = view.findViewById(R.id.complete);
        }

    }
    public DeskRecyclerAdapter(){

    }
    public void UpdateDeskRecyclerAdapter(List<CardList> cardList) {

        this.mCardList = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.examination_item_card,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int podition = holder.getAdapterPosition();
                CardList card = mCardList.get(podition);
                Intent intent = new Intent(mContext,ExaminationActivity.class);
                intent.putExtra(ExaminationActivity.EXAMINATION_NAME,card.getTitle());
                mContext.startActivity(intent);
            }
        });
        holder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        CardList card = mCardList.get(position);
        if (card.getDifficultyLevel() == DeskModel.EASYLEVEL){
            Glide.with(mContext).load(R.drawable.easylevel).into(holder.difficultyLevel);
        }else if (card.getDifficultyLevel() == DeskModel.NORMALLEVEL){
            Glide.with(mContext).load(R.drawable.normallevel).into(holder.difficultyLevel);
        }else {
            Glide.with(mContext).load(R.drawable.highlevel).into(holder.difficultyLevel);
        }
        holder.subject.setText(card.getSubject());
        if (card.isCollect()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.collect.setBackground(mContext.getResources().getDrawable(R.drawable.collect_down));
            }
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.collect.setBackground(mContext.getResources().getDrawable(R.drawable.collect_up));
            }
        }
        holder.title.setText(card.getTitle());
        if (card.isCompelete()){
            holder.complete.setText("已完成");
        }else {
            holder.complete.setText("未完成");
        }
    }
    @Override
    public int getItemCount(){
        return mCardList.size();
    }

}
