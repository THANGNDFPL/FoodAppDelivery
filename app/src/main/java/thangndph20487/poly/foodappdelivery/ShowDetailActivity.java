package thangndph20487.poly.foodappdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import thangndph20487.poly.foodappdelivery.Domain.FoodDomain;
import thangndph20487.poly.foodappdelivery.Helper.ManagementCart;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView titleTxt,feeTxt,descriptionTxt,addToCartBtn,numberOrderTxt;
    private ImageView plusBtn,minusBtn,picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle(){
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDesc());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1){
                numberOrder = numberOrder - 1;
            }
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView(){
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.feeTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picFood);

    }
}