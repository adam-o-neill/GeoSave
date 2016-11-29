package assignment.geosave;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.location.LocationListener;

import java.util.ArrayList;

public class LocationChangeService extends Service implements LocationListener {
    public LocationChangeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private ArrayList<Item> items;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            items = (ArrayList<Item>) intent.getSerializableExtra("items");
        }

        return START_STICKY;
    }



    public float getDistance(double lat_a, double lng_a, double lat_b, double lng_b )
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
    public void onLocationChanged(Location location) {
        boolean notify = false;
        for(Item item : items){
            double lat = Double.parseDouble(item.getmLatitudeText());
            double lon = Double.parseDouble(item.getmLongitudeText());
            if (getDistance(lat, lon, location.getLatitude(), location.getLongitude()) <= 100){
                notify = true;
            }
        }

        if(notify) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(LocationChangeService.this)
                            .setSmallIcon(R.mipmap.ic_camera)
                            .setContentTitle("GeoSave")
                            .setContentText("There is an image nearby");
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(
                            Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}
