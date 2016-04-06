package com.uw.bbm.controller;

import android.os.Handler;
import android.util.Log;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uw.bbm.constant.Constants;
import com.uw.bbm.item.ContactItem;
import com.uw.bbm.util.HttpUtil;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpStatus;

import java.util.ArrayList;

/**
 * Created by jiy on 4/4/16.
 */
public class FetchData {
    private final static String TAG = "FetchData";

    private static ArrayList<ContactItem> contactItems = new ArrayList<>();

    private static ArrayList<Handler> viewHandlers = new ArrayList<>();

    public static void registerHandler(Handler handler) {
        if (!viewHandlers.contains(handler)) {
            viewHandlers.add(handler);
        }
    }

    public static void unRegisterHandler(Handler handler) {
        viewHandlers.remove(handler);
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

                // TODO test only
                contactItems.clear();
                for (int index = 0; index < 20; index++) {
                    ContactItem item = new ContactItem("John" + index, "Terry" + index, "jh@grh.ca", "group", "10:00", "227-222-1986");
                    contactItems.add(item);
                }
            }

            notifyViews(Constants.MESSAGE_GET_SCHEDULE_SUCCESS);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.d(TAG, "get schedule failed, error is " + error.getMessage());
            notifyViews(Constants.MESSAGE_GET_SCHEDULE_FAIL);
        }
    };

    public static void fetchSchedules() {
        HttpUtil.get(Constants.API_GET_URL, null, scheduleHandler);
    }

    public static ArrayList<ContactItem> getContacts() {
        return contactItems;
    }

    public static ContactItem getContactAt(int index) {
        return contactItems.get(index);
    }

    public static int getContactSize() {
        return contactItems.size();
    }
}
