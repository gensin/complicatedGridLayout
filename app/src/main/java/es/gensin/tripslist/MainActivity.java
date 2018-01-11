package es.gensin.tripslist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.tripsdaylist.TripDaysFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TRIPS_DAYS = "Trips days";

    @BindView(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addFragment();
    }

    private void addFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        Fragment mainFragment = TripDaysFragment.newInstance();
        fragmentTransaction.replace(container.getId(), mainFragment, TRIPS_DAYS);
        fragmentTransaction.commit();
    }
}
