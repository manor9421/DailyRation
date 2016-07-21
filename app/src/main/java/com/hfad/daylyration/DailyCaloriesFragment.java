package com.hfad.daylyration;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyCaloriesFragment extends Fragment {


    public DailyCaloriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_calories, container, false);


        try {
            DBHelper db = new DBHelper(getActivity());
            SQLiteDatabase foodsDB = db.getReadableDatabase();
            Cursor cursor = db.dailyCalProds(foodsDB);
            TableLayout tableLayout = (TableLayout) v.findViewById(R.id.dailyCalTabL);

            if(cursor.moveToFirst()){
                //Шапка
                TableRow firstRow = new TableRow(getActivity());
                TableRow.LayoutParams firstRowLp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                firstRowLp.setMargins(0,5,5,0);

                TextView tv0 = new TextView(getActivity());
                tv0.setLayoutParams(firstRowLp);
                tv0.setText("Date");
                tv0.setBackgroundColor(Color.parseColor("#000000"));
                tv0.setTextColor(Color.parseColor("#FFFFFF"));
                tv0.setPadding(5,0,5,0);
                firstRow.addView(tv0);
                TextView tv1 = new TextView(getActivity());
                tv1.setLayoutParams(firstRowLp);
                tv1.setText("Products");
                tv1.setBackgroundColor(Color.parseColor("#000000"));
                tv1.setTextColor(Color.parseColor("#FFFFFF"));
                tv1.setPadding(5,0,5,0);
                firstRow.addView(tv1);
                TextView tv2 = new TextView(getActivity());
                tv2.setLayoutParams(firstRowLp);
                tv2.setText("Calories");
                tv2.setBackgroundColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                tv2.setPadding(5,0,5,0);
                firstRow.addView(tv2);
                TextView tv3 = new TextView(getActivity());
                tv3.setLayoutParams(firstRowLp);
                tv3.setText("Proteins");
                tv3.setBackgroundColor(Color.parseColor("#000000"));
                tv3.setTextColor(Color.parseColor("#FFFFFF"));
                tv3.setPadding(5,0,5,0);
                firstRow.addView(tv3);
                TextView tv4 = new TextView(getActivity());
                tv4.setLayoutParams(firstRowLp);
                tv4.setText("Fats");
                tv4.setBackgroundColor(Color.parseColor("#000000"));
                tv4.setTextColor(Color.parseColor("#FFFFFF"));
                tv4.setPadding(5,0,5,0);
                firstRow.addView(tv4);
                TextView tv5 = new TextView(getActivity());
                tv5.setLayoutParams(firstRowLp);
                tv5.setText("Carbohydrates");
                tv5.setBackgroundColor(Color.parseColor("#000000"));
                tv5.setTextColor(Color.parseColor("#FFFFFF"));
                tv5.setPadding(5,0,5,0);
                firstRow.addView(tv5);
                TextView tv6 = new TextView(getActivity());
                tv6.setLayoutParams(firstRowLp);
                tv6.setText("Cellulose");
                tv6.setBackgroundColor(Color.parseColor("#000000"));
                tv6.setTextColor(Color.parseColor("#FFFFFF"));
                tv6.setPadding(5,0,5,0);
                firstRow.addView(tv6);
                tableLayout.addView(firstRow);

                do {
                    //int rows = cursor.getCount();
                    int cols = cursor.getColumnCount();

                    ///for (int i = 0; i < 2; i++) {
                    TableRow newRow = new TableRow(getActivity());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0,5,5,0);
                    //newRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    for(int j=0;j<cols;j++){
                        if(j==0){//значит, дата
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            long ts = Long.parseLong(cursor.getString(j))*1000;
                            Date date = new Date(ts);
                            String time = sdf.format(date);

                            TextView info = new TextView(getActivity());
                            info.setLayoutParams(lp);
                            info.setText(time);
                            info.setBackgroundColor(Color.parseColor("#000000"));
                            info.setTextColor(Color.parseColor("#FFFFFF"));
                            info.setPadding(5,0,5,0);
                            newRow.addView(info);
                        }else {// все столбцы, кроме даты
                            TextView info = new TextView(getActivity());

                            info.setLayoutParams(lp);
                            info.setText(cursor.getString(j));
                            info.setBackgroundColor(Color.parseColor("#000000"));
                            info.setTextColor(Color.parseColor("#FFFFFF"));
                            info.setPadding(5, 0, 5, 0);
                            newRow.addView(info);
                        }
                    }
                    tableLayout.addView(newRow);
                    // }
                }while(cursor.moveToNext());
                cursor.close();
                db.close();
            }

        }catch(SQLiteException e){
            Toast toast = Toast.makeText(getActivity(), "Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
        return v;
    }

}
