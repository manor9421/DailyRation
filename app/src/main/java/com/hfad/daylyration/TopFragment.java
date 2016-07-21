package com.hfad.daylyration;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.topFLLayout);

        ll.findViewById(R.id.addProdButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.content_frame, new AddProductFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
            }
        });
        ll.findViewById(R.id.prodListButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.content_frame, new ProductListFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
            }
        });
        ll.findViewById(R.id.newIngButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.content_frame, new NewIngestionFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
            }
        });
        ll.findViewById(R.id.dailyCalButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction frt = getFragmentManager().beginTransaction();
                frt.replace(R.id.content_frame, new DailyCaloriesFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
            }
        });
        ll.findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.exit(0);
            }
        });


        // Inflate the layout for this fragment
        return v;
    }

}