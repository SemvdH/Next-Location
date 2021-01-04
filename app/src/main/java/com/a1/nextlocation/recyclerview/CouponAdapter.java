package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {

    private Context appContext;
    private List<Coupon> couponList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    class CouponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView couponCode;
        private TextView couponReward;

        public CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }


        public void setTextViewName(String text){
            this.couponReward = itemView.findViewById(R.id.coupon_name);
            this.couponReward.setText(text);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition());
        }
    }

    public CouponAdapter(Context context, List<Coupon> coupon, OnItemClickListener listener){
        this.appContext = context;
        this.couponList = coupon;
        this.clickListener = listener;

    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
        return new CouponViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        Coupon coupon = couponList.get(position);
        holder.setTextViewName(coupon.getReward());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

}
