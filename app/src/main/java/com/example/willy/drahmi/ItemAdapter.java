package com.example.willy.drahmi;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import  java.util.List;



/**
 * Created by Walid on 02/01/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewBolder> {
    List <item> items;
    Context context;

    public ItemAdapter(List<item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ItemAdapter.MyViewBolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemAdapter.MyViewBolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.MyViewBolder holder, int position) {
        item item = items.get(position);
        holder.display(item,context);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class MyViewBolder extends RecyclerView.ViewHolder {
        item itemerino;
        Context context;
        TextView titre,solde;
        public MyViewBolder(final View itemView){
            super(itemView);

            titre = (TextView) itemView.findViewById(R.id.accountname);
            solde = (TextView) itemView.findViewById(R.id.accountsolde);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context.getApplicationContext(),OutputInput.class);
                    i.putExtra("id",itemerino.getId());
                   itemView.getContext().startActivity(i);

                }
            });
        }

        public  void display (item item , Context context){
            this.itemerino =item;
            this.context=context;
            titre.setText(item.getName());
            solde.setText(item.getSolde());




        }



    }

}
