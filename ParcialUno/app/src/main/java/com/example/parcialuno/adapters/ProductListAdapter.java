package com.example.parcialuno.adapters;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.example.parcialuno.Repository.ProductRepository;
import com.example.parcialuno.entities.Product;
import com.example.parcialuno.viewholder_rv.ProductViewHolder;
import com.example.parcialuno.viewmodel.ProductViewModel;
import org.jetbrains.annotations.NotNull;

public class ProductListAdapter extends ListAdapter<Product, ProductViewHolder> {

    private ProductViewModel viewModel;

    public ProductListAdapter(ProductViewModel viewModel, @NonNull @NotNull DiffUtil.ItemCallback<Product> diffCallback) {
        super(diffCallback);
        this.viewModel = viewModel;
    }

    @NonNull
    @NotNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return ProductViewHolder.create(parent, viewModel, context);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int position) {
        Product current = getItem(holder.getAbsoluteAdapterPosition());
        holder.bind(current);

    }

    public static class WordDiff extends DiffUtil.ItemCallback<Product>{
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem){
            return oldItem.getTitle().equals(newItem.getTitle());
        }

    }
}
