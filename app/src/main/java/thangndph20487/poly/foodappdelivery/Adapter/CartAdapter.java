package thangndph20487.poly.foodappdelivery.Adapter;

import android.content.Context;
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
import thangndph20487.poly.foodappdelivery.Helper.ManagementCart;
import thangndph20487.poly.foodappdelivery.IChangeNumberItemsListener;
import thangndph20487.poly.foodappdelivery.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private IChangeNumberItemsListener iChangeNumberItemsListener;

    public CartAdapter(ArrayList<FoodDomain> foodDomains, Context context, IChangeNumberItemsListener iChangeNumberItemsListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.iChangeNumberItemsListener = iChangeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee())*100)/100));
        holder.numberItemTxt.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusCartBtn.setOnClickListener(v -> managementCart.plusNumberFood(foodDomains, position, () -> {
            notifyDataSetChanged();
            iChangeNumberItemsListener.changed();
        }));

        holder.minusCartBtn.setOnClickListener(v -> managementCart.minusNumberFood(foodDomains, position, () -> {
            notifyDataSetChanged();
            iChangeNumberItemsListener.changed();
        }));
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem,totalEachItem,numberItemTxt;
        ImageView pic,plusCartBtn,minusCartBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            numberItemTxt = itemView.findViewById(R.id.numberItemTxt);
            pic = itemView.findViewById(R.id.pic);
            plusCartBtn = itemView.findViewById(R.id.plusCartBtn);
            minusCartBtn = itemView.findViewById(R.id.minusCartBtn);

        }
    }
}
