package com.hfad.daylyration;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewIngestionFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //выбрать ВСЕ имена
        View thisFragView = inflater.inflate(R.layout.fragment_new_ingestion, container,false);
        final GridLayout grid = (GridLayout) thisFragView.findViewById(R.id.newILayout);
        final GridLayout grid2 = (GridLayout) thisFragView.findViewById(R.id.toConfirmIngestion);
        //выборка айди и имен
        DBHelper dbH = new DBHelper(getActivity());
        SQLiteDatabase thisDB = dbH.getReadableDatabase();
        Cursor prodInfoCursor = dbH.selectAllProdNames(thisDB);

        String[] namesOfAll = getAllNames(prodInfoCursor);
        //Create first Line
        createNewLine(grid,namesOfAll);

        createNavButtons(grid2,grid,namesOfAll);

        dbH.close();
        prodInfoCursor.close();

        return thisFragView;
    }

    public String[] getAllNames(Cursor c){
        if(c.moveToFirst()){
            List<String> hints = new ArrayList<String>();
            do{
                hints.add(c.getString(1));
            }while(c.moveToNext());
            String[] hint = hints.toArray(new String[0]);
            return hint;
        }
        return new String[0];
    }

    public void createNewLine(final GridLayout g,final String[] names){
        Random rn = new Random();
        final int newTag = rn.nextInt();

        AutoCompleteTextView au = new AutoCompleteTextView(getActivity());
        GridLayout.LayoutParams auPar = new GridLayout.LayoutParams();
        //au.setText("abc");
        auPar.columnSpec = GridLayout.spec(0,2);
        auPar.setGravity(Gravity.FILL_HORIZONTAL);
        au.setLayoutParams(auPar);
        au.setHint("Product name");
        au.setTag(newTag+"au");
        au.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,names));
        g.addView(au);

        EditText et = new EditText(getActivity());
        GridLayout.LayoutParams etPar = new GridLayout.LayoutParams();
        et.setLayoutParams(etPar);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        et.setHint("g");
        et.setTag(newTag+"et");
        g.addView(et);

        Button delB = new Button(getActivity());
        GridLayout.LayoutParams delBPar = new GridLayout.LayoutParams();
        delB.setText("X");
        delB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.removeView(g.findViewWithTag(newTag+"au"));
                g.removeView(g.findViewWithTag(newTag+"et"));
                g.removeView(g.findViewWithTag(newTag));
            }
        });
        delB.setTag(newTag);
        g.addView(delB);

    }

    public void createNavButtons(final GridLayout g, final GridLayout g2,final String[] names){
        Button plusProdButton = new Button(getActivity());
        GridLayout.LayoutParams plusProdButtonPar = new GridLayout.LayoutParams();
        plusProdButtonPar.columnSpec = GridLayout.spec(0,4);
        plusProdButtonPar.setGravity(Gravity.CENTER_HORIZONTAL);
        plusProdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewLine(g2,names);
            }
        });
        plusProdButton.setText("+");
        plusProdButton.setLayoutParams(plusProdButtonPar);
        g.addView(plusProdButton);

        //create Error Field
        TextView errorText = new TextView(getActivity());
        GridLayout.LayoutParams errorTextPar = new GridLayout.LayoutParams();
        errorTextPar.columnSpec = GridLayout.spec(0,3);
        errorTextPar.setGravity(Gravity.RIGHT);
        //errorText.setTextColor(Color.parseColor("#00ffff22"));
        errorText.setText("*check All");
        errorText.setLayoutParams(errorTextPar);
        g.addView(errorText);

        //create Check button
        Button checkIngestionButton = new Button(getActivity());
        //GridLayout.LayoutParams checkIngestionButtonPar = new GridLayout.LayoutParams();
        checkIngestionButton.setText("OK");
        //checkIngestionButton.setLayoutParams(checkIngestionButtonPar);
        checkIngestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllInIngestion(g2,names);
            }
        });
        g.addView(checkIngestionButton);
    }

    public void checkAllInIngestion(GridLayout g,String[] allProdNames){
        int count = g.getChildCount();//узнаем количество элементов
        if(count > 0) { //если есть дети
            ArrayList<String> prodNames = new ArrayList<String>();//для названий
            ArrayList<Integer> prodWeights = new ArrayList<Integer>();//для веса
            for (int i = 0; i < count / 3; i++) {//ili i+=3,[i/3]//количество строк
                //обновление текущих данных
                String av = ((AutoCompleteTextView) g.getChildAt(i * 3)).getText().toString();
                String et = ((EditText) g.getChildAt(i * 3 + 1)).getText().toString();
                //Проверяем на пустоту
                if (av.isEmpty() || et.isEmpty()) {
                    //все пусто
                } else {//НЕ пусты
                    String prodName = av.trim();//значение названия
                    int prodWeight = Integer.parseInt(et.trim());//значение веса
                    if (!prodName.isEmpty() && prodWeight >= 1) {//если нет нулей в полях
                        if(Arrays.asList(allProdNames).contains(prodName)){//если есть данное значение в массиве из базы
                            //делаем запись в массив
                            prodNames.add(prodName);
                            prodWeights.add(prodWeight);
                        }else{
                            Toast a = Toast.makeText(getActivity(), "Есть нули в полях", Toast.LENGTH_SHORT);
                            a.show();
                        }
                    } else {//All is bad
                        Toast a = Toast.makeText(getActivity(), "Massivi pustie", Toast.LENGTH_SHORT);
                        a.show();
                    }

                }
            }//end of for.
            // Проверили все поля
            if (prodNames.size() >= 1 && prodWeights.size() >= 1) {
                String[] elNames = prodNames.toArray(new String[prodNames.size()]);
                Integer[] gWei = prodWeights.toArray(new Integer[prodWeights.size()]);

                //ищем данные
                DBHelper db = new DBHelper(getActivity());
                Cursor allElData = db.selectProd(db.getReadableDatabase(),elNames);
                if(allElData.moveToFirst()){//если курсор не пуст
                    StringBuilder totalNames = new StringBuilder();
                    float totalCalories = 0.f;
                    float totalProteins = 0.f;
                    float totalFats = 0.f;
                    float totalCarbohydrates = 0.f;
                    float totalCellulose = 0.f;
                    for (int i=0; !allElData.isAfterLast(); allElData.moveToNext()) {
                        totalNames.append(allElData.getInt(0)+",");
                        //allElData.getString(1);// NAME
                        totalCalories += allElData.getFloat(2)*gWei[i]/100;// CALORIES
                        totalProteins += allElData.getFloat(3)*gWei[i]/100;// PROTEINS
                        totalFats += allElData.getFloat(4)*gWei[i]/100;// FATS
                        totalCarbohydrates += allElData.getFloat(5)*gWei[i]/100;// CARBOHYDRATES
                        totalCellulose += allElData.getFloat(6)*gWei[i]/100;// CELLULOSE
                    }
                    totalNames.deleteCharAt(totalNames.length()-1);
                    //для округления
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                   // float total1Proteins = df.format(totalProteins);
                    float newtotalCalories = Float.parseFloat(df.format(totalCalories));
                    float newtotalProteins = Float.parseFloat(df.format(totalProteins));
                    float newtotalFats = Float.parseFloat(df.format(totalFats));
                    float newtotalCarbohydrates = Float.parseFloat(df.format(totalCarbohydrates));
                    float newtotalCellulose = Float.parseFloat(df.format(totalCellulose));



                    //запись в базу
                    //db.insertNewMeal(db.getReadableDatabase(),totalNames.toString(),totalCalories,totalProteins,totalFats,totalCarbohydrates,totalCellulose);
                    // add to DB
                    db.insertNewMeal(db.getWritableDatabase(),totalNames.toString(),newtotalCalories,newtotalProteins,newtotalFats,newtotalCarbohydrates,newtotalCellulose);

                    //
                    //
                    //
                    /*String a = totalNames.toString()+" "+newtotalCalories+" "+newtotalProteins+" "+newtotalFats+" "+newtotalCarbohydrates+" "+newtotalCellulose;
                    Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();*/

                }else{
                    //all is bad
                    Toast.makeText(getActivity(), "Проблема с курсором", Toast.LENGTH_SHORT).show();
                }
                /*String p = " " + allElData.getCount();
                Toast a = Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT);
                a.show();*/
                String p = elNames.length + " " + gWei.length;
                Toast a = Toast.makeText(getActivity(), p, Toast.LENGTH_SHORT);
                a.show();
            } else {// пустые массивы
                Toast a = Toast.makeText(getActivity(), "Массивы", Toast.LENGTH_SHORT);
                a.show();
            }
        }else{// нет детей
            Toast.makeText(getActivity(),"Нет детей",Toast.LENGTH_SHORT).show();
        }


    }

}