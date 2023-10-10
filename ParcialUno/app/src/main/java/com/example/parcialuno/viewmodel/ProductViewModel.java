package com.example.parcialuno.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.parcialuno.Repository.ProductRepository;
import com.example.parcialuno.entities.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private final MutableLiveData<List<Product>> allProducts;

    public ProductViewModel(@NotNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        this.allProducts = repository.getAllProducts();
    }

    public MutableLiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
}
