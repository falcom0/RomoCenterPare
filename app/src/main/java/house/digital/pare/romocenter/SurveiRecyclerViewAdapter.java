package house.digital.pare.romocenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import house.digital.pare.romocenter.SurveiFragment.OnListFragmentInteractionListener;
import model.Survei;

public class SurveiRecyclerViewAdapter extends RecyclerView.Adapter<SurveiRecyclerViewAdapter.ViewHolder> {

    private final List<Survei> mValues;
    private final OnListFragmentInteractionListener mListener;
    private SelectionTracker mSelectionTracker;

    public SurveiRecyclerViewAdapter(List<Survei> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_survei, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.bind(mValues.get(position).getTitle(), mSelectionTracker.isSelected(mValues.get(position).getTitle()));

    }

    @Override
    public int getItemCount() {
        if(mValues==null){
            return 0;
        }else
            return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Survei mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        void bind(String item, boolean isSelected) {
            mContentView.setText(item);
            mContentView.setActivated(isSelected);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}
