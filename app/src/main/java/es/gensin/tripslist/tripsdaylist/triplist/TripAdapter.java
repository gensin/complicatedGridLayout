package es.gensin.tripslist.tripsdaylist.triplist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.dummy.Trip;
import es.gensin.tripslist.tripsdaylist.dummy.TripDay;
import rx.functions.Action1;

/**
 * Created on 11/01/2018.
 */

public class TripAdapter extends RecyclerView.Adapter {

    private static final int WORKING_TYPE = 0;
    private static final int NORMAL_TYPE = 1;
    private static final int TESTING_TYPE = 2;
    private static final int SHORT_TYPE = 3;
    private static final int BAD_TYPE = 4;

    private final List<Trip> trips;
    private final Action1<Trip> onItemClick;
    private final Context context;

    public TripAdapter(Context context, ArrayList<Trip> trips, Action1<Trip> onTripClickListener) {
        this.trips = trips;
        this.onItemClick = onTripClickListener;
        this.context = context;
    }

    /*************************************
     ********** PUBLIC METHODS ***********
     *************************************/

    /********* Adapter Methods ***********/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case WORKING_TYPE:
            case TESTING_TYPE:
            case BAD_TYPE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_trip_only_text, parent, false);
                return new TripOnlyTextViewHolder(view);
            case NORMAL_TYPE:
            case SHORT_TYPE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_trip_normal, parent, false);
                return new TripNormalViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Trip item = trips.get(position);
        switch (holder.getItemViewType()) {
            case WORKING_TYPE:
            case TESTING_TYPE:
            case BAD_TYPE:
            case NORMAL_TYPE:
            case SHORT_TYPE:
        }
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    @Override
    public int getItemViewType(int position) {
        Trip trip = trips.get(position);
        if (trip == null || trip.tripId == null){
            return WORKING_TYPE;
        } else {
            return trip.tripType;
        }
    }
}
