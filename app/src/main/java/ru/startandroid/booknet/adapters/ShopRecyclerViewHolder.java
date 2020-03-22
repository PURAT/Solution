package ru.startandroid.booknet.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.startandroid.booknet.R;


public class ShopRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView productImage;

    public TextView productName;

    public ShopRecyclerViewHolder(View itemView) {
        super(itemView);
        productImage = (ImageView) itemView.findViewById(R.id.product_image);
        productName = (TextView) itemView.findViewById(R.id.product_name);

    }
}
