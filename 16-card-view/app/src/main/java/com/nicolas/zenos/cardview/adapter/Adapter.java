package com.nicolas.zenos.cardview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicolas.zenos.cardview.R;
import com.nicolas.zenos.cardview.model.Posts;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Posts> listPosts;

    public Adapter(List<Posts> posts) {
        this.listPosts = posts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_details, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Posts posts = listPosts.get(position);
        holder.textName.setText(posts.getName());
        holder.textPost.setText(posts.getPost());
        holder.postImage.setImageResource(posts.getImage());

    }

    @Override
    public int getItemCount() {
        return listPosts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private ImageView postImage;
        private TextView textPost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            postImage = itemView.findViewById(R.id.postImage);
            textPost = itemView.findViewById(R.id.textPost);
        }
    }

}
