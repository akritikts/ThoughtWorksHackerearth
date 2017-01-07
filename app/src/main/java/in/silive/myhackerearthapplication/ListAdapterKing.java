package in.silive.myhackerearthapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by akriti on 7/1/17.
 */

public class ListAdapterKing extends BaseAdapter {
    Context context;
    ArrayList<Integer> res_king_img = new ArrayList<>();
    ArrayList<String> res_king_name = new ArrayList<>();
    ArrayList<String> res_king_battle = new ArrayList<>();
    ArrayList<String> res_king_rating = new ArrayList<>();

    public ListAdapterKing(Context c, ArrayList<Integer> r_img, ArrayList<String> r_name, ArrayList<String> r_battle, ArrayList<String> r_rating) {
        context = c;
        res_king_img = r_img;
        res_king_name = r_name;
        res_king_battle = r_battle;
        res_king_rating = r_rating;
    }

    @Override
    public int getCount() {
        return res_king_name.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_adapter, viewGroup, false);
        holder h = new holder();
        h.king_img = (ImageView) view.findViewById(R.id.king_img);
        h.king_name = (TextView) view.findViewById(R.id.king_name);
        h.king_battle = (TextView) view.findViewById(R.id.king_battle);
        h.king_rating = (TextView) view.findViewById(R.id.king_rating);
        h.king_img.setImageResource(res_king_img.get(i));
        h.king_name.setText(res_king_name.get(i));
        h.king_battle.setText(res_king_battle.get(i));
        h.king_rating.setText(res_king_rating.get(i));
        h.king_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    public class holder {
        ImageView king_img;
        TextView king_name;
        TextView king_battle;
        TextView king_rating;
    }
}
