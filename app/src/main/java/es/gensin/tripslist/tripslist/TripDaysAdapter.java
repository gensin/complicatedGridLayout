package es.gensin.tripslist.tripslist;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.gensin.tripslist.R;
import es.gensin.tripslist.tripslist.dummy.DummyItem;
import rx.functions.Action2;

import static android.view.ViewTreeObserver.OnPreDrawListener;

public class TripDaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BIG_TYPE = 1;
    private static final int NORMAL_TYPE = 0;

    private final List<DummyItem> mValues;
    private final Action2<DummyItem, Integer> onItemClick;
    private final Context context;
    private Integer bigPosition;
    private Integer selectedPosition;

    public TripDaysAdapter(Context context, List<DummyItem> items, Action2<DummyItem, Integer> onItemClick) {
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
           view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
               @Override
               public boolean onPreDraw() {
                   final ViewGroup.LayoutParams lp = view.getLayoutParams();
                   if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                       StaggeredGridLayoutManager.LayoutParams sglp =
                               (StaggeredGridLayoutManager.LayoutParams) lp;
                       sglp.setFullSpan(true);
                       view.setLayoutParams(sglp);
                       final StaggeredGridLayoutManager lm =
                               (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();
                       lm.invalidateSpanAssignments();
                   }
                   view.getViewTreeObserver().removeOnPreDrawListener(this);
                   return true;
               }
           });
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
                onBindBigViewHolder((BigViewHolder) holder, item);
                break;
            case NORMAL_TYPE:
                onBindNormalViewHolder((NormalViewHolder) holder, position, item);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (bigPosition != null && position == bigPosition){
            return BIG_TYPE;
        }
        return NORMAL_TYPE;
    }

    public int setBigItemPosition(DummyItem bigItem, Integer position, int columns) {
        Integer bigPosition = position + (columns - (position % columns));
        if (bigPosition > mValues.size()){
            this.bigPosition = mValues.size();
        } else {
            this.bigPosition = bigPosition;
        }
        mValues.add(this.bigPosition, bigItem);
        this.notifyDataSetChanged();
        return this.bigPosition;
    }

    public void clearBigPosition() {
        if (bigPosition != null){
            mValues.remove(bigPosition.intValue());
            this.bigPosition = null;
        }
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
    private void onBindBigViewHolder(BigViewHolder holder, DummyItem item) {
        holder.dayNumber.setText(item.dayNumber);
        holder.dayText.setText(item.dayText);
        holder.tripsNumber.setText(item.tripNumber);
        holder.closeDetail.setOnClickListener(view -> {
            this.clearBigPosition();
            this.notifyDataSetChanged();
            item.isPressed = false;
        });

        setNotification(holder.notification, item);
        setAchievements(holder.achievementsIcon, item);
    }

    private void onBindNormalViewHolder(NormalViewHolder holder, int position, DummyItem item) {
        holder.dayNumber.setText(item.dayNumber);
        holder.dayText.setText(item.dayText);
        holder.tripsNumber.setText(item.tripNumber);

        holder.tripDayItem.setOnClickListener(v -> {
            if (onItemClick != null) {
                item.isPressed = true;
                if (this.bigPosition != null && position > this.bigPosition) {
                    this.selectedPosition = position - 1;
                } else {
                    this.selectedPosition = position;
                }
                onItemClick.call(item, position);
            }
        });

        if (selectedPosition != null && item.isPressed && position == selectedPosition){
            holder.tripDayItem.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background_pressed));
        } else {
            item.isPressed = false;
            holder.tripDayItem.setBackground(ContextCompat.getDrawable(context, R.drawable.button_background));
        }

        setNotification(holder.notification, item);
        setAchievements(holder.achievementsIcon, item);
    }

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
}
