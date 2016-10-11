package com.jzheadley.eat.views.adapters;
import android.widget.Button;
import android.content.Context;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.jzheadley.eat.R;



/**
 * Created by Mit on 10/9/16.
 */

public class arrayAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Button> catList = new ArrayList<Button>();
    private Context context;

    public arrayAdapter (ArrayList<Button> list, Context context){
        this.catList = list;
        this.context = context;
    }
   @Override
    public int getCount () {
       return catList.size();
   }
    @Override
    public Object getItem(int pos) {
        return catList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return catList.get(pos).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_input, null);
        }


        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        Button addBtn = (Button)view.findViewById(R.id.add_btn_meal);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // ToDo:
            }

            @Override
            public String toString() {
                return "$classname{}";
            }



        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
