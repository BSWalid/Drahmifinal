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

public class outPutItemAdapter extends RecyclerView.Adapter<outPutItemAdapter.MyViewBolder> {
    List <inputitem> items;
    Context context;

    public outPutItemAdapter(List<inputitem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public outPutItemAdapter.MyViewBolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemoutput,parent,false);
        return new outPutItemAdapter.MyViewBolder(view);
    }



    @Override
    public void onBindViewHolder(outPutItemAdapter.MyViewBolder holder, int position) {
        inputitem item = items.get(position);
        holder.display(item,context);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class MyViewBolder extends RecyclerView.ViewHolder {
        inputitem itemerino;
        Context context;
        TextView objet,solde,date,time;
        public MyViewBolder(final View itemView){
            super(itemView);

            objet = (TextView) itemView.findViewById(R.id.objectif);
            solde = (TextView) itemView.findViewById(R.id.solde);
            date=(TextView) itemView.findViewById(R.id.date);
            time=(TextView) itemView.findViewById(R.id.time);

        }

        public  void display (inputitem item , Context context){
            this.itemerino =item;
            this.context=context;
            objet.setText(item.getObjectif());
            solde.setText(item.getAmmount()+"$");
            date.setText(item.getDate());
            time.setText(item.getTime());




        }



    }

}
