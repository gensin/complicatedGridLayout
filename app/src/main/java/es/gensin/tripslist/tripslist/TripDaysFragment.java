package es.gensin.tripslist.tripslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.gensin.tripslist.R;
import es.gensin.tripslist.tripslist.dummy.DummyContent;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tripday_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(COLUMN_COUNT, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(new TripDaysAdapter(getContext(), DummyContent.ITEMS, (tripDayItem, position) -> {
                // TODO: 08/01/2018 Do Something with the item
                TripDaysAdapter adapter = (TripDaysAdapter) recyclerView.getAdapter();
                adapter.clearBigPosition();
                Integer bigPosition = position + (COLUMN_COUNT - (position % COLUMN_COUNT));
                adapter.setBigItemPosition(tripDayItem, bigPosition);
                recyclerView.scrollToPosition(bigPosition);
            }));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
