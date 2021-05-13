package com.example.appcarros;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterCarros extends BaseAdapter {

    private List<Carro> carroList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterCarros(Context context, List<Carro> listacarros){
    this.carroList = listacarros;
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return carroList.size();
    }

    @Override
    public Object getItem(int position) {
        return carroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return carroList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte item;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista,null);

            item = new ItemSuporte();
            item.tvModelo = convertView.findViewById(R.id.tvListaModelo);
            item.tvMontadora= convertView.findViewById(R.id.tvListaMontadora);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag(item);
        } else {
            item = (ItemSuporte)convertView.getTag();
        }

        Carro carro = carroList.get(position);
        item.tvModelo.setText("Modelo: " + carro.getModelo());
        item.tvMontadora.setText("Montadora: " + carro.getMontadora());
        item.tvAno.setText("Ano: " + String.valueOf(carro.getAno()));

        if( position % 2 == 0){
            item.layout.setBackgroundColor(Color.rgb(150, 150, 150));
        } else{
            item.layout.setBackgroundColor(Color.rgb(180, 180, 180));
        }

        return convertView;
    }

    private class ItemSuporte{
        TextView tvModelo, tvMontadora, tvAno;
        LinearLayout layout;
    }
}
