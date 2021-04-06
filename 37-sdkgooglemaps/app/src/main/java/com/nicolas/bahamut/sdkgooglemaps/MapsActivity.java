package com.nicolas.bahamut.sdkgooglemaps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        // Add a marker in Sydney and move the camera
        LatLng mcdonaldsCuritiba = new LatLng(-25.496452676342365, -49.27038208737373);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                mMap.addMarker(
//                        new MarkerOptions()
//                                .position(latLng)
//                                .title("Local")
//                                .snippet("Clicado")
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconfinder_mcdonalds_294670))
//                );
//                Toast.makeText(MapsActivity.this, "Clicado", Toast.LENGTH_SHORT).show();
//            }
//        });

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(mcdonaldsCuritiba);
        circleOptions.radius(500);//em metros
        circleOptions.strokeWidth(3);
        circleOptions.strokeColor(Color.argb(20, 66, 173, 245));
        circleOptions.fillColor(Color.argb(40, 66, 173, 245));
        mMap.addCircle(circleOptions);

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754));
        polygonOptions.add(new LatLng(-23.585615, -46.656662));
        polygonOptions.add(new LatLng(-23.587158, -46.657037));
        polygonOptions.add(new LatLng(-23.587247, -46.658797));
        polygonOptions.fillColor(Color.argb(40, 66, 173, 245));

        mMap.addPolygon(polygonOptions);

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(mcdonaldsCuritiba);
        polylineOptions.add(new LatLng(-23.586332, -46.658754));
        polylineOptions.color(Color.argb(60, 99, 173, 245));
        polylineOptions.width(20);

        mMap.addPolyline(polylineOptions);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Clicado")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconfinder_mcdonalds_294670))
                );
                Toast.makeText(MapsActivity.this, "Clicado", Toast.LENGTH_SHORT).show();
            }
        });

        mMap.addMarker(
                new MarkerOptions()
                .position(mcdonaldsCuritiba)
                .title("McDonald's aqui do meu bairro")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconfinder_mcdonalds_294670))
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mcdonaldsCuritiba, 4));
    }
}