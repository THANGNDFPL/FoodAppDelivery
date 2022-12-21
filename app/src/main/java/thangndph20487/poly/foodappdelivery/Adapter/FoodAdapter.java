package thangndph20487.poly.foodappdelivery.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import thangndph20487.poly.foodappdelivery.Domain.FoodDomain;
import thangndph20487.poly.foodappdelivery.R;
import thangndph20487.poly.foodappdelivery.ShowDetailActivity;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    ArrayList<FoodDomain> foodDomains;

    public FoodAdapter(ArrayList<FoodDomain> foodDomains) {
        this.foodDomains = foodDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foodTitle.setText(foodDomains.get(position).getTitle());
        holder.foodFee.setText(String.valueOf(foodDomains.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.foodPic);

        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object",foodDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodTitle,foodFee,addBtn;
        ImageView foodPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTitle = itemView.findViewById(R.id.title);
            foodFee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
            foodPic = itemView.findViewById(R.id.foodPic);
        }
    }
}
