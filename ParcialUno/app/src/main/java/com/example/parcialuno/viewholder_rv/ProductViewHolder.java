package com.example.parcialuno.viewholder_rv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parcialuno.DisplayProductActivity;
import com.example.parcialuno.R;
import com.example.parcialuno.Repository.ProductRepository;
import com.example.parcialuno.entities.Product;
import com.example.parcialuno.viewmodel.ProductViewModel;
import org.jetbrains.annotations.NotNull;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ProductViewModel viewModel;
    private final TextView title;
    private final TextView description;
    private Context context;


    public ProductViewHolder(ProductViewModel viewModel, @NonNull @NotNull View itemView, Context context) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        this.viewModel = viewModel;
        this.context = context;
    }


    public void bind(Product product){
        title.setText(product.getTitle());
        description.setText(product.getDescription());

        //Clickear itemView para abrir el producto especifico
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int productId = product.getId();
                Intent intent = new Intent(context, DisplayProductActivity.class);
                intent.putExtra("productId", productId);
                Log.d("productId:", "id: " + productId); //Mandar el id seleccionado por parámetro

                context.startActivity(intent);
            }
        });
    }

    public static ProductViewHolder create(ViewGroup parent, ProductViewModel viewModel, Context context){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv, parent, false);
        return new ProductViewHolder(viewModel, view, parent.getContext());
    }
}
