package house.digital.pare.romocenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import model.Aspirasi;
import model.SessionManager;

public class AspirasiActivity extends AppCompatActivity {
    private SessionManager session;
    private ListView listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspirasi);
        Bundle data = getIntent().getExtras();
        Aspirasi aspirasi = data.getParcelable("aspirasi");
        TextView tNoAspirasi = (TextView)findViewById(R.id.textViewNoAspirasi);
        TextView tJudul = (TextView)findViewById(R.id.textViewJudul);
        TextView tTujuan = (TextView)findViewById(R.id.textViewTujuan);
        TextView tKronologi = (TextView)findViewById(R.id.textViewKronologi);
        TextView tPrioritas = (TextView)findViewById(R.id.textViewPrioritas);
        TextView tStatus = (TextView)findViewById(R.id.textViewStatus);

        tNoAspirasi.setText(aspirasi.getNoAspirasi());
        tJudul.setText(aspirasi.getJudul());
        tTujuan.setText(aspirasi.getTujuan());
        tKronologi.setText(aspirasi.getKronologi());
        tPrioritas.setText(aspirasi.getPrioritas());
        String status ="";
        String st = aspirasi.getStatus();
        if(st=="0"){
            status="Pending";
        } else if (st == "1") {
            status="On Progress";
        } else if (st == "2") {
            status="Finish";
        }
        tStatus.setText(status);
    }
}
