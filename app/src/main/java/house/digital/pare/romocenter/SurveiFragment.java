package house.digital.pare.romocenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import house.digital.pare.api.AdapterApi;
import model.SessionManager;
import model.Survei;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveiFragment extends ListFragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private SessionManager session;
    private SurveiRecyclerViewAdapter mAdapter;


    public SurveiFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SurveiFragment newInstance(int columnCount) {
        SurveiFragment fragment = new SurveiFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_survei_list, container, false);
        session = new SessionManager(getActivity().getApplicationContext());
        AdapterApi.service().listSurvei().enqueue(new Callback<List<Survei>>() {
                @Override
                public void onResponse(Call<List<Survei>> call, Response<List<Survei>> response) {
                    List<Survei> result = response.body();

                    SurveiFragment.StableArrayAdapter adapter = new SurveiFragment.StableArrayAdapter(getActivity(),
                            android.R.layout.simple_list_item_1, android.R.id.text1,
                            result);
                    setListAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Survei>> call, Throwable t) {

                }
            });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Survei j = (Survei) l.getAdapter().getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("survei_id", j);
        Intent i = new Intent(getActivity().getApplicationContext(), SurveiActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnListFragmentInteractionListener(Survei item);
    }

    private class StableArrayAdapter extends ArrayAdapter<Survei> {
        private final Context context;
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context,int layoutResourceId, int textViewResourceId,
                                  List<Survei> objects) {

            super(context,layoutResourceId, textViewResourceId, objects);
            this.context = context;
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i).getTitle(), i);
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Survei food = getItem(position);
            if(convertView==null)
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(food.getTitle());

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position).getTitle();
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
