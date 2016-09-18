package com.hfad.daylyration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by gdx on 04/07/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "foods";
    private static final int DB_VERSION = 2;

    DBHelper(Context c){
        super(c,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        updateDB(db,0,DB_VERSION);

    }

    private void updateDB(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion < 1){
            //create DB
            db.execSQL("CREATE TABLE FOOD(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        +"NAME TEXT, "
                        +"CALORIES FLOAT, "
                        +"PROTEINS FLOAT, "
                        +"FATS FLOAT, "
                        +"CARBOHYDRATES FLOAT, "
                        +"CELLULOSE FLOAT)");
            db.execSQL("CREATE TABLE USER_MEALS("
                    +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +"DATE INTEGER, "
                    +"PRODUCTS TEXT, "
                    +"CALORIES FLOAT, "
                    +"PROTEINS FLOAT, "
                    +"FATS FLOAT, "
                    +"CARBOHYDRATES FLOAT, "
                    +"CELLULOSE FLOAT)");
            //заполняем базу
            insertFood(db,"Watermelon", 38f,0.7f,0.5f,0.2f,10.9f);
            insertFood(db,"Eggplant",24f,1.2f,1.3f,0.1f,7.1f);
            insertFood(db,"Bananas", 89f,1.5f,0.8f,0.1f,21.8f);
            insertFood(db,"Shelling peas", 299f,23f,10.7f,1.6f,48.1f);
            insertFood(db,"Peas, dry",298f,20.5f,5.7f,2f,53.3f);
            insertFood(db,"Green peas",73f,5f,5.5f,0.2f,13.8f);
            insertFood(db,"Buckwheat",335f,12.6f,1.1f,3.3f,68f);
            insertFood(db,"Pear", 57f,0.4f,3.1f,0.1f,15f);
            insertFood(db,"Dried Pears",249f,2.3f,6f,0.6f,62.6f);
            insertFood(db,"Daikon",18f,0.6f,1.6f,0.1f,4.1f);
            insertFood(db,"Wild rice",357f,15f,6f,1.1f,75f);
            insertFood(db,"Green buckwheat",310f,12.6f,1.3f,3.3f,62f);
            insertFood(db,"Boiled turkey",195f,25.3f,0f,10.4f,0f);
            insertFood(db,"Roast turkey",165f,28f,0f,6f,0f);
            insertFood(db,"Yogurt", 64f,5f,0f,0.05f,13.5f);
            insertFood(db,"Cocoa",410f,3f,4f,15f,75f);
            insertFood(db,"Cappuccino", 428f,20.3f,3.2f,13.9f,53.7f);
            insertFood(db,"Kefir 0%", 26f,3f,0f,0f,3.5f);
            insertFood(db,"Dogwood",40.4f,2f,1.5f,0f,9.7f);
            insertFood(db,"Strawberries", 33f,0.7f,2f,0.3f,8f);
            insertFood(db,"Corn frozen",81f,2.55f,2.4f,0.67f,19.3f);
            insertFood(db,"Corn grits",337f,8.3f,2.1f,1.2f,75f);
            insertFood(db,"Apricots", 215f,5.2f,3.2f, 0.3f,51f);
            insertFood(db,"Chicken breast",113f,23.6f,0f,1.9f,0.4f);
            insertFood(db,"Chicken cooked",170f,25.2f,0f,7.4f,0f);
            insertFood(db,"Pita", 219f,7.2f,0.2f,0.8f,48f);
            insertFood(db,"Green Onions", 19f,1.3f,0.9f,0f,4.6f);
            insertFood(db,"Onions",41f,1.4f,0.7f,0f,10.4f);
            insertFood(db,"Macaroni",359f,12.8f,3f,2f,70.9f);
            insertFood(db,"Semolina",328f,10.3f,0.2f,1f,73.3f);
            insertFood(db,"Olive oil", 898f,0f,0f,99.8f,0f);
            insertFood(db,"Honey",304f,0.3f,0f,0f,82f);
            insertFood(db,"Milk",54f,2.8f,0f,2.6f,4.7f);
            insertFood(db,"Milk powder 1.5%",345f,32.6f,0f,1.5f,25f);
            insertFood(db,"Carrots",34f,1.3f,1.2f,0.1f,9.3f);
            insertFood(db,"Corn flour",327f,7.2f,4.4f,1.5f,75.8f);
            insertFood(db,"Wheat flourf, wholegrain",290f,11.2f,9.3f,2.1f,55.2f);
            insertFood(db,"Nut",364f,19f,17f,6f,61f);
            insertFood(db,"Oatmeal",303f,11f,2.8f,6.1f,65.4f);
            insertFood(db,"Oatmeal, wholegrain",342f,12.3f,11f,6.1f,59.5f);
            insertFood(db,"Cucumber",16f,0.6f,0.5f,0.1f,3.6f);
            insertFood(db,"Nuts",648f,15.5f,1.5f,65f,10.1f);
            insertFood(db,"Oat bran",279f,22f,16f,5.7f,35f);
            insertFood(db,"Wheat bran",208f,15.5f,43f,5.3f,25.6f);
            insertFood(db,"Rye bran",238f,14.5f,8.4f,3.5f,35f);
            insertFood(db,"Red sweet pepper ",27f,1.3f,0.3f,1.4f,7.2f);
            insertFood(db,"Barley",320f,9.3f,1f,1.1f,73.7f);
            insertFood(db,"Fried Beef liver",199f,22.9f,0f,10.2f,3.9f);
            insertFood(db,"Boiled Beef Liver",115f,19.5f,0f,3.4f,1.45f);
            insertFood(db,"Chicken liver boiled",166f,25.9f,0f,6.2f,2f);
            insertFood(db,"Boiled pork liver",109f,18.8f,0f,3.8f,4.7f);
            insertFood(db,"Tomatoes",18f,0.9f,1.2f,0.2f,3.9f);
            insertFood(db,"Wheat groats",316f,11.5f,4.6f,1.3f,62f);
            insertFood(db,"Millet grits",348f,11.5f,0.7f,3.3f,69.3f);
            insertFood(db,"Figure",323f,7.5f,2f,2.6f,56.1f);
            insertFood(db,"Sugar",379f,0f,0f,0f,99.8f);
            insertFood(db,"Flax seeds",534f,18.3f,27f, 42.2f,28.9f);
            insertFood(db,"Plum", 43f,0.8f,0.5f,0.3f,11.2f);
            insertFood(db,"Currant",56f,1.4f,4.3f,0.1f,14f);
            insertFood(db,"Soybean oil",899f,0f,0f,99.9f,0f);
            insertFood(db,"Soy meat", 396f,40f,4f,16f,23f);
            insertFood(db,"Soy sauce",60f,4f,0f,0f,11f);
            insertFood(db,"Soy",332f,34.9f,4.3f,17.3f,26.5f);
            insertFood(db,"Serum", 18.75f,1f,0f,0f,3.5f);
            insertFood(db,"Cheese",384f,33f,0f,28f,0f);
            insertFood(db,"Cottage cheese 0%",84f,18f,0f,0.6f,1.8f);
            insertFood(db,"Cottage cheese",159f,16.7f,0f,9f,2f);
            insertFood(db,"Veal boiled",97f,19.7f,0f,2f,0f);
            insertFood(db,"Pumpkin", 25f,1f,1.2f,0.1f,5.9f);
            insertFood(db,"Dill",40f,2.5f,28f,0.5f,6.3f);
            insertFood(db,"Beans",23f,2.5f,3.4f,0.3f,3f);
            insertFood(db,"Bread", 269f,8.7f,0f,3.8f,49f);
            insertFood(db,"Garlic",149f,6f,2.1f,0.5f,33f);
            insertFood(db,"Lentils",284f,24f,3.7f,1.1f,53.7f);
            insertFood(db,"Lentils dried",284f,24f,8f,1.5f,42.7f);
            insertFood(db,"Mushrooms",27f,4.3f,0.9f,1f,1f);
            insertFood(db,"Apples",45f,0.4f,0.6f,0.4f,11.8f);
            insertFood(db,"Boiled beef tongue",231f,23.9f,0f,15f,0f);
            insertFood(db,"Egg", 157f,12.7f,0f,10.9f,0.7f);
            insertFood(db,"Egg protein",44f,11.1f,0f,0f,0f);
            insertFood(db,"Egg yolk",352f,16.2f,0f,31.2f,1f);
            insertFood(db,"Wheat (whole)",295f,10f,9.6f,1f,60f);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        updateDB(db,oldVersion,newVersion);
    }

    public void insertFood(SQLiteDatabase db, String name, float calories,float proteins,
                           float fats, float carbohydrates, float cellulose){
        ContentValues foodValues = new ContentValues();
        foodValues.put("NAME", name);
        foodValues.put("CALORIES", calories);
        foodValues.put("PROTEINS", proteins);
        foodValues.put("FATS", fats);
        foodValues.put("CARBOHYDRATES", carbohydrates);
        foodValues.put("CELLULOSE", cellulose);
        db.insert("FOOD", null, foodValues);

    }

    /*public void insertNewMeal(SQLiteDatabase db,String date,String products){
        ContentValues foods = new ContentValues();
        foods.put("DATE",date);
        foods.put("PRODUCTS", products);
        db.insert("USER_MEALS",null,foods);
    }*/
    public Cursor dailyCalProds(SQLiteDatabase db){
        Cursor c = db.query("USER_MEALS",
                new String[]{"DATE","PRODUCTS","CALORIES","PROTEINS","FATS","CARBOHYDRATES","CELLULOSE"},//"date BETWEEN '2011-01-11' AND '2011-8-11'"
                null, null, null, null, "DATE DESC", "10");
        return c;
    }

    public Cursor selectAllProdNames(SQLiteDatabase db){
        Cursor c = db.query("FOOD",
                new String[]{"_id","NAME"},
                null, null, null,null,    null);//"ORDER BY NAME DESC"



        return c;
    }
    //выбираем продукты
    public Cursor selectProd(SQLiteDatabase db,String[] names){
        StringBuilder que = new StringBuilder();
        int nLen = names.length;// 0.1.2.3.4    5
        for(int i=0;i<nLen;i++){
            que.append("?,");
        }
        que.deleteCharAt(que.length()-1);
        Cursor cr = db.query("FOOD",
                new String[]{"_id","NAME","CALORIES","PROTEINS","FATS","CARBOHYDRATES","CELLULOSE"},
                "NAME IN("+que+")",
                names,null,null,null);
        return cr;
    }
    public void insertNewMeal(SQLiteDatabase db,String totalNames,Float totalCalories,Float totalProteins,Float totalFats,Float totalCarbohydrates,Float totalCellulose){
        ContentValues newIngestionValues = new ContentValues();
        Date d = new Date();
        newIngestionValues.put("DATE",d.getTime()/1000);//Сохраняем СЕКУНДЫ!!!
        newIngestionValues.put("PRODUCTS", totalNames);
        newIngestionValues.put("CALORIES", totalCalories);
        newIngestionValues.put("PROTEINS", totalProteins);
        newIngestionValues.put("FATS", totalFats);
        newIngestionValues.put("CARBOHYDRATES", totalCarbohydrates);
        newIngestionValues.put("CELLULOSE", totalCellulose);
        db.insert("USER_MEALS", null, newIngestionValues);
    }
    public Cursor allList(SQLiteDatabase db){
        Cursor c = db.query("FOOD",
                new String[]{"NAME", "CALORIES", "PROTEINS", "FATS", "CARBOHYDRATES", "CELLULOSE"},
                null, null, null, null, "NAME ASC");
        return c;
    }

}
