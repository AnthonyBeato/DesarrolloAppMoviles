package com.example.parcialuno;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.parcialuno.api.APIClient;
import com.example.parcialuno.api.APIInterface;
import com.example.parcialuno.dto.ProductSingle;
import com.example.parcialuno.entities.Product;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayProductActivity extends AppCompatActivity {

    private ProductSingle product;
    private ImageView productImage;
    private TextView title;
    private TextView description;
    private TextView price;
    private TextView discountPercentage;
    private TextView rating;
    private TextView stock;
    private TextView brand;
    private TextView category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);

        APIInterface api = APIClient.getClient().create(APIInterface.class);

        int productId = getIntent().getIntExtra("productId", -1);
        Log.d("DisplayProductActivity", "id mandado: " + productId);
        productImage = findViewById(R.id.productThumbnail);
        title = findViewById(R.id.titleTextView);
        description = findViewById(R.id.descriptionTextView);
        price = findViewById(R.id.priceTextView);

        discountPercentage = findViewById(R.id.discountPercentageTextView);
        rating = findViewById(R.id.ratingTextView);
        stock = findViewById(R.id.stockTextView);
        brand = findViewById(R.id.brandTextView);
        category = findViewById(R.id.categoryTextView);

        api.find(productId).enqueue(new Callback<ProductSingle>() {
            @Override
            public void onResponse(@NotNull Call<ProductSingle> call, @NotNull Response<ProductSingle> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //Log.w("onResponse", response.body().getProduct().getTitle());

                    product = response.body();
                    title.setText(product.getTitle());
                    description.setText(product.getDescription());
                    price.setText(String.valueOf(product.getPrice()));
                    Picasso.get()
                            .load(product.getThumbnail())
                            .into(productImage);
                    discountPercentage.setText(String.valueOf(product.getDiscountPercentage()));
                    rating.setText(String.valueOf(product.getRating()));
                    stock.setText(String.valueOf(product.getStock()));
                    brand.setText(product.getBrand());
                    category.setText(product.getCategory());



                }
            }

            @Override
            public void onFailure(Call<ProductSingle> call, Throwable t) {
                Log.w("onFailure", t.getLocalizedMessage());
                call.cancel();
            }
        });
    }
}