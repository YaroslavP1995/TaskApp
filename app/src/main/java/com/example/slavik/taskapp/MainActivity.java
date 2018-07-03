package com.example.slavik.taskapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class MainActivity extends FragmentActivity {

    private int counter;
    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 10;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(final int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageSelected, state = " + state);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    public void plusFragment(View v){
        pager.setCurrentItem(pager.getCurrentItem() + 1 );
    }
    public void minusFragment(View v){
        pager.setCurrentItem(pager.getCurrentItem() - 1 );
    }

    public void showNotification(View v){

        int count = (pager.getCurrentItem()+1);
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Resources resources = this.getResources();

        NotificationCompat.Builder build = new NotificationCompat.Builder(this);
        build.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(resources.getString(R.string.you_create_a_notification))
                .setContentText(resources.getString(R.string.notification)+" "+count)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

         NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(counter++,build.build());

    }

   private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
       MyFragmentPagerAdapter(FragmentManager fm) {
           super(fm);
       }

       @Override
       public Fragment getItem(int position) {
           switch (position) {
               case 0:
                   return FragmentOne.newInstance(position+1);
           }
           return FragmentTwo.newInstance(position+1);
       }

       @Override
       public int getCount() {
           return PAGE_COUNT;
       }
   }
}
