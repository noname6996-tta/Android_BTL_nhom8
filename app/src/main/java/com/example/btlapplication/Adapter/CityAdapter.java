package com.example.btlapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlapplication.Modal.City;
import com.example.btlapplication.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>{

    private List<City> mListCity;
    private Context mContext;

    public CityAdapter(List<City> mListCity, Context mContext) {
        this.mListCity = mListCity;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_city,parent,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = mListCity.get(position);
        if (city == null){
            return;
        }
        holder.tv_CityName.setText("Thành phố:"+city.getNameCity());
        Glide.with(mContext).load(city.getImageCity()).error(R.drawable.ic_launcher_background).into(holder.img_City);
    }

    @Override
    public int getItemCount() {
        if (mListCity != null){
            return mListCity.size();
        }
        return 0;
    }

    public class CityViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_CityName;
        private ImageView img_City;
        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_CityName = itemView.findViewById(R.id.tv_CityName);
            img_City = itemView.findViewById(R.id.img_City);
        }
    }
}
