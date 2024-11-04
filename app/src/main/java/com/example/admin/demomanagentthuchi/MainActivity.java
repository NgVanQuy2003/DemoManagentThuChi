package com.example.admin.demomanagentthuchi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.admin.demomanagentthuchi.ViewPager.ThongKeTabPager;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ActionBarDrawerToggle toggle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.shitStuff);
        toolbar = findViewById(R.id.toolBar);

        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //set toolbar thay the cho actionbar

        //set color opacity navigation drawer auto with system.
        getWindow().setStatusBarColor(Color.parseColor("#20111111"));
        //set color opacity navigation drawer auto with system

        //Gắn fagment mặt định cho giao diện activity khi chưa chọn shitstuff
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new ThuFragment()).commit();
        //Gắn fragment mặt đình cho giao diện activity khi chưa chọn shitstuff

        //Even when click on item navigationDrawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                XuLyChonMenu(item);
                return false;
            }
        });
        //Even when click on item of navigationdrawer

        //set icon auto for navigation drawer
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name,R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        //set icon auto for navigation drawer
    }

    //////////////////////////////
    public void XuLyChonMenu(MenuItem item) {
        Fragment fragment = null;
        Class classfragment = null;
        //
        if (item.getItemId() == R.id.KThu)
            classfragment = ThuFragment.class;
        if (item.getItemId() == R.id.KChi)
            classfragment = ChiFragment.class;
        if (item.getItemId() == R.id.TKe)
            classfragment = ThongKeTabFragment.class;
        if (item.getItemId() == R.id.GThieu) {
            Intent i = new Intent(MainActivity.this, InforActivity.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.Thoat)
            DialogExit();

        try {
            fragment = (Fragment) classfragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

            //Even when click on item for navigation Drawer
            item.setChecked(true);
            setTitle(item.getTitle());
            drawerLayout.closeDrawer(GravityCompat.START);
            //Even when click on item for navigation drawer

        } catch (Exception e) {
        }
    }

    //////////////////////////////
    public void DialogExit() {
        //View dialog exit for even click on item exit of navigationDrawer
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inf = MainActivity.this.getLayoutInflater();
        View view = inf.inflate(R.layout.dialog_exit, null);
        alertDialog.setView(view);
        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
        //View dialog exit for even click on item exit of navigationDrawer
    }
}
