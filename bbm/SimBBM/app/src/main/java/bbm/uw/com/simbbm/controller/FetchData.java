package bbm.uw.com.simbbm.controller;

import android.os.Handler;
import android.util.Log;
import bbm.uw.com.simbbm.constant.Constants;
import bbm.uw.com.simbbm.item.ContactItem;
import bbm.uw.com.simbbm.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jiy on 4/4/16.
 */
public class FetchData {
    private final static String TAG = "FetchData";

    // TODO use Service and aidl instead
    private static Timer timer;
    private static boolean isRunning = false;

    private static ArrayList<ContactItem> contactItems = new ArrayList<>();

    private static ArrayList<ContactItem> availableItems = new ArrayList<>();

    private static ArrayList<Handler> viewHandlers = new ArrayList<>();

    public static void registerHandler(Handler handler) {
        if (!viewHandlers.contains(handler)) {
            viewHandlers.add(handler);
        }
        timer = new Timer();
    }

    public static void unRegisterHandler(Handler handler) {
        viewHandlers.remove(handler);
        timer.cancel();
        timer = null;
        isRunning = false;
    }

    private static void notifyViews(int message) {
        for (Handler handler : viewHandlers) {
            handler.sendEmptyMessage(message);
        }
    }

    private static AsyncHttpResponseHandler scheduleHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            Log.d(TAG, "get schedule success");
            if ((HttpStatus.SC_OK == statusCode) && (null != responseBody) && (responseBody.length > 0)) {
                // by default, the "responst" object is set to SUCCESS
                String resBody = new String(responseBody);
                Log.d(TAG, "get schedule response: " + resBody);

                // TODO real code
                cacheContacts(resBody);

                // TODO test only
//                contactItems.clear();
//                for (int index = 0; index < 20; index++) {
//                    ContactItem item = new ContactItem("John" + index, "Terry" + index, "jh@grh.ca", "group", 1111, 1111, "227-222-1986");
//                    contactItems.add(item);
//                }
            }

            notifyViews(Constants.MESSAGE_GET_SCHEDULE_SUCCESS);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.d(TAG, "get schedule failed, error is " + error.getMessage());
            notifyViews(Constants.MESSAGE_GET_SCHEDULE_FAIL);
        }
    };

    private static void cacheContacts(String contactsStr) {
        contactItems = new Gson().fromJson(contactsStr, new TypeToken<ArrayList<ContactItem>>() {
        }.getType());
        Log.d(TAG, "contacts : " + contactItems);

        sortAvailable();
    }

    private static void sortAvailable() {
        availableItems.clear();
        long currentTime = System.currentTimeMillis();
        for (ContactItem item : contactItems) {
            if (item.getStart_timestamp() < currentTime && item.getEnd_timestamp() > currentTime) {
                availableItems.add(item);
            }
        }

        Log.d(TAG,"sortAvailable: " + availableItems);
    }

    public static void fetchSchedulesDirectly() {
        HttpUtil.get(Constants.API_GET_URL, null, scheduleHandler);
    }

    public static void fetchSchedules() {
        if (isRunning) {
            return;
        }

        TimerTask locateTask = new TimerTask() {
            @Override
            public void run() {
                HttpUtil.get(Constants.API_GET_URL, null, scheduleHandler);
            }
        };
        timer.schedule(locateTask, 10, 30000);
        isRunning = true;
    }

    public static ArrayList<ContactItem> getContacts() {
        return availableItems;
    }

    public static ContactItem getContactAt(int index) {
        return availableItems.get(index);
    }

    public static int getContactSize() {
        return availableItems.size();
    }
}
