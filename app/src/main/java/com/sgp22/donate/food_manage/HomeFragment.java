package com.sgp22.donate.food_manage;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Data_rest> items;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        items=new ArrayList<>();

        Data_rest obj1=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","canada");
        items.add(obj1);
        Data_rest obj2=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
        items.add(obj2);
        Data_rest obj3=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
        items.add(obj3);
        Data_rest obj4=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
        items.add(obj4);
        Data_rest obj5=new Data_rest(R.drawable.logo_splash,"Tulsi_Restaurant","9kg/12p","india");
        items.add(obj5);

        recyclerView.setAdapter(new Adapter(items));
        return view;
    }
}