package com.uw.bbm;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.view.animation.LinearInterpolator;
import android.widget.*;
import com.uw.bbm.constant.Constants;
import com.uw.bbm.controller.FetchData;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    // store if this activity is in foreground
    private boolean visibility = false;

    private ContactGridAdapter mGridAdapter;

    // animation for refresh button
    private final int[] ids = {
            R.drawable.ic_popup_sync_1,
            R.drawable.ic_popup_sync_2,
            R.drawable.ic_popup_sync_3,
            R.drawable.ic_popup_sync_4,
            R.drawable.ic_popup_sync_5,
            R.drawable.ic_popup_sync_6,
    };
    final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, ids.length - 1).setDuration(800);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListeners();
    }

    // receive message from connection with MC
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (visibility) {
                switch (msg.what) {
                    case Constants.MESSAGE_GET_SCHEDULE_SUCCESS:
                        mGridAdapter.notifyDataSetChanged();
                        valueAnimator.end();
                        break;
                    case Constants.MESSAGE_GET_SCHEDULE_FAIL:
                        Toast.makeText(MainActivity.this, "Connection failed!", Toast.LENGTH_LONG).show();
                        valueAnimator.end();
                        break;
                    default:
                        break;
                }
            }
        }
    };

    private void initListeners() {
        FetchData.registerHandler(mHandler);
    }

    private void setFloatingBtn() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int i = -1;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                if (i != animatedValue) {
                    fab.setImageDrawable(getResources().getDrawable(ids[animatedValue]));
                    i = animatedValue;
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valueAnimator.isRunning()) {
                    Log.d(TAG, "end");
                    valueAnimator.end();
                } else {
                    Log.d(TAG, "start");
                    valueAnimator.start();
                    FetchData.fetchSchedules();
                }
            }
        });
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG,"menu clicked " + item.getOrder());
                return false;
            }
        });
        setSupportActionBar(toolbar);

        setFloatingBtn();

        GridView gridView = (GridView) this.findViewById(R.id.main_contact_grid);
        mGridAdapter = new ContactGridAdapter();
        gridView.setAdapter(mGridAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        visibility = true;
        FetchData.fetchSchedules();
    }

    @Override
    protected void onPause() {
        super.onPause();
        visibility = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FetchData.unRegisterHandler(mHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    static class ViewHolder {
        ImageView avatarView;

        TextView nameView;
    }

    // adapter for GridView
    private class ContactGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return FetchData.getContactSize();
        }

        @Override
        public Object getItem(int i) {
            return FetchData.getContactAt(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
            //return Integer.valueOf(FetchData.getContacts().get(i).getContactNumber());
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder contactHolder = null;

            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                view = inflater.inflate(R.layout.contact_item, null);
                contactHolder = new ViewHolder();
                contactHolder.avatarView = (ImageView) view.findViewById(R.id.contact_item_avatar);
                contactHolder.nameView = (TextView) view.findViewById(R.id.contact_item_name);
                view.setTag(contactHolder);
            } else {
                contactHolder = (ViewHolder) view.getTag();
            }

            contactHolder.nameView.setText(FetchData.getContactAt(i).getName());
            // for demo, we only use default avatar
            // TODO contactHolder.avatarView.setImageBitmap();

            return view;
        }
    }
}
