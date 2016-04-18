package com.aleksodem.tasklightitdemchenko.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksodem.tasklightitdemchenko.R;
import com.aleksodem.tasklightitdemchenko.model.pojo.Product;
import com.aleksodem.tasklightitdemchenko.utils.Constants;
import com.aleksodem.tasklightitdemchenko.view.IListProductListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterListProduct extends RecyclerView.Adapter<AdapterListProduct.ViewHolder> {

    private List<Product> products;
    private Context context;
    private IListProductListener listener;

    public AdapterListProduct( Context context, IListProductListener listener) {
        this.products = new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    public void addList(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      //  holder.image.setImageResource(products.get(position).getImg());
        holder.title.setText(products.get(position).getTitle());
        holder.description.setText(products.get(position).getText());

        Picasso.with(context)
                .load(Constants.IMG_URL + products.get(position).getImg())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openInfoProduct(products.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.image));
            title = ((TextView) itemView.findViewById(R.id.title));
            description = ((TextView) itemView.findViewById(R.id.description));
        }
    }
}
