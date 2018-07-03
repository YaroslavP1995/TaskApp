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
    int PAGE_COUNT = 1;
    //int page = 1;
    private ViewPager pager;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    //PagerAdapter pagerAdapter;
    //клас пейдж и пейдж намбер

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(myFragmentPagerAdapter);

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
    //фрагмент не правильно
    //в онстарты гет интент якщо ынтент
    //зробити лыст ынтегерыв який рывен 1
    //повысити на оклыыыки лыстенери listAdd  лыст сайз збыльшкувати зменшувати
    //getCount
    //
    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    public void plusFragment(View v){
        PAGE_COUNT++ ;
        myFragmentPagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(PAGE_COUNT);

    }
    public void minusFragment(View v){
        PAGE_COUNT--;
        myFragmentPagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(PAGE_COUNT);
    }

    public void showNotification(View v){

        int count = (pager.getCurrentItem()+1);

        Intent notificationIntent = new Intent(this,MainActivity.class);
        notificationIntent.putExtra("DO",count);

        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);

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

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "shiva said right");
        intent = getIntent();
        int actions = intent.getIntExtra("DO",0);
        if (actions != 0) {
            Log.d(TAG, "shiva said ");
            pager.setCurrentItem(actions);
        }
        super.onNewIntent(intent);
    }

   private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
       MyFragmentPagerAdapter(FragmentManager fm) {
           super(fm);
       }

       @Override
       public Fragment getItem(int position) {
           switch (position) {
               case 0:
                   return FragmentOne.newInstance(position);
           }
           return FragmentTwo.newInstance(position);
       }
       @Override
       public int getItemPosition(Object object) {
           return PagerAdapter.POSITION_NONE;
       }

       @Override
       public int getCount() {
           return PAGE_COUNT;
       }
   }
}
