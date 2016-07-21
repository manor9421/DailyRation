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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {


    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_product_list, container, false);

        try {
            DBHelper db = new DBHelper(getActivity());
            SQLiteDatabase foodsDB = db.getReadableDatabase();
            Cursor cursor = db.allList(foodsDB);
            TableLayout tableLayout = (TableLayout) v.findViewById(R.id.tabL);

            if(cursor.moveToFirst()){
                //Шапка
                TableRow firstRow = new TableRow(getActivity());
                TableRow.LayoutParams firstRowLp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                firstRowLp.setMargins(0,5,5,0);

                TextView tv1 = new TextView(getActivity());
                tv1.setLayoutParams(firstRowLp);
                tv1.setText("Name");
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

                //цвет
                String[] rowBackgColor = {"#393939","#5B0000","#00650D","#001075"};
                do {
                    //int rows = cursor.getCount();
                    int cols = cursor.getColumnCount();
                    // Выбор цвета для строки
                    int idx = new Random().nextInt(rowBackgColor.length);


                    ///for (int i = 0; i < 2; i++) {
                        TableRow newRow = new TableRow(getActivity());
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(0,5,5,0);
                    //newRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                        for(int j=0;j<cols;j++){
                            TextView info = new TextView(getActivity());

                            info.setLayoutParams(lp);
                            info.setText(cursor.getString(j));
                            info.setBackgroundColor(Color.parseColor(rowBackgColor[idx]));//random
                            info.setTextColor(Color.parseColor("#FFFFFF"));
                            info.setPadding(5,0,5,0);
                            newRow.addView(info);
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

        // Inflate the layout for this fragment
        return v;
    }

}