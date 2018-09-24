package house.digital.pare.romocenter;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import model.Jadwal;

public class HomeActivity extends AppCompatActivity implements KegiatanFragment.OnListFragmentInteractionListener{

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
//                    mTextMessage.setText(R.string.title_peta);
                    return true;
                case R.id.navigation_kegiatan:
//                    mTextMessage.setText(R.string.title_kegiatan);
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_kopi_darat:
//                    mTextMessage.setText(R.string.title_kopi_darat);
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_masjid:
//                    mTextMessage.setText(R.string.title_masjid);
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
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

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        setupViewPager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        //peta
        pagerAdapter.addFragments(new PetaFragment());
        pagerAdapter.addFragments(new KegiatanFragment());
        pagerAdapter.addFragments(createFragment(R.color.colorPrimaryDark));
        pagerAdapter.addFragments(createFragment(R.color.colorPrimaryDark));
        pagerAdapter.addFragments(createFragment(R.color.colorPrimaryDark));

        viewPager.setAdapter(pagerAdapter);
    }

    @NonNull
    private DummyFragment createFragment(int color) {
        DummyFragment fragment = new DummyFragment();
        fragment.setArguments(passFragmentArguments(fetchColor(color)));
        return fragment;
    }

    @NonNull
    private Bundle passFragmentArguments(int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        return bundle;
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }

    @Override
    public void onListFragmentInteraction(Jadwal item) {

    }
}
