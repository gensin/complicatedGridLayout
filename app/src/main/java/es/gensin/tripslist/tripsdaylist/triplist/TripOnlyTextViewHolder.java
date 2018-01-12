package es.gensin.tripslist.tripsdaylist.triplist;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.DateUtils;
import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.dummy.Trip;

/**
 * Created on 11/01/2018.
 */

class TripOnlyTextViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.processing_trip_icon)
    ImageView processingTripIcon;
    @BindView(R.id.time_start)
    TextView timeStart;
    @BindView(R.id.time_finish)
    TextView timeFinish;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.item_parent)
    ConstraintLayout tripItem;
    @BindView(R.id.trip_text)
    TextView tripText;
    @BindView(R.id.support)
    Button support;

    public TripOnlyTextViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void onBindItem(Trip item, int type) {
        setBasicInfo(item);
        if (type == TripAdapter.TESTING_TYPE){
            tripText.setText("Testing");
            score.setText("Not scoreable");
            support.setVisibility(View.GONE);
            processingTripIcon.setVisibility(View.GONE);
        } else if (type == TripAdapter.BAD_TYPE){
            tripText.setText("Sorry but due to problems with the GPS data that was collected we can not score this trip.");
            score.setText("Not scoreable");
            support.setVisibility(View.VISIBLE);
            processingTripIcon.setVisibility(View.GONE);
            support.setOnClickListener(view -> {
                // TODO: 12/01/2018 go to somewhere in the internet
            });
        } else { //WORKING TYPE
            tripText.setText("We are processing all your data from this trip. We should have it ready for you shortly please come back in a few minutes."); // TODO: 12/01/2018 Add to strings
            score.setText("Processing data");
            support.setVisibility(View.GONE);
            processingTripIcon.setVisibility(View.VISIBLE);
        }
    }

    private void setBasicInfo(Trip item) {
        timeStart.setText(DateUtils.getHourFormatStringFromDate(DateUtils.getDateFromString(item.startTripTime)));
        timeFinish.setText(DateUtils.getHourFormatStringFromDate(DateUtils.getDateFromString(item.stopTripTime)));
        distance.setText(new StringBuilder().append(item.distance / 1000).append(" km").toString()); //todo Properly do this
    }
}
