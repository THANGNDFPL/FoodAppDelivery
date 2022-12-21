package thangndph20487.poly.foodappdelivery.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import thangndph20487.poly.foodappdelivery.Domain.FoodDomain;
import thangndph20487.poly.foodappdelivery.IChangeNumberItemsListener;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodDomain> foodDomains, int position, IChangeNumberItemsListener iChangeNumberItemsListener){
        foodDomains.get(position).setNumberInCart(foodDomains.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",foodDomains);
        iChangeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> foodDomains,int position, IChangeNumberItemsListener iChangeNumberItemsListener){
        if (foodDomains.get(position).getNumberInCart() == 1){
            foodDomains.remove(position);
        }else {
            foodDomains.get(position).setNumberInCart(foodDomains.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList",foodDomains);
        iChangeNumberItemsListener.changed();
    }

    public double getTotalFee(){
        ArrayList<FoodDomain> foodDomains = getListCart();
        double fee = 0;
        for (int i = 0; i < foodDomains.size(); i++) {
            fee = fee + foodDomains.get(i).getFee() * foodDomains.get(i).getNumberInCart();
        }
        return fee;
    }
}
