package com.usal.usmanusal.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.usal.usmanusal.Model.Idea;
import com.usal.usmanusal.R;

import java.util.List;

/**
 * Created by Sheriffdeen on 17/07/2017.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
   private List<Idea> ide;

    public MyRecyclerAdapter(List<Idea> ide) {
        this.ide = ide;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row ,parent, false);

        return new MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         Idea idd=ide.get(position);
        holder.idea.setText(idd.getIdea());
    }

    @Override
    public int getItemCount() {
        return ide.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
      private TextView idea;
        public MyViewHolder(View itemView) {
            super(itemView);
            idea=(TextView)itemView.findViewById(R.id.idea_row);
        }
    }
}
