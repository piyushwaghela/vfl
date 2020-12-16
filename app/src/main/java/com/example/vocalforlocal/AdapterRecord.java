package com.example.vocalforlocal;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.Holer> {

    private Context context;
    private ArrayList<ModelRecord> arrayList;

    public AdapterRecord(Context context, ArrayList<ModelRecord> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_record,parent,false);

        return new Holer(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Holer holder, int position) {

        ModelRecord model = arrayList.get(position);
        String id = model.getId();
        String image = model.getImage();
        String name = model.getName();
        String mobile = model.getMobile();
        String profe = model.getProfe();
        String sd = model.getSd();
        String location = model.getLocation();



        holder.profileIv.setImageURI(Uri.parse(image));
        holder.name.setText(name);
        holder.mobile.setText(mobile);
        holder.profe.setText(profe);
        holder.sd.setText(sd);
        holder.location.setText(location);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holer extends RecyclerView.ViewHolder{

        ImageView profileIv;
        TextView name,mobile,profe,sd,location;

        public Holer(@NonNull View itemView) {
            super(itemView);

            profileIv = itemView.findViewById(R.id.profileIv);
            name = itemView.findViewById(R.id.nametv);
            mobile = itemView.findViewById(R.id.mobiletv);
            profe = itemView.findViewById(R.id.profetv);
            sd = itemView.findViewById(R.id.sdtv);
            location = itemView.findViewById(R.id.locationtv);
        }
    }


}
