package com.bigbrandtire.elijah.thirtyminutechallenge;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Elija on 3/19/2018.
 */

public class ListViewAdapter  extends ArrayAdapter<Offers> {
    Context context;
    List<Offers> list;
    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, List<Offers> objects) {
        super(context, resource);
        this.context = context;
        this.list = objects;
    }
    @Override
    public int getCount() {
        if(list != null)
            return list.size();
        return 0;
    }
    @Override
    public Offers getItem(int position) {
        if(list != null)
            return list.get(position);
        return null;
    }
    @Override
    public long getItemId(int position) {
        if(list != null)
            return list.get(position).hashCode();
        return 0;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        //If the listview does not have an xml layout ready set the layout
        if (convertView == null){

            //we need a new holder to hold the structure of the cell
            holder = new Holder();

            //get the XML inflation service
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inflate our xml cell to the convertView
            convertView = inflater.inflate(R.layout.offerslayout, null);

            //Get xml components into our holder class

            holder.textView = (TextView)convertView.findViewById(R.id.textView9);



            //Attach our holder class to this particular cell
            convertView.setTag(holder);

        }else{

            //The listview cell is not empty and contains already components loaded, get the tagged holder
            holder = (Holder)convertView.getTag();

        }

        //Fill our cell with data

        //get our person object from the list we passed to the adapter
        Offers tir = getItem(position);
        // String price = Double.toString(Double.parseDouble(cart.getPrice())*Double.parseDouble(cart.getQty())) ;
        //Fill our view components with data
        holder.textView.setText(tir.getOffers());
        convertView.setBackgroundResource(R.color.white);

        return convertView;
    }
    private class Holder{

        TextView textView;


    }
}