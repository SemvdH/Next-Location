package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;
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

        private TextView routeName;

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.routeName = itemView.findViewById(R.id.route_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }

        public void setTextViewText(String text){
            this.routeName = itemView.findViewById(R.id.route_name);
            this.routeName.setText(text);
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_item, parent, false);
        RouteAdapter.RouteViewHolder viewHolder = new RouteAdapter.RouteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RouteAdapter.RouteViewHolder holder, int position) {
        Route route = routeList.get(position);
        holder.setTextViewText(route.getName());
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }
}
