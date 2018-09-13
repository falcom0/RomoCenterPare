package house.digital.pare.romocenter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import house.digital.pare.api.AdapterApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetaFragment extends Fragment implements OnMapReadyCallback,LocationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SupportMapFragment map;
    private LocationManager locationManager;
    private String provider;
    private Location loc;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PetaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PetaFragment newInstance(String param1, String param2) {
        PetaFragment fragment = new PetaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        Log.v("Fariz", locationManager.toString());
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
//        for(String prov :locationManager.getAllProviders()){
//            Log.v("Fariz", prov);
//        }
        provider = locationManager.getAllProviders().get(2);
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
//        Log.v("Fariz", location.toString());
        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            loc = location;
            onLocationChanged(location);
        } else {
//            latituteField.setText("Location not available");
//            longitudeField.setText("Location not available");
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peta, container, false);
        map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(loc.getLatitude(), loc.getLongitude()), 12));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        AdapterApi.service().insertKoordinat("1","0").enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> result = response.body();
                for(int i=0;i<result.size();i++) {
                    String[] split = result.get(i).split(",");
                    map.addMarker(new MarkerOptions()
                            .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                            .position(new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1])))); //Iasi, Romania
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
        map.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(loc.getLatitude(), loc.getLongitude()))); //Iasi, Romania
//        map.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = null;

        String errorMessage = "";
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException ioException) {
            errorMessage = "Service Not Available";
            Log.e("FarizTest", errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            errorMessage = "Invalid Latitude or Longitude Used";
            Log.e("FarizTest", errorMessage + ". " +
                    "Latitude = " + lat + ", Longitude = " +
                    lng, illegalArgumentException);
        }

        Address address = addresses.get(0);
        String locationCity = "";
        if(addresses != null && addresses.size() > 0){
            String addressName = address.getAddressLine(0);
//            Log.d("FarizTest0",address.getAddressLine(0));
//            Log.d("FarizTestAdminArea",address.getAdminArea());
            Log.d("FarizTestLocality",address.getLocality());
            Log.d("FarizTestSubAdminArea",address.getSubAdminArea());
            Log.d("FarizTestSubLocality",address.getSubLocality());
            for(int i=1;i<address.getMaxAddressLineIndex();i++){
                addressName += address.getAddressLine(i);
                Log.d("FarizTestIncr",address.getAddressLine(i));
            }
            String addressText = String.format(
                    "%s, %s, %s",
                    // If there's a street address, add it
                    addressName,
                    // Locality is usually a city
                    address.getLocality(),
                    // The country of the address
                    address.getCountryName());
            /*for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
//                    postalcode="billboard"+address.getPostalCode();
                locationCity = address.getLocality();
                // Toast.makeText(getApplicationContext(),locationcity,Toast.LENGTH_SHORT).show();
                addressName += address.getAddressLine(i);
                if(i<address.getMaxAddressLineIndex()-1){
                    addressName+= ", ";
                }
            }*/

//            addressField.setText(locationCity+" "+addressText);
        }
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
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
}
