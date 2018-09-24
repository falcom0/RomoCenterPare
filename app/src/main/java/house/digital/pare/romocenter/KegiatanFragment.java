package house.digital.pare.romocenter;

//import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import house.digital.pare.api.AdapterApi;
import model.Jadwal;
import model.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class KegiatanFragment extends ListFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private SessionManager session;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KegiatanFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static KegiatanFragment newInstance(int columnCount) {
        KegiatanFragment fragment = new KegiatanFragment();
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
        View view = inflater.inflate(R.layout.fragment_kegiatan_list, container, false);
        session = new SessionManager(getActivity().getApplicationContext());
        ListView listMenu = (ListView)view.findViewById(android.R.id.list);
        AdapterApi.service().getJadwal(session.getUserDetails().get("nik")).enqueue(new Callback<List<Jadwal>>() {
            @Override
            public void onResponse(Call<List<Jadwal>> call, Response<List<Jadwal>> response) {
                List<Jadwal> result = response.body();

                StableArrayAdapter adapter = new StableArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1,
                        result);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Jadwal>> call, Throwable t) {
                Log.e("FarizJadwal",t.getMessage());
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
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
        Jadwal j = (Jadwal) l.getAdapter().getItem(position);
        Intent i = new Intent(getActivity().getApplicationContext(), KegiatanActivity.class);
        i.putExtra("jadwal", j);
        startActivity(i);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Jadwal item);
    }

    private class StableArrayAdapter extends ArrayAdapter<Jadwal> {
        private final Context context;
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context,int layoutResourceId, int textViewResourceId,
                                  List<Jadwal> objects) {

            super(context,layoutResourceId, textViewResourceId, objects);
            this.context = context;
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i).getJudul(), i);
            }
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Jadwal food = getItem(position);
            if(convertView==null)
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(food.getJudul());
//            textView.setTextColor(getResources().getColor(android.R.color.white));
            /*NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
            hargaItem.setText(nf.format((double)food.getPrice()));
            namaItem.setText(food.getName());
            ketItem.setText(food.getContent());
            Bitmap b = Function.getBitmapFromURL(url+food.getImagePath().substring(2));
            imageItem.setImageBitmap(b);*/

            return convertView;
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position).getJudul();
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
