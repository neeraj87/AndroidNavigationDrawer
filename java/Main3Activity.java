package com.violet.neerajjadhav.navigationdrawertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main3, frameLayout);
        mDrawerList.setItemChecked(position, true);
        setTitle(navItemsTitles[position]);
    }
}
