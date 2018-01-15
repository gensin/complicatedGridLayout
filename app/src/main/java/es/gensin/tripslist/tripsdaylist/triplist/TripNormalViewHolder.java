package es.gensin.tripslist.tripsdaylist.triplist;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.DateUtils;
import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.dummy.Trip;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created on 11/01/2018.
 */

class TripNormalViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    @BindView(R.id.time_start)
    TextView timeStart;
    @BindView(R.id.time_finish)
    TextView timeFinish;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.score_correct_complete)
    LinearLayout score_container;
    @BindView(R.id.score_icon)
    ImageView scoreIcon;
    @BindView(R.id.score_correct)
    TextView scoreCorrect;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.item_parent)
    ConstraintLayout tripItem;
    @BindView(R.id.from_address)
    TextView fromAddress;
    @BindView(R.id.to_address)
    TextView toAddress;
    @BindView(R.id.avatar_normal)
    ImageView avatarNormal;
    @BindView(R.id.like)
    TextView like;
    @BindView(R.id.dislike)
    TextView dislike;
    @BindView(R.id.avatar_pitch)
    TextView avatarPitch;
    @BindView(R.id.item_less_info)
    View lessInfo;
    @BindView(R.id.item_more_info)
    ImageView moreInfo;

    public TripNormalViewHolder(View view) {
        super(view);
        context = view.getContext();
        ButterKnife.bind(this, view);
    }

    public void onBindItem(Trip item, int type, Action0 expandCLick, Action1<Trip> onItemClick) {
        setBasicInfo(item);
        if (type == TripAdapter.NORMAL_TYPE){
            avatarPitch.setText(item.tripInsight);
            changeScore(item);
        } else { //SHORT TYPE
            avatarPitch.setText("Sorry but this trip is too short to be scored, trips should be larger than a minimum of 500 mts."); // TODO: 12/01/2018 Add to strings
            score_container.setVisibility(View.GONE);
            score.setVisibility(View.VISIBLE);
            score.setText("Not scoreable");
        }
        moreInfo.setOnClickListener(view -> {
            moreInfo.setVisibility(View.GONE);
            lessInfo.setVisibility(View.VISIBLE);
            showMoreInfo();
            expandCLick.call();
        });
        lessInfo.setOnClickListener(view -> {
            moreInfo.setVisibility(View.VISIBLE);
            lessInfo.setVisibility(View.GONE);
            hideMoreInfo();
            expandCLick.call();
        });
        tripItem.setOnClickListener(view -> onItemClick.call(item));
        // TODO: 15/01/2018 Añadir like dislike
    }

    private void hideMoreInfo() {
        avatarNormal.setVisibility(View.GONE);
        like.setVisibility(View.GONE);
        dislike.setVisibility(View.GONE);
        avatarPitch.setVisibility(View.GONE);
        // TODO: 15/01/2018 Hide map
    }

    private void showMoreInfo() {
        avatarNormal.setVisibility(View.VISIBLE);
        like.setVisibility(View.VISIBLE);
        dislike.setVisibility(View.VISIBLE);
        avatarPitch.setVisibility(View.VISIBLE);
        // TODO: 15/01/2018 Add map
    }

    private void setBasicInfo(Trip item) {
        timeStart.setText(DateUtils.getHourFormatStringFromDate(DateUtils.getDateFromString(item.startTripTime)));
        timeFinish.setText(DateUtils.getHourFormatStringFromDate(DateUtils.getDateFromString(item.stopTripTime)));
        distance.setText(new StringBuilder().append(item.distance / 1000).append(" km").toString()); //todo Properly do this
        changeScore(item);
        fromAddress.setText(item.from);
        toAddress.setText(item.to);
    }

    private void changeScore(Trip item) {
        if (item.score != null){
            score.setVisibility(View.GONE);
            score_container.setVisibility(View.VISIBLE);
            scoreCorrect.setText(String.valueOf(item.score));
            scoreIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_bad_default_red)); // TODO: 15/01/2018 Take from picasso
        } else {
            score_container.setVisibility(View.GONE);
            score.setVisibility(View.VISIBLE);
            score.setText("Pending score");
            avatarPitch.setText("$We are still working on your score, but here is a glance at your trip. We should be finished shortly");
        }
    }
}
