package thangndph20487.poly.foodappdelivery.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import thangndph20487.poly.foodappdelivery.Adapter.CartAdapter;
import thangndph20487.poly.foodappdelivery.Helper.ManagementCart;
import thangndph20487.poly.foodappdelivery.IChangeNumberItemsListener;
import thangndph20487.poly.foodappdelivery.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerCartList;
    private ManagementCart managementCart;
    private TextView taxFeeTxt, deliveryFeeTxt, totalTxt, totalFeeTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton fab = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        fab.setOnClickListener(v -> startActivity(new Intent(CartActivity.this,CartActivity.class)));

        homeBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, MainActivity.class)));
    }

    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCartList.setLayoutManager(manager);
        adapter = new CartAdapter(managementCart.getListCart(), this, new IChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerCartList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;
        totalFeeTxt.setText("$"+itemTotal);
        taxFeeTxt.setText("$"+tax);
        deliveryFeeTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }

    private void initView() {
        recyclerCartList = findViewById(R.id.recyclerCartList);
        taxFeeTxt = findViewById(R.id.taxFeeTxt);
        deliveryFeeTxt = findViewById(R.id.deliveryFeeTxt);
        totalTxt = findViewById(R.id.totalTxt);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView);
    }
}