package bbm.uw.com.simbbm.util;

import bbm.uw.com.simbbm.constant.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by jiy on 4/4/16.
 */
public class HttpUtil {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (params == null) {
            client.get(getAbsoluteUrl(url), responseHandler);
        } else {
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constants.API_BASE_URL + relativeUrl;
    }
}
