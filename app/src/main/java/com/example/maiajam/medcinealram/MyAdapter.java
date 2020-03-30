package com.example.maiajam.medcinealram;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maiAjam on 9/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {



    List<Medcine> ListMed ;
    Context context ;
    Mysql db ;


  public MyAdapter(List<Medcine> listmedc,Context con)
    {
            this.ListMed = listmedc ;
            this.context = con ;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recrow,parent,false);

        Holder vH = new Holder(view);
        return vH ;
    }

    public void addItem(List<Medcine> list) {
     this.ListMed = list ;
        notifyItemInserted(list.size());
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {

        final Medcine medcine = ListMed.get(position);
        final int medid=medcine.getMedcineId();
        String medName = medcine.getMedcindeName();
        String medDesc = medcine.getMedcineDesc();
        int medose = medcine.getMedDose();

        holder.imageViewDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.imageViewDet);
                popupMenu.getMenuInflater().inflate(R.menu.popmenu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int itemId = item.getItemId();
                        switch (itemId)
                        {
                            case R.id.action_delet:
                                db = new Mysql(context);
                                db.delete(medid);
                                break;

                            case R.id.action_edit:
                                Intent i = new Intent(context,AddMedcine.class);
                                i.putExtra("med_id",medid);
                                context.startActivity(i);
                                break;
                        }

                        return false;
                    }
                });


            }
        });


        holder.med_Name.setText(medName);
        holder.med_desc.setText(medDesc);
        if(medcine.getNoTime()== 1)
        {
            holder.remAt.setText(context.getResources().getString(R.string.oneTime));
        }else if(medcine.getNoTime() == 2)
        {
            holder.remAt.setText(context.getResources().getString(R.string.twice));
        }
        else
        {
            holder.remAt.setText(context.getResources().getString(R.string.three));
        }

        holder.RemTime.setText(String.valueOf(medose));

    }

    @Override
    public int getItemCount() {
        return ListMed.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        TextView med_Name,med_desc,remAt,RemTime;
        CardView cardView ;
        ImageView imageViewDet ;
        public Holder(View itemView) {
            super(itemView);

            cardView =(CardView) itemView.findViewById(R.id.card_view);
            imageViewDet =(ImageView)itemView.findViewById(R.id.imDet);
            this.med_Name =(TextView) itemView.findViewById(R.id.med_name);
           this.med_desc =(TextView) itemView.findViewById(R.id.med_desc);
            this.remAt =(TextView) itemView.findViewById(R.id.RemAt);
            this.RemTime =(TextView) itemView.findViewById(R.id.timeTxt);

        }
    }

}
