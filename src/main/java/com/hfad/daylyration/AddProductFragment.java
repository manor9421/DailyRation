package com.hfad.daylyration;


import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {

    public boolean checkStringData(String data){
        if(!data.isEmpty()){
            if(!data.trim().isEmpty()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        Button b = (Button) view.findViewById(R.id.addConfirm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Получение строк
                String addNameString = ((EditText) view.findViewById(R.id.addName)).getText().toString();
                String caloriesString = ((EditText) view.findViewById(R.id.calories)).getText().toString();
                String proteinsString = ((EditText) view.findViewById(R.id.proteins)).getText().toString();
                String fatsString = ((EditText) view.findViewById(R.id.fats)).getText().toString();
                String carbohydratesString = ((EditText) view.findViewById(R.id.carbohydrates)).getText().toString();
                String celluloseString = ((EditText) view.findViewById(R.id.cellulose)).getText().toString();
                // проверка строк
                if(checkStringData(addNameString) && checkStringData(caloriesString) && checkStringData(proteinsString) && checkStringData(fatsString) && checkStringData(carbohydratesString) && checkStringData(celluloseString)) {
                    String addName = addNameString.trim();
                    float calories = Float.parseFloat(caloriesString.trim());
                    float proteins = Float.parseFloat(proteinsString.trim());
                    float fats = Float.parseFloat(fatsString.trim());
                    float carbohydrates = Float.parseFloat(carbohydratesString.trim());
                    float cellulose = Float.parseFloat(celluloseString.trim());

                    //dobavit v bazu
                    try{
                        DBHelper db = new DBHelper(getActivity());
                        db.insertFood(db.getWritableDatabase(), addName, calories,proteins,fats,carbohydrates,cellulose);
                        db.close();
                        Toast t = Toast.makeText(getActivity(),"Added successfull",Toast.LENGTH_SHORT);
                        t.show();
                    }catch (SQLiteException e){
                        Toast t = Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT);
                        t.show();
                    }
                    //Reload fragment
                    Fragment frg = null;
                    frg = new AddProductFragment();//getFragmentManager().findFragmentByTag("visible_fragment");
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //ft.detach(frg);
                    //ft.attach(frg);
                    ft.replace(R.id.content_frame,frg);
                    ft.commit();

                }else{
                    TextView errorView = (TextView) view.findViewById(R.id.errorText);
                    errorView.setText("*Write all information, please");
                }
            }
        });

        return view;
    }

}