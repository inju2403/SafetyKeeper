package com.example.safetykeeper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetykeeper.Model.Person;
import com.example.safetykeeper.R;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Person> items;

    public PersonAdapter(Context context, ArrayList<Person> personList) {
        this.context = context;
        items = personList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView personTextView;
        private Switch onOffSwitch;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personTextView = itemView.findViewById(R.id.personTextView);

            // 위치 공유 switch
            onOffSwitch = itemView.findViewById(R.id.onOffSwitch);

            onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        Toast.makeText(context, "on", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context, "off", Toast.LENGTH_LONG).show();
                }
            });
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
