package es.gensin.tripslist.tripslist;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.R;
import es.gensin.tripslist.tripslist.dummy.DummyContent.*;
import rx.functions.Action1;

public class TripDaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BIG_TYPE = 1;
    private static final int NORMAL_TYPE = 0;

    private final List<DummyItem> mValues;
    private final Action1<DummyItem> onItemClick;
    private final Context context;

    public TripDaysAdapter(Context context, List<DummyItem> items, Action1<DummyItem> onItemClick) {
        this.mValues = items;
        this.onItemClick = onItemClick;
        this.context = context;
    }

    /*************************************
     ********** PUBLIC METHODS ***********
     *************************************/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == BIG_TYPE){
           view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_tripday_detail, parent, false);
            return new BigViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_tripday, parent, false);
            return new NormalViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        DummyItem item = mValues.get(position);
        switch (holder.getItemViewType()) {
            case BIG_TYPE:
                onBindBigViewHolder((BigViewHolder) holder, position, item);
                break;
            case NORMAL_TYPE:
                onBindNormalViewHolder((NormalViewHolder) holder, position, item);
                break;
        }

    }

    private void onBindNormalViewHolder(NormalViewHolder holder, int position, DummyItem item) {
        holder.dayNumber.setText(item.dayNumber);
        holder.dayText.setText(item.dayText);
        holder.tripsNumber.setText(item.tripNumber);

        holder.tripDayItem.setOnClickListener(v -> {
            if (null != onItemClick) {
                item.isPressed =!item.isPressed;
                changeItemView(holder, item);
                onItemClick.call(item);
            }
        });

        setNotification(holder.notification, item);
        setAchievements(holder.achievementsIcon, item);
    }

    private void onBindBigViewHolder(BigViewHolder holder, int position, DummyItem item) {

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 5){
            return BIG_TYPE;
        }
        return NORMAL_TYPE;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.achievements_icon)
        ImageView achievementsIcon;
        @BindView(R.id.day_text)
        TextView dayText;
        @BindView(R.id.day_number)
        TextView dayNumber;
        @BindView(R.id.trips_number)
        TextView tripsNumber;
        @BindView(R.id.notification)
        TextView notification;
        @BindView(R.id.item_parent)
        ConstraintLayout tripDayItem;

        public NormalViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class BigViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.achievements_icon)
        ImageView achievementsIcon;
        @BindView(R.id.close_detail)
        ImageView closeDetail;
        @BindView(R.id.day_text)
        TextView dayText;
        @BindView(R.id.day_number)
        TextView dayNumber;
        @BindView(R.id.trips_number)
        TextView tripsNumber;
        @BindView(R.id.notification)
        TextView notification;
        @BindView(R.id.item_parent)
        ConstraintLayout tripDayItem;
        @BindView(R.id.trips_time)
        TextView tripsTime;
        @BindView(R.id.trips_distance)
        TextView tripsDistance;
        @BindView(R.id.trips_score)
        TextView tripsScore;

        public BigViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /*************************************
     ********** PRIVATE METHODS **********
     *************************************/
    private void setAchievements(ImageView achievementsIcon, DummyItem item) {
        if(item.hasAchievement){
            achievementsIcon.setVisibility(View.VISIBLE);
        } else {
            achievementsIcon.setVisibility(View.GONE);
        }
    }

    private void setNotification(TextView notification, DummyItem item) {
        if (item.tripsUnseeing > 0){
            notification.setVisibility(View.VISIBLE);
            notification.setText(String.valueOf(item.tripsUnseeing));
        } else {
            notification.setVisibility(View.GONE);
        }
    }

    private void changeItemView(NormalViewHolder holder, DummyItem item) {
        // TODO: 08/01/2018 change background or something to show is clicked
        holder.tripDayItem.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background_pressed));
    }
}
