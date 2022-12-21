package thangndph20487.poly.foodappdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import thangndph20487.poly.foodappdelivery.Adapter.CategoryAdapter;
import thangndph20487.poly.foodappdelivery.Adapter.FoodAdapter;
import thangndph20487.poly.foodappdelivery.Domain.CategoryDomain;
import thangndph20487.poly.foodappdelivery.Domain.FoodDomain;
import thangndph20487.poly.foodappdelivery.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewFoodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewFood();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton fab = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MainActivity.class)));
    }

    private void recyclerViewFood() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodList);
        recyclerViewFoodList.setLayoutManager(manager);

        ArrayList<FoodDomain> food = new ArrayList<>();
        food.add(new FoodDomain("Pepperoni Pizza","pop_1","slices pepperoni,mozzerella cheese,fresh oregano,ground black pepper,pizza sauce",9.76));
        food.add(new FoodDomain("Cheese Burger","pop_2","beef, Gouda Cheese, Special Sauce, Lettuce , tomato,",8.79));
        food.add(new FoodDomain("Vegetable Pizza","pop_3","olive oil, Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil",8.5));

        adapter2 = new FoodAdapter(food);
        recyclerViewFoodList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerViewCategoryList);
        recyclerViewCategoryList.setLayoutManager(manager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza","cat_1"));
        category.add(new CategoryDomain("Burger","cat_2"));
        category.add(new CategoryDomain("Hotdog","cat_3"));
        category.add(new CategoryDomain("Drink","cat_4"));
        category.add(new CategoryDomain("Donut","cat_5"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }



}