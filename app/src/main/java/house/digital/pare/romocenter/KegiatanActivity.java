package house.digital.pare.romocenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import model.Jadwal;
import model.SessionManager;

public class KegiatanActivity extends AppCompatActivity {

    private SessionManager session;
    private ListView listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);
        Bundle data = getIntent().getExtras();
        Jadwal jadwal = (Jadwal) data.getParcelable("jadwal");
        TextView tJudul = (TextView)findViewById(R.id.textViewJudul);
        TextView tAlamat = (TextView)findViewById(R.id.textViewAlamat);
        TextView tDeskripsi = (TextView)findViewById(R.id.textViewDeskripsi);
        TextView tJumlahPeserta = (TextView)findViewById(R.id.textViewJumlahPeserta);
        TextView tPendamping = (TextView)findViewById(R.id.textViewPendamping);
        TextView tTokoh = (TextView)findViewById(R.id.textViewTokoh);
        TextView tWaktuMulai = (TextView)findViewById(R.id.textViewWaktuMulai);
        TextView tWaktuSelesai = (TextView)findViewById(R.id.textViewWaktuSelesai);

        tJudul.setText(jadwal.getJudul());
        tAlamat.setText(jadwal.getAlamat());
        tDeskripsi.setText(jadwal.getDeskripsi());
        tJumlahPeserta.setText(jadwal.getJumlahPeserta());
        tPendamping.setText(jadwal.getPendamping());
        tTokoh.setText(jadwal.getTokoh());
        tWaktuMulai.setText(jadwal.getWaktuMulai());
        tWaktuSelesai.setText(jadwal.getWaktuSelesai());
    }

}
