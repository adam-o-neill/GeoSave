package assignment.geosave;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP Lab1 on 29/11/2016.
 */

public class CustomListAdapter extends ArrayAdapter<Item> {
    private final Activity context;
    private ArrayList<Item> items;

    public CustomListAdapter(Activity context, ArrayList<Item> items) {
        super(context, R.layout.my_list, items);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.items = items;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemDesc);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.thumb);
        Bitmap bitmap = decodeBitmap(items.get(position).getImage());

        txtTitle.setText(items.get(position).getDescription());
        imageView.setImageBitmap(bitmap);
        return rowView;

    };

    public static Bitmap decodeBitmap(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
