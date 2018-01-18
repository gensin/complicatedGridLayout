package es.gensin.tripslist.tripsdaylist.dayslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import es.gensin.tripslist.R;
import es.gensin.tripslist.tripsdaylist.dummy.Trip;
import es.gensin.tripslist.tripsdaylist.dummy.TripDay;
import rx.functions.Action1;
import rx.functions.Action2;

import static android.view.ViewTreeObserver.OnPreDrawListener;

public class TripDaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = "TAG.TripsDaysAdapter";

    private static final int BIG_TYPE = 1;
    private static final int NORMAL_TYPE = 0;

    private final List<TripDay> mValues;
    private final Action2<TripDay, Integer> onTripDayClick;
    private final Context context;
    private Integer bigPosition;
    private Integer selectedPosition;
    private Action1<Trip> onTripClick;

    public TripDaysAdapter(Context context, List<TripDay> items, Action2<TripDay, Integer> onTripDayClick, Action1<Trip> onTripClick) {
        Log.d(TAG, "Create Adapter");
        this.mValues = items;
        this.onTripDayClick = onTripDayClick;
        this.context = context;
        this.onTripClick = onTripClick;
    }

    /*************************************
     ********** PUBLIC METHODS ***********
     *************************************/

    /********* Adapter Methods ***********/
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
        Log.d(TAG, "On Bind View Holder " + position);
        TripDay item = mValues.get(position);
        switch (holder.getItemViewType()) {
            case NORMAL_TYPE:
                ((NormalViewHolder) holder).onBindItem(context, item, position, selectedPosition, itemClick -> {
                    if (onTripDayClick != null) {
                        item.isPressed = true;
                        changeSelectedPosition(position);
                        onTripDayClick.call(item, position);
                    }
                });
                break;
            case BIG_TYPE:
                ((BigViewHolder) holder).onBindItem(context, item,
                        onCloseClick -> {
                            this.clearBigPosition();
                            item.isPressed = false;
                            Log.d(TAG, "Change values on close clicked");
                            this.notifyDataSetChanged();
                        },
                        this.onTripClick);
                break;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "get item count");
        return mValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (bigPosition != null && position == bigPosition){
            return BIG_TYPE;
        }
        return NORMAL_TYPE;
    }

    /********* Fragment Communication Methods ***********/

    public int setBigItemPosition(TripDay bigItem, Integer position, int columns) {
        Integer bigPosition = position + (columns - (position % columns));
        if (bigPosition > mValues.size()){
            this.bigPosition = mValues.size();
        } else {
            this.bigPosition = bigPosition;
        }
        mValues.add(this.bigPosition, bigItem);
        Log.d(TAG, "Change values on day clicked");
        this.notifyDataSetChanged();
        return this.bigPosition;
    }

    public void clearBigPosition() {
        if (bigPosition != null){
            mValues.remove(bigPosition.intValue());
            this.bigPosition = null;
        }
    }

    /*************************************
     ********** PRIVATE METHODS **********
     *************************************/
    private void changeSelectedPosition(int position) {
        if (this.bigPosition != null && position > this.bigPosition) {
            this.selectedPosition = position - 1;
        } else {
            this.selectedPosition = position;
        }
    }
}
