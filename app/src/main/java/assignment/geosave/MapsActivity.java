package assignment.geosave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private LatLng current;
    private String lat;
    private String lon;
    private List<Item> items;
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent i = getIntent();
        lat = i.getStringExtra("Lat");
        lon = i.getStringExtra("Lon");
        items = (ArrayList<Item>) i.getSerializableExtra("items");
        currentUser = i.getStringExtra("userId");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnInfoWindowClickListener(this);

        // Add a marker in Sydney and move the camera
        current = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
//        mMap.addMarker(new MarkerOptions().position(current).title("Your Loco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        if(!items.isEmpty()){
            for(Item item : items){
                LatLng curr = new LatLng(Double.parseDouble(item.getmLatitudeText()),
                        Double.parseDouble(item.getmLongitudeText()));
                Marker marker;
                if(currentUser.equals(item.getmUserId())) {
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(curr).title("Open")
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                } else {
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(curr).title("Open")
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                }

                marker.setTag(item);
            }
        }
    }

    public float distance (float lat_a, float lng_a, float lat_b, float lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Float(distance * meterConversion).floatValue();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Item i = (Item) marker.getTag();
        Float distance = distance(Float.parseFloat(lat), Float.parseFloat(lon),
                Float.parseFloat(i.getmLatitudeText()), Float.parseFloat(i.getmLongitudeText()));
//        Toast.makeText(this, distance.toString(), Toast.LENGTH_LONG).show();
        if (distance < 100) {
            Intent intent = new Intent(this, ImageActivity.class);
            intent.putExtra("item", i);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Not close enough", Toast.LENGTH_LONG).show();
        }
    }
}
