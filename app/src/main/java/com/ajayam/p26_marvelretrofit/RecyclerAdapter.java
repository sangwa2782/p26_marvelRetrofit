package com.ajayam.p26_marvelretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.CookieHandler;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Results> result;
    public RecyclerAdapter( Context context, List<Results> result) {
        this.context = context;
        this.result = result;
    }

    public void updatemovielist(List<Results> list) {
        this.result = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_show, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
        return new RecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(result.get(position).getName());
//        holder.Image.setImageResource(result.get(position).getImageurl());

        Glide.with(context)
                .load(result.get(position).getImageurl())
                .into(holder.Image);

        holder.Bio.setText(result.get(position).getBio());
        holder.Publisher.setText(result.get(position).getPublisher());
        holder.CreatedBy.setText(result.get(position).getCreatedby());
        holder.Team.setText(result.get(position).getTeam());
    }

    @Override
    public int getItemCount() {
        if (this.result!=null)
            return this.result.size();
          else
              return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, Bio, Publisher, CreatedBy, Team;
        ImageView Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.Name);
            Image = itemView.findViewById(R.id.Image);
            Bio = itemView.findViewById(R.id.Bio);
            Publisher = itemView.findViewById(R.id.Publisher);
            CreatedBy = itemView.findViewById(R.id.CreatedBy);
            Team = itemView.findViewById(R.id.Team);
        }
    }
}
