package bbm.uw.com.simbbm.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jiy on 4/6/16.
 */
public class FetchService extends Service{

    public static final String TAG = "FetchService";

    private FetchListener mListener = null;

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        startForeground(1, new Notification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "startDownload() executed");
        }

        public void addListener(FetchListener listener){
            Log.d(TAG, "addListener() executed");
        }
    }
}
