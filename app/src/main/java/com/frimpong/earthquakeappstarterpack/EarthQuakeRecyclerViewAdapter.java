package com.frimpong.earthquakeappstarterpack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {

    private List<EarthQuakeItem> earthquakeList;
    Context context;

    public EarthquakeAdapter(List<EarthQuakeItem> earthquakeList, Context context) {
        this.earthquakeList = earthquakeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_item, parent, false);
        return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        EarthQuakeItem earthquake = earthquakeList.get(position);
        holder.title.setText(earthquake.getName());
        holder.date.setText(earthquake.getDate());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullView.class);
                intent.putExtra("name", earthquake.getName());
                intent.putExtra("date", earthquake.getDate());
                intent.putExtra("description", earthquake.getDescription());
                // If you want to pass more info about the item on to the next page, do it here as shown above
                context.startActivity(intent);
                System.out.println("Just been clicked here mate!");
            }
        });
    }

    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }

    public static class EarthquakeViewHolder extends RecyclerView.ViewHolder {

         TextView title;
         TextView date;

         LinearLayout card;

        public EarthquakeViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            date = itemView.findViewById(R.id.dateTextView);
            card = itemView.findViewById(R.id.cardItself);
        }
    }
}

