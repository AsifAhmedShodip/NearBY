package com.example.asif.nearby;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class nearby extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener{

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private Button button;
    private LocationListener locationListener;
    private TextView text;
    private LocationManager locationManager;
    private DatabaseReference mDatabase,mDatabase2;
    private FirebaseAuth mAuth;
    private ArrayList<String > list = new ArrayList<String>() ;
    GridView simpleList;
    ArrayList<Students> studentList=new ArrayList<>();

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    // MapFragment mapFragment;
    Location mLastLocation;

    double lat, lng;

    LatLng latLng;
    GoogleMap mGoogleMap;
    SupportMapFragment mFragment;
    Marker mCurrLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

       /* simpleList = (GridView) findViewById(R.id.simpleGridView);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                Toast.makeText(getApplicationContext(), "Good to GO", Toast.LENGTH_SHORT).show();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                mAuth = FirebaseAuth.getInstance();
                final String userID = mAuth.getCurrentUser().getUid();
                mDatabase.child(userID).child("lat").setValue(location.getLatitude());
                mDatabase.child(userID).child("lon").setValue(location.getLongitude());

                studentList.clear();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Users").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Students temp=new Students();
                        temp = dataSnapshot.getValue(Students.class);

                        Location A = new Location("");
                        A.setLatitude(location.getLatitude());
                        A.setLongitude(location.getLongitude());

                        Location B = new Location("");
                        B.setLatitude(temp.lat);
                        B.setLongitude(temp.lon);

                        float dist = A.distanceTo(B);

                        if(dist < 1000.00 && !userID.equals(temp.uid))
                        {
                            studentList.add(temp);
                        }
                        MyAdapter myAdapter=new MyAdapter(nearby.this,R.layout.activity_gridview,studentList);
                        simpleList.setAdapter(myAdapter);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };*/
      /*  if (ActivityCompat.checkSelfPermission(nearby.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(nearby.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String []{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);*/

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Location Not Granted", Toast.LENGTH_SHORT).show();
        }
        buildGoogleApiClient();

    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    void keepLocationUpdated(){

       // t.append(lat+" ; "+lng+"\n");
        Toast.makeText(getApplicationContext(),lat+","+lng, Toast.LENGTH_SHORT).show();
    }

    private void buttonClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(nearby.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(nearby.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String []{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        },10);
                    }
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
            }
        });

    }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, @NonNull int[] grantResults){
            switch (requestCode) {
                case 10:
                    if (ActivityCompat.checkSelfPermission(nearby.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(nearby.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String []{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.INTERNET
                            },10);
                        }
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
                    break;
            }
        }

    @Override
    public void onLocationChanged(Location location) {

        lat = location.getLatitude();
        lng = location.getLongitude();
        Log.e("LocationChanged",location.getSpeed()+" "+location.getAccuracy()+" "+location.getBearing()+" "+location.getProvider()+" "+location.getAltitude()
        );

        keepLocationUpdated();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setMaxWaitTime(5000);
        mLocationRequest.setInterval(10000); // Update location every second

        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }
        catch (SecurityException e)
        {
            Log.e("LocatonUpdate",e+" ");
        }
        if (mLastLocation != null) {
            lat = mLastLocation.getLatitude();
            lng = mLastLocation.getLongitude();

        }

        keepLocationUpdated();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        buildGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }
}


















