package com.hfad.daylyration;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevInfoFragment extends Fragment {


    public DevInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dev_info, container, false);
        TextView tv = (TextView)v.findViewById(R.id.myEmail);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND,
                        Uri.parse("mailto:roman3920@yandex.ua"));
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });

        return v;
    }

}
