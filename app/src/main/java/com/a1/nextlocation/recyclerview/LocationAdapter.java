package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.data.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Context appContext;
    private List<Location> locationList;
    private CouponAdapter.OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }
    }

    public LocationAdapter(Context context, List<Location> location, CouponAdapter.OnItemClickListener listener){
        appContext = context;
        locationList = location;
        clickListener = listener;
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
