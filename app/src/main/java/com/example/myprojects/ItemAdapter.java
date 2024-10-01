package com.example.myprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private static List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.nameTextView.setText(currentItem.getDescription());
        holder.priceTextView.setText(String.valueOf(currentItem.getPrice()));
        holder.categoryTextView.setText(currentItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private TextView priceTextView;
        private TextView categoryTextView;
        Button btndelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            btndelete=itemView.findViewById(R.id.viewdelete);
            btndelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           Item item=new Item();
           int position=getAdapterPosition();
           int ID=items.get(position).getId();
           item.setId(ID);

           Dashboard.roomdatabase.itemDao().delete(item);
           Dashboard.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ViewFragment(),null).commit();
        }
    }
}

