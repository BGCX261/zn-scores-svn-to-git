package com.zink.ezequiel.Zcores.pro;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Ezequiel Zink
 * Date: 18/03/13
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class Adapter extends ArrayAdapter<Model> {

    int resource;
    String response;
    Context context;
    //Initialize adapter
    public Adapter(Context context, int resource, List<Model> items) {
        super(context, resource, items);
        this.resource=resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout itemView;
        //Get the current object
        Model model = getItem(position);

        //Inflate the view
        if(convertView==null)
        {
            itemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, itemView, true);
        }
        else
        {
            itemView = (LinearLayout) convertView;
        }
        //Get the text boxes from the listitem.xml file
        TextView finalText =(TextView) itemView.findViewById(R.id.finalScore);
        TextView originalDate =(TextView) itemView.findViewById(R.id.originalScore);
        TextView time =(TextView) itemView.findViewById(R.id.time);
        ImageView rating = (ImageView) itemView.findViewById(R.id.rating);

        //Assign the appropriate data from our alert object above
        finalText.setText(String.valueOf(model.getFinalScore()));
        originalDate.setText(String.valueOf(model.getOriginalScore()));
        time.setText(model.getCreateDate().toString());

        rating.setImageResource(model.isAppproved()? R.drawable.rating_good : R.drawable.rating_bad);

        return itemView;
    }

}