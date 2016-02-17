package com.example.thadaninilesh.approvalchain;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class BossActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentAddEmployee.FragmentBoss, FragmentBossEditDetails.FragmentBoss {
    SharedPreferences sharedPreferences;
    @Override
    public void addEmployee(String method, String name, String email, String password, String phone) {
        BossBackgroundTask bossBackgroundTask = new BossBackgroundTask(this);
        bossBackgroundTask.execute(method, name, email, password, phone);
    }

    @Override
    public void editDetails(String email, String method, String name, String phone) {
        DetailsBackgroundClass detailsBackgroundClass = new DetailsBackgroundClass(this);
        detailsBackgroundClass.execute(email, method,name, phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame_boss, new FragmentAddEmployee()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.boss, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.add_employee) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_boss, new FragmentAddEmployee()).commit();
        } else if (id == R.id.pending_approvals) {
            Fragment fragmentManagerForApproval = new FragmentManagerForApproval();
            //FragmentBossBackgroundClass fragmentBossBackgroundClass = new FragmentBossBackgroundClass(this);
            sharedPreferences = getSharedPreferences("ApprovalChain", Context.MODE_PRIVATE);
            final String email = sharedPreferences.getString("Email", "");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentBossBackgroundClass.execute("pendingapprovals",email);
            //fragmentTransaction.replace(R.id.content_frame_boss, fragmentApprovedApprovals).commit();
        } else if (id == R.id.approved_approvals) {
            Fragment fragmentManagerForApproval = new FragmentManagerForApproval();
            //FragmentBossBackgroundClass fragmentBossBackgroundClass = new FragmentBossBackgroundClass(this);
            sharedPreferences = getSharedPreferences("ApprovalChain", Context.MODE_PRIVATE);
            final String email = sharedPreferences.getString("Email", "");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentBossBackgroundClass.execute("pendingapprovals", email);
            //fragmentTransaction.replace(R.id.content_frame_boss, fragmentApprovedApprovals).commit();
        }
         else if (id == R.id.logout) {
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute("logout");
            finish();
        }
        else if (id == R.id.edit_details){
            Fragment fragmentBossEditDetails = new FragmentBossEditDetails();
            DetailsBackgroundClass detailsBackgroundClass = new DetailsBackgroundClass(this);
            sharedPreferences = getSharedPreferences("ApprovalChain",Context.MODE_PRIVATE);
            final String email = sharedPreferences.getString("Email","");
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            detailsBackgroundClass.execute(email,"show");
            fragmentTransaction.replace(R.id.content_frame_boss, fragmentBossEditDetails).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
