package com.example.btlapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlapplication.Fragment.ListStoreFragment;
import com.example.btlapplication.Fragment.StoreItem;
import com.example.btlapplication.Modal.City;
import com.example.btlapplication.Modal.Restaurant;
import com.example.btlapplication.R;

import java.io.Serializable;
import java.util.List;

public class ListStoreAdapter extends RecyclerView.Adapter<ListStoreAdapter.RestaurantViewHolder>{
    private List<Restaurant> mRestaurant;
    private Context mContext;

    public ListStoreAdapter(List<Restaurant> mRestaurant, Context mContext) {
        this.mRestaurant = mRestaurant;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_liststore,parent,false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = mRestaurant.get(position);
        Log.e("CCCC",restaurant.toString());
        if (restaurant == null){
            return;
        }
        holder.tv_NameStore.setText("Thành phố:"+restaurant.getName_res());
        Glide.with(mContext).load(restaurant.getImg_res5()).error(R.drawable.ic_launcher_background).into(holder.img_store);
        holder.tv_AddressStore.setText("Địa chỉ: "+restaurant.getAddress_res());
        holder.tv_StarStore.setText(restaurant.getStar_res());
        holder.items_card_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, StoreItem.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("restaurant",restaurant);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mRestaurant != null){
            return mRestaurant.size();
        }
        return 0;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        private CardView items_card_store;
        private ImageView img_store;
        private TextView tv_NameStore,tv_AddressStore,tv_StarStore;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            items_card_store = itemView.findViewById(R.id.items_card_store);
            img_store = itemView.findViewById(R.id.img_store);
            tv_NameStore = itemView.findViewById(R.id.tv_NameStore);
            tv_AddressStore = itemView.findViewById(R.id.tv_AddressStore);
            tv_StarStore = itemView.findViewById(R.id.tv_StarStore);
        }
    }
}
