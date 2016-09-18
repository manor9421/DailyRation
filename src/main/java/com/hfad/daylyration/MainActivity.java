package com.hfad.daylyration;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    ActionBarDrawerToggle drawerToggle;

    private String[] capabilitiesMenu;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //стандарт
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получаем элементы меню
        capabilitiesMenu = getResources().getStringArray(R.array.capabilitiesMenu);
        //получаем место для списка
        drawerList = (ListView) findViewById(R.id.drawer);
        //получаем Лейаут
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //задаем адаптер(в место для списка) для элементов меню
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, capabilitiesMenu));
        //задаем слушателя для щелчков по элементам списка
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //Display the correct fragment
        if(savedInstanceState != null){//если восстанавливаемся
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }else{//если нет
            selectItem(0);//выбранный элемент - 0(дефолтный)
        }

        //Создаем ActionBarDrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.open_drawer, R.string.close_drawer) {
            //Called when a drawer has settled in a completely closed state
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();//выполняем onPrepareOptionsMenu. сообщает Android, что элементы меню, которые должны находиться на панели действий, изменились и их необходимо создать заново
            }
            //Called when a drawer has settled in a completely open state.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        //задаем Лейауту слушатель
        drawerLayout.setDrawerListener(drawerToggle);

        //Включить кнопку Вверх, чтобы она могла
        // использоваться объектом ActionBarDrawerToggle
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);






        /////////////
        /*getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        FragmentManager fragMan = getFragmentManager();
                        Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
                        if (fragment instanceof TopFragment) {
                            currentPosition = 0;
                        }
                        if (fragment instanceof PizzaMaterialFragment) {
                            currentPosition = 1;
                        }
                        if (fragment instanceof PastaFragment) {
                            currentPosition = 2;
                        }
                        if (fragment instanceof StoresFragment) {
                            currentPosition = 3;
                        }
                        setActionBarTitle(currentPosition);
                        drawerList.setItemChecked(currentPosition, true);

                    }
                }
        );*/


    }



    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    //Drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    private void selectItem(int position){
        currentPosition = position;
        Fragment frag;
        switch(position){
            case 1:
                frag = new AddProductFragment();
                break;//
            case 2:
                frag = new ProductListFragment();
                break;//
            case 3:
                frag = new NewIngestionFragment();
                break;
            case 4:
                frag = new DailyCaloriesFragment();
                break;//
            case 5: finish(); System.exit(0);//Activity.finish()//
            default: frag = new TopFragment();
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, frag,"visible_fragment");
        //ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        setActionBarTitle(position);
        drawerLayout.closeDrawer(drawerList);

    }

    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    //добавляем элементы в меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Заполнение меню; элементы (если они есть) добавляются на панель действий
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //чтобы объект ActionBarDrawerToggle реагировал на щелчки
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)) {
            /*возвращает true, если объект ActionBarDrawerToggle обработал щелчок.
            Если вызов возвращает false, это означает, что щелчок был сделан
            на другом элементе действия на панели действий; в этом случае будет
            выполнен остальной код в методе onOptionsItemSelected() активности*/
            return true;
        }
        //Fragment menuFrag;
        FragmentTransaction frt = getFragmentManager().beginTransaction();
        //какой элемент выбрал пользователь
        switch (item.getItemId()) {
            case R.id.action_new_in:////Код, выполняемый при щелчке на действии Create Order
                //Code to run when the Create Order item is clicked
                frt.replace(R.id.content_frame, new NewIngestionFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
                getActionBar().setTitle("New Ingestion");
                return true;
            case R.id.action_today_ration:
                //Code to run when the settings item is clicked
                frt.replace(R.id.content_frame, new DailyCaloriesFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
                getActionBar().setTitle("Daily calories");
                //Transaction of fragm;
                return true;//сообщить, что был Щелчок*/
            case R.id.action_dev_inf:
                //
                //Transaction of fragm;
                frt.replace(R.id.content_frame, new DevInfoFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
                getActionBar().setTitle("Developer info");
                return true;
            default:
                frt.replace(R.id.content_frame, new TopFragment(),"visible_fragment");
                //ft.addToBackStack(null);
                frt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                frt.commit();
                getActionBar().setTitle("Daily ration");
                return super.onOptionsItemSelected(item);
        }
    }
    //При вызове метода invalidateOptionsMenu() вызывается метод onPrepareOptionsMenu() активности
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        // Если выдвижная панель открыта, скрыть элементы, связанные с контентом
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        menu.findItem(R.id.action_today_ration).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    //Метод syncState() должен вызываться в методе onPostCreate() активности, чтобы
    // объект ActionBarDrawerToggle находился в правильном состоянии после создания активности
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        // Синхронизировать состояние выключателя после onRestoreInstanceState
        drawerToggle.syncState();
    }
    //Для передачи информации о любых изменениях конфигурации ActionBarDrawerToggle
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }








    public void setActionBarTitle(int position){
        String title;
        switch(position) {
            case 1:
                title = "Add new product";
                break;
            case 2:
                title = "All products";
                break;
            case 3:
                title = "New ingestion";
                break;
            case 4:
                title = "Your daily calories";
                break;
            default:
                title = "DailyRation";
        }
        getActionBar().setTitle(title);
    }
}
