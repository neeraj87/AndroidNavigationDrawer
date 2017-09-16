package com.violet.neerajjadhav.navigationdrawertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by neerajjadhav on 9/14/17.
 */

/**
* This activity will add Navigation Drawer for our application and all the code related to navigation drawer.
* We are going to extend all our other activites from this BaseActivity so that every activity will have Navigation Drawer in it.
* This activity layout contain one frame layout in which we will add our child activity layout.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     *  Frame layout: Which is going to be used as parent layout for child activity layout.
     *  This layout is protected so that child activity can access this
     *  */
    protected FrameLayout frameLayout;

    /**
     * ListView to add navigation drawer item in it.
     * We have made it protected to access it in child class.
     * We will just use it in child class to make item selected according to activity opened.
     */
    protected ListView mDrawerList;

    /**
     * List item array for navigation drawer items.
     * */
    protected String[] navItemsTitles;
    protected ArrayList<Items> navItemsArray;
    /**
     * Static variable for selected item position. Which can be used in child activity to know which item is selected from the list.
     * */
    protected static int position;

    /**
     *  This flag is used just to check that launcher activity is called first time
     *  so that we can open appropriate Activity on launch and make list item position selected accordingly.
     * */
    private static boolean isLaunched = true;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        navItemsTitles = getResources().getStringArray(R.array.nav_drawer_items);
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        navItemsArray = new ArrayList<Items>();

        navItemsArray.add(new Items("Item 1", "Item description", R.drawable.home_icon));
        navItemsArray.add(new Items("Item 2", "Item description", R.drawable.announcement_icon));
        navItemsArray.add(new Items("Item 3", "Item description", R.drawable.avatar_male));
        navItemsArray.add(new Items("Item 4", "Item description", R.drawable.case_icon));
        navItemsArray.add(new Items("Item 5", "Item description", R.drawable.settings_icon));
        navItemsArray.add(new Items("Item 6", "Item description", R.drawable.buildings_icon));

        //Adding header on list view
        View header = (View) getLayoutInflater().inflate(R.layout.list_view_header_layout, null);
        mDrawerList.addHeaderView(header);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new NavigationDrawerListAdapter(this, navItemsArray));

        //Set the list's on-item click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                openActivity(position);
            }
        });

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //use your own icon from drawable folder to show/hide the nav drawer
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.blue_violet_icon);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(navItemsTitles[position]);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(getString(R.string.app_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        };

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        /******************* IMP *********************/
        /** As soon as the app is launched, since BaseActivity is the LAUNCHER activity
         * and isLaunched is set to true (in the declaration, the below if is called onCreate
         * we send the value 0 to openActivity(int) which then called the MainActivity
         * remove this code if you do not want to call any activity on launch OR
         * if your BaseActivity is not the LAUNCHER activity
         */
        if(isLaunched) {
            isLaunched = false;
            openActivity(0);
        }
    }

    protected void openActivity(int position) {
        mDrawerLayout.closeDrawer(mDrawerList);

        BaseActivity.position = position;

        switch (position) {
            case 0:
                Log.i("NAV", "---- item 0");
                startActivity(new Intent(BaseActivity.this, MainActivity.class));
                break;
            case 1:
                Log.i("NAV", "---- item 1");
                startActivity(new Intent(BaseActivity.this, Main2Activity.class));
                break;
            case 2:
                Log.i("NAV", "---- item 2");
                startActivity(new Intent(BaseActivity.this, Main3Activity.class));
                break;
            case 3:
                Log.i("NAV", "---- item 3");
                break;
            case 4:
                Log.i("NAV", "---- item 4");
                break;
            case 5:
                Log.i("NAV", "---- item 5");
                break;
            default:
                break;
        }

        Toast.makeText(this, "Selected Item Position::"+position, Toast.LENGTH_LONG).show();
    }

    //Before you do this, we need to create a new folder called menu in res directory
    //Right click menu > New > Menu Resource File
    //Paste the content
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(actionBarDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }

        switch (menuItem.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(BaseActivity.this, Main4Activity.class));
                return true;
            case R.id.profile_settings:
                startActivity(new Intent(BaseActivity.this, Main5Activity.class));
                return true;
            case R.id.logout_settings:
                startActivity(new Intent(BaseActivity.this, Main4Activity.class));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        menu.findItem(R.id.logout_settings).setVisible(!drawerOpen);
        menu.findItem(R.id.profile_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /* We can override onBackPressed method to toggle navigation drawer*/
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(mDrawerList)){
            mDrawerLayout.closeDrawer(mDrawerList);
        }else {
            mDrawerLayout.openDrawer(mDrawerList);
        }
    }

}
