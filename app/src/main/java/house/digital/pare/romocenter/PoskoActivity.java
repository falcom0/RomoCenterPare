package house.digital.pare.romocenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import house.digital.pare.api.AdapterApi;
import model.ResponseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoskoActivity extends AppCompatActivity {

    public static final String TAG = "PoskoActivity";
    private static final int LOC_REQ_CODE = 1;
    private static final int PLACE_PICKER_REQ_CODE = 2;
    private Place place;
    private EditText etAlamat;
    private EditText etNamaPosko;
    private TextView tvKoordinat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posko);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNamaPosko = (EditText) findViewById(R.id.editTextNamaPosko);
        etAlamat = (EditText) findViewById(R.id.editTextAlamat);
        tvKoordinat = (TextView) findViewById(R.id.textViewKoordinat);
        ImageButton mapButton = (ImageButton)findViewById(R.id.ibMapPosko);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentPlaceItems();
            }
        });
        Button btnSimpan = (Button) findViewById(R.id.buttonSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("FarizSubmitPosko",tvKoordinat.getText().toString()+etNamaPosko.getText().toString()+etAlamat.getText().toString());
                AdapterApi.service().submitPosko(tvKoordinat.getText().toString(),etNamaPosko.getText().toString(),etAlamat.getText().toString()).enqueue(new Callback<ResponseApi>() {
                    @Override
                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                        Log.v("FarizSubmitPoskoRes",response.body().getMessage());
                        Toast.makeText(getApplicationContext(),"Posko berhasil di simpan",Toast.LENGTH_SHORT);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseApi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Posko gagal di simpan. Periksa kembali koneksi internet anda.",Toast.LENGTH_SHORT);
                    }
                });
            }
        });

    }

    private void getCurrentPlaceItems() {
        if (isLocationAccessPermitted()) {
            showPlacePicker();
        } else {
            requestLocationAccessPermission();
        }
    }

    @SuppressLint("MissingPermission")
    private void showPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQ_CODE);
        } catch (Exception e) {
            Log.e(TAG, e.getStackTrace().toString());
        }
    }

    private boolean isLocationAccessPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    private void requestLocationAccessPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOC_REQ_CODE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOC_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                showPlacePicker();
            }
        }else if(requestCode == PLACE_PICKER_REQ_CODE){
            if (resultCode == RESULT_OK) {
                place = PlacePicker.getPlace(this, data);
                for (int i : place.getPlaceTypes()) {
                    if(i == Place.TYPE_MOSQUE){
                        
                    }
                }
//                Place.TYPE_MOSQUE;
//                name.setText(place.getName());
                etAlamat.setText(place.getAddress());
                tvKoordinat.setText(Double.toString(place.getLatLng().latitude)+","+Double.toString(place.getLatLng().longitude));
            }
        }
    }
}
