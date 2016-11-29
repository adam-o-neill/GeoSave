package assignment.geosave;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent i = getIntent();
        Item item = (Item) i.getSerializableExtra("item");

        ImageView imageView = (ImageView) findViewById(R.id.fullImage);
        imageView.setImageBitmap(decodeBitmap(item.getImage()));
        TextView descImg = (TextView) findViewById(R.id.descImg);
        if (item.getDescription() != null){
            descImg.setText(item.getDescription());
        }
    }

    public static Bitmap decodeBitmap(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
