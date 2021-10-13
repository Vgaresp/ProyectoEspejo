package com.example.appespejo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeActivity extends AppCompatActivity {
   // private String[] nombres = new String[]{"Luces","Pestaña 2","Pestaña 3", "Pesataña 4", "Pesataña 5"};

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Tab1()).commit();


        /*ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiPagerAdapter(this));
        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position){
                        tab.setText(nombres[position]);
                    }
                }
        ).attach();*/
    }
    private NavigationBarView.OnItemSelectedListener bottomNavMethod = new
            NavigationBarView.OnItemSelectedListener(){
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                    Fragment fragment=null;

                    switch (menuItem.getItemId()){
                        case R.id.ligth:
                        fragment=new Tab1();
                        break;

                        case R.id.music:
                            fragment=new Tab2();
                        break;

                        case R.id.photo:
                            fragment=new Tab3();
                        break;

                        case R.id.perfil:
                            fragment=new Tab4();
                        break;

                        case R.id.conf:
                            fragment=new Tab5();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };
    /*Tab1 tab1 = new Tab1();
    Tab2 tab2 = new Tab2();
    Tab3 tab3 = new Tab3();
    Tab4 tab4 = new Tab4();
    Tab5 tab5 = new Tab5();*/

    /*public class MiPagerAdapter extends FragmentStateAdapter {
        public MiPagerAdapter(FragmentActivity activity){
            super(activity);
        }
        @Override
        public int getItemCount() {
            return 5;
        }
        @Override @NonNull
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return new Tab1();
                case 1: return new Tab2();
                case 2: return new Tab3();
                case 3: return new Tab4();
                case 4: return new Tab5();
            }
            return null;
        }
    }*/
    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ligth:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tab1).commit();
                return true;

            case R.id.music:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tab2).commit();
                return true;

            case R.id.photo:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tab3).commit();
                return true;
            case R.id.perfil:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tab4).commit();
                return true;
            case R.id.conf:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, tab5).commit();
                return true;
        }
        return false;
    }*/


}
