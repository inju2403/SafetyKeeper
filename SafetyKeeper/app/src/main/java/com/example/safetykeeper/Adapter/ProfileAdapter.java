package com.example.safetykeeper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetykeeper.Model.Profile;
import com.example.safetykeeper.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {
    private ArrayList<Profile> mProfile;
    private LayoutInflater mInflate;
    private Context mContext;


    public ProfileAdapter(Context context, ArrayList<Profile> profileList) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mProfile = profileList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflate.inflate(R.layout.profile_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Profile item = mProfile.get(position);
        holder.setItem(item);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, item.getName(), Toast.LENGTH_SHORT).show(); }
        });
    }

    //holder.name.setText(mProfile.get(position).name);
    //holder.image.setImageResource(mProfile.get(position).image);

    @Override
    public int getItemCount() {
        return mProfile.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView name;
        public CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.profileName);
            image =  itemView.findViewById(R.id.profileImage);
            cardView =itemView.findViewById(R.id.profileCardView);
        }

        public void setItem(Profile item) {
            name.setText(item.getName());
            image.setImageResource(item.getImage());
        }
    }
}