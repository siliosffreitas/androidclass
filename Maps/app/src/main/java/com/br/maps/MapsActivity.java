package com.br.maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // localização
    private Location lastRegisteredLocation;
    private LocationManager locationManager;
    private LocationProvider gpsLocationProvider;
    private LocationProvider networkProvider;

    private MarkerOptions marker;


    private static final int EXT_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    private void capturarPosicao() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsLocationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        networkProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);

        if (gpsLocationProvider != null && locationManager.isProviderEnabled(gpsLocationProvider.getName())) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(gpsLocationProvider.getName(), 100000, 20, gpsLocationListener);
        }

        if (networkProvider != null && locationManager.isProviderEnabled(networkProvider.getName())) {
            locationManager.requestLocationUpdates(networkProvider.getName(), 100000, 20, networkLocationListener);
        }
    }

    private LocationListener gpsLocationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {
            String statusAsString = "Available";
            if (status == LocationProvider.OUT_OF_SERVICE)
                statusAsString = "Out of service";
            else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE)
                statusAsString = "Temporarily Unavailable";
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }

        public void onLocationChanged(Location location) {
            if (mMap != null) {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(MapsActivity.this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);

                // posicao do usuario capturada
                setNewLocation(location);
            }
        }
    };

    private LocationListener networkLocationListener = new LocationListener() {

        public void onStatusChanged(String provider, int status, Bundle extras) {
            String statusAsString = "Available";
            if (status == LocationProvider.OUT_OF_SERVICE)
                statusAsString = "Out of service";
            else if (status == LocationProvider.TEMPORARILY_UNAVAILABLE)
                statusAsString = "Temporarily Unavailable";
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }

        public void onLocationChanged(Location location) {
            if (ActivityCompat.checkSelfPermission(MapsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(MapsActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            // posicao do usuario capturada
            setNewLocation(location);
        }
    };

    private void setNewLocation(Location newLocation){
        lastRegisteredLocation = newLocation;
        LatLng latLng = new LatLng(newLocation.getLatitude(), newLocation.getLongitude());

        if(marker != null){
            mMap.clear();
        }

        marker = new MarkerOptions().position(latLng);
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name));
        marker.title("Estou aqui");

        mMap.addMarker(marker);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

        if (checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, EXT_PERMISSION);
            }
        } else {
            capturarPosicao();
        }
    }
}
