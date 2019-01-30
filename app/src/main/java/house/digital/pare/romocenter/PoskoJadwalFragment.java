package house.digital.pare.romocenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import house.digital.pare.api.AdapterApi;
import model.PoskoJadwal;
import model.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PoskoJadwalFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private SessionManager session;

    public PoskoJadwalFragment() {
    }

    public static PoskoJadwalFragment newInstance(int columnCount) {
        PoskoJadwalFragment fragment = new PoskoJadwalFragment();
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
        View view = inflater.inflate(R.layout.fragement_poskojadwal_list_test, container, false);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.poskoButton);
        View vRv = (View)view.findViewById(R.id.list2);
        session = new SessionManager(getActivity().getApplicationContext());
        if(Integer.parseInt(session.getUserDetails().get("role")) <= 4){
            ll.setVisibility(LinearLayout.VISIBLE);
            Button tambahPosko = (Button) ll.findViewById(R.id.buttonPosko);
            Button tambahJadwal = (Button) ll.findViewById(R.id.buttonJadwal);

            tambahPosko.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity().getApplicationContext(), PoskoActivity.class);
                    startActivity(i);
                }
            });
            tambahJadwal.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity().getApplicationContext(), PoskoJadwalActivity.class);
                    startActivity(i);
                }
            });
        }else
            ll.setVisibility(LinearLayout.GONE);
        // Set the adapter
        if (vRv instanceof RecyclerView) {
            Context context = vRv.getContext();
            final RecyclerView recyclerView = (RecyclerView) vRv;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            AdapterApi.service().listPoskoJadwal().enqueue(new Callback<List<PoskoJadwal>>() {
                @Override
                public void onResponse(Call<List<PoskoJadwal>> call, Response<List<PoskoJadwal>> response) {
                    recyclerView.setAdapter(new PoskoJadwalRecyclerViewAdapter(response.body(), mListener));
                }

                @Override
                public void onFailure(Call<List<PoskoJadwal>> call, Throwable t) {

                }
            });
        }
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

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PoskoJadwal item);
    }
}
