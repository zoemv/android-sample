package com.emv.myapplication.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.emv.myapplication.GsonRequest;
import com.emv.myapplication.R;
import com.emv.myapplication.adapters.SongsAdapter;
import com.emv.myapplication.dto.RequestResponse;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements View.OnClickListener {


    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Arguments:", getArguments().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        requestQueue.add(new GsonRequest(Request.Method.GET, "https://itunes.apple.com/search?term=calvin+harris", null, new GsonRequest.ResponseRequest() {
            @Override
            public void getResponse(RequestResponse requestResponse) {
                Log.i("callback", requestResponse.toString());
                recyclerView.setAdapter(new SongsAdapter(requestResponse.getResults(), ListFragment.this));
            }
        }));
        return view;
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "Un click jejeje", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
