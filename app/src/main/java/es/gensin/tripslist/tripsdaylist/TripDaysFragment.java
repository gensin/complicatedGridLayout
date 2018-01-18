package es.gensin.tripslist.tripsdaylist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.dayslist.TripDaysAdapter;
import es.gensin.tripslist.tripsdaylist.dummy.TripDayDummyContent;

public class TripDaysFragment extends Fragment {

    private final static String TAG = "TAG.tripDaysList";
    private final int COLUMN_COUNT = 4;

    public static TripDaysFragment newInstance() {
        TripDaysFragment fragment = new TripDaysFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on Create --> " + this.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "on Start --> " + this.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "on Resume --> " + this.toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "on Attach --> " + this.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "on Pause --> " + this.toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "on Stop --> " + this.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "on Destroy view --> " + this.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on Destroy --> " + this.toString());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "on Detach --> " + this.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "on Create view --> " + this.toString());
        View view = inflater.inflate(R.layout.fragment_tripday_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(COLUMN_COUNT, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new TripDaysAdapter(getContext(), TripDayDummyContent.ITEMS,
                    (tripDayItem, position) -> {
                        TripDaysAdapter adapter = (TripDaysAdapter) recyclerView.getAdapter();
                        adapter.clearBigPosition();
                        int newPosition = adapter.setBigItemPosition(tripDayItem, position, COLUMN_COUNT);
                        recyclerView.scrollToPosition(newPosition);
                    },
                    trip -> {
                        // TODO: 11/01/2018 Do something with trip
                        Toast.makeText(getContext(),String.valueOf(trip.tripId),Toast.LENGTH_SHORT).show();
                    }));
        }
        return view;
    }
}
