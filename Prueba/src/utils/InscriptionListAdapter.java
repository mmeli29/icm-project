package utils;

import java.util.ArrayList;

import com.example.prueba.R;

import modelo.Inscription;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InscriptionListAdapter extends BaseAdapter {
	
	 private ArrayList<Inscription> listData;
	 
	    private LayoutInflater layoutInflater;
	 
	    public InscriptionListAdapter (Context context, ArrayList<Inscription> listData) {
	        this.listData = listData;
	        layoutInflater = LayoutInflater.from(context);
	    }
	 
	    @Override
	    public int getCount() {
	        return listData.size();
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return listData.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }
	 
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder;
	        if (convertView == null) {
	            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
	            holder = new ViewHolder();
	            holder.inscriptionNameView = (TextView) convertView.findViewById(R.id.inscription_name);
	            holder.inscriptionClassroomView = (TextView) convertView.findViewById(R.id.inscription_classroom);
	            holder.inscriptionBuildingView = (TextView) convertView.findViewById(R.id.inscription_building);
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	 
	        holder.inscriptionNameView.setText( ((Inscription)listData.get(position)).getNameSubject() );
	        holder.inscriptionClassroomView.setText("Aula: " + ((Inscription)listData.get(position)).getClassroomName());
	        holder.inscriptionBuildingView.setText("Edificio: " + ((Inscription)listData.get(position)).getBuildingName());
	 
	        return convertView;
	    }
	 
	    static class ViewHolder {
	        TextView inscriptionNameView;
	        TextView inscriptionClassroomView;
	        TextView inscriptionBuildingView;
	    }
	 
}

