package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Context appContext;
    private List<Location> locationList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView locationName;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            this.locationName = itemView.findViewById(R.id.location_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }

        public void setTextViewText(String text){
            this.locationName = itemView.findViewById(R.id.location_name);
            locationName.setText(text);
        }
    }

    public LocationAdapter(Context context, List<Location> location, OnItemClickListener listener){
        this.appContext = context;
        this.locationList = location;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item, parent, false);
        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {
        Location location = locationList.get(position);
        holder.setTextViewText(location.getName());
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
