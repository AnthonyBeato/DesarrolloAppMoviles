package com.example.parcialuno.Repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.parcialuno.api.APIClient;
import com.example.parcialuno.api.APIInterface;
import com.example.parcialuno.dto.ProductList;
import com.example.parcialuno.entities.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ProductRepository {

    private MutableLiveData<List<Product>> productsListLiveData = new MutableLiveData<>();

    private APIInterface apiInterface;

    public MutableLiveData<List<Product>> getAllProducts() {
        return productsListLiveData;
    }

    public ProductRepository(Application application){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<ProductList> call = apiInterface.findAll();
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                if(response.isSuccessful() && response.body() != null){
                    productsListLiveData.setValue(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Log.w("onFailure", t.getMessage());
                call.cancel();
            }
        });


    }
}
