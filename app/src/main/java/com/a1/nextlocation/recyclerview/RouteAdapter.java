package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.data.Route;

import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder>{

    private Context appContext;
    private List<Route> routeList;
    private CouponAdapter.OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            clickListener.onItemClick(clickedPosition);
        }
    }

    public RouteAdapter(Context context, List<Route> route, CouponAdapter.OnItemClickListener listener){
        appContext = context;
        routeList = route;
        clickListener = listener;
    }

    @NonNull
    @Override
    public RouteAdapter.RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RouteAdapter.RouteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }
}
