package house.digital.pare.romocenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import model.Aspirasi;
import model.Jadwal;
import model.PoskoJadwal;
import model.Survei;

public class HomeActivity extends AppCompatActivity implements KegiatanFragment.OnListFragmentInteractionListener,PoskoJadwalFragment.OnListFragmentInteractionListener,SurveiFragment.OnListFragmentInteractionListener,AspirasiFragment.OnListFragmentInteractionListener{

    private TextView mTextMessage;
    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;
    private SupportMapFragment map;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_peta:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_kegiatan:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_kopi_darat:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_masjid:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_survei:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setupViewPager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        //hanya untuk 5 fragments
        pagerAdapter.addFragments(new PetaFragment());
        pagerAdapter.addFragments(new KegiatanFragment());
        pagerAdapter.addFragments(new PoskoJadwalFragment());
        pagerAdapter.addFragments(new AspirasiFragment());
        pagerAdapter.addFragments(new SurveiFragment());

        viewPager.setAdapter(pagerAdapter);
    }


    @Override
    public void onListFragmentInteraction(Jadwal item) {

    }

    @Override
    public void onListFragmentInteraction(PoskoJadwal item) {

    }


    @Override
    public void OnListFragmentInteractionListener(Survei item) {

    }

    @Override
    public void onListFragmentInteraction(Aspirasi item) {

    }
}
