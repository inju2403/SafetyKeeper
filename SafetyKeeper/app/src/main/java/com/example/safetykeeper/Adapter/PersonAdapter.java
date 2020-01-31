package com.example.safetykeeper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetykeeper.Model.Person;
import com.example.safetykeeper.R;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    Context context;
    ArrayList<Person> items;

    public PersonAdapter(Context context, ArrayList<Person> personList) {
        this.context = context;
        items = personList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView personTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personTextView = itemView.findViewById(R.id.personTextView);
        }
        public void setItem(Person item) {
            personTextView.setText(item.getName());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
