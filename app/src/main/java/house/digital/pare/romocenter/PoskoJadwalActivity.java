package house.digital.pare.romocenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import house.digital.pare.api.AdapterApi;
import model.Posko;
import model.ResponseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoskoJadwalActivity extends AppCompatActivity implements View.OnFocusChangeListener
        , View.OnClickListener, AdapterView.OnItemSelectedListener{

    private TextInputEditText pjtmTanggal,pjtmWaktu,pjtsTanggal,pjtsWaktu;
    private int mYear,mMonth,mDay;
    private int mHour,mMinute;
    private Spinner spnLokasi;
    private Button btnSimpan;
    private Integer idPosko;
    private String tanggalMulai,tanggalSampai,waktuMulai,waktuSampai;
    private EditText etJudul,etDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posko_jadwal);

        pjtmTanggal = (TextInputEditText) findViewById(R.id.pjtmTanggal);
        pjtmWaktu = (TextInputEditText) findViewById(R.id.pjtmWaktu);
        pjtsTanggal = (TextInputEditText) findViewById(R.id.pjtsTanggal);
        pjtsWaktu = (TextInputEditText) findViewById(R.id.pjtsWaktu);
        spnLokasi = (Spinner) findViewById(R.id.spinner);
        btnSimpan = (Button) findViewById(R.id.button2Simpan);
        etJudul = (EditText)findViewById(R.id.pjJudul);
        etDeskripsi = (EditText) findViewById(R.id.pjDeskripsi);

        pjtmTanggal.setOnFocusChangeListener(this);
        pjtmTanggal.setOnClickListener(this);
        pjtmWaktu.setOnFocusChangeListener(this);
        pjtmWaktu.setOnClickListener(this);
        pjtsTanggal.setOnFocusChangeListener(this);
        pjtsTanggal.setOnClickListener(this);
        pjtsWaktu.setOnFocusChangeListener(this);
        pjtsWaktu.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);


        AdapterApi.service().listPosko().enqueue(new Callback<List<Posko>>() {
            @Override
            public void onResponse(Call<List<Posko>> call, Response<List<Posko>> response) {
                List<Posko> listPosko = response.body();
                if(listPosko.size() > 0){
                    supplySpinnerLocation(listPosko);
                }
            }

            @Override
            public void onFailure(Call<List<Posko>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        selectDatetime(v,hasFocus);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnSimpan)){
            String wm = tanggalMulai + " " + waktuMulai;
            String ws = tanggalSampai + " " + waktuSampai;
            AdapterApi.service().submitPoskoJadwal(idPosko,etJudul.getText().toString(),etDeskripsi.getText().toString(),
                    wm, ws).enqueue(new Callback<ResponseApi>() {
                @Override
                public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                    Log.v("FarizSubmitPoskoJadRes",response.body().getMessage());
                    Toast.makeText(getApplicationContext(),"Jadwal berhasil di simpan",Toast.LENGTH_SHORT);
                    finish();
                }

                @Override
                public void onFailure(Call<ResponseApi> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Jadwal gagal di simpan. Periksa kembali koneksi internet anda.",Toast.LENGTH_SHORT);
                }
            });
        }else
            selectDatetime(v,true);
    }

    private void selectDatetime(View v, boolean hasFocus){
        if(v.equals(pjtmTanggal) && hasFocus){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            tanggalMulai = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            pjtmTanggal.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }else if(v.equals(pjtmWaktu) && hasFocus){
            final Calendar d = Calendar.getInstance();
            mHour = d.get(Calendar.HOUR_OF_DAY);
            mMinute = d.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            waktuMulai = hourOfDay + ":" + new Formatter().format("%02d",minute) + ":" + "00";
                            pjtmWaktu.setText(hourOfDay + ":" + new Formatter().format("%02d",minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }else if(v.equals(pjtsTanggal) && hasFocus){
            final Calendar e = Calendar.getInstance();
            mYear = e.get(Calendar.YEAR);
            mMonth = e.get(Calendar.MONTH);
            mDay = e.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            tanggalSampai = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            pjtsTanggal.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }else if(v.equals(pjtsWaktu) && hasFocus){
            final Calendar f = Calendar.getInstance();
            mHour = f.get(Calendar.HOUR_OF_DAY);
            mMinute = f.get(Calendar.MINUTE);
            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            waktuSampai = hourOfDay + ":" + new Formatter().format("%02d",minute) + ":" + "00";
                            pjtsWaktu.setText(hourOfDay + ":" + new Formatter().format("%02d",minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }else{

        }
    }

    private void supplySpinnerLocation(List<Posko> listPosko){
        ArrayAdapter<Posko> adapter = new ArrayAdapter<Posko>(this,R.layout.spinner_item, listPosko);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLokasi.setAdapter(adapter);
        spnLokasi.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Posko posko = (Posko) parent.getItemAtPosition(position);
        idPosko = posko.getIdPosko();
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Posko posko = (Posko) parent.getItemAtPosition(0);
        idPosko = posko.getIdPosko();
    }
}
