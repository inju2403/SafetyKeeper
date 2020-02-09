package com.example.safetykeeper.CallFragment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetykeeper.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CallListAdapter extends RecyclerView.Adapter<CallListAdapter.ViewHolder> {
    private Activity mActivity;
    private Context mContext;
    private ArrayList<Friend> friends;

    public CallListAdapter(Activity mActivity, Context mContext, ArrayList<Friend> friends) {
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.friends = friends;
    }

    @NonNull
    @Override
    public CallListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);

        itemView.getLayoutParams().width = (int) (getScreenWidth() / 3); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new CallListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CallListAdapter.ViewHolder holder, int position) {
        Friend item = friends.get(position % friends.size());
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }
        else return 1;
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.friend_item_Image_CircleImageView);
            name = itemView.findViewById(R.id.friend_item_Name_TextView);
        }
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }
}

