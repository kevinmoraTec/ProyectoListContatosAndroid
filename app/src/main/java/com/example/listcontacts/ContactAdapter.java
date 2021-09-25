package com.example.listcontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.listcontacts.database.Contact;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private ArrayList<Contact> contactArrayList;

    public ContactAdapter(Context context, ArrayList<Contact> contactArrayList) {
        super(context, R.layout.list_contact);
        this.context = context;
        this.contactArrayList = contactArrayList;
    }
    //este método permite obtener la vista a partir de una fila determinada(position)
    //la vista que representa la lista se pasa por parámetro al método(convertView)

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        View view;
        final ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_contact, parent, false);
            viewHolder.mContactName = (TextView) view.findViewById(R.id.adapterContactName);
            viewHolder.mContactImg = (ImageView) view.findViewById(R.id.adapterContactImage);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            view=convertView;
        }

        viewHolder.mContactName.setText(contactArrayList.get(position).getNombre());
//        viewHolder.mContactImg.setImageResource(contactArrayList.get(position).getFoto());
        Glide.with(context)
                .load(contactArrayList.get(position).getFoto())
                .into(viewHolder.mContactImg);
        return view;

    }
    static class ViewHolder{
        protected TextView mContactName;
        protected ImageView mContactImg;
    }
}
