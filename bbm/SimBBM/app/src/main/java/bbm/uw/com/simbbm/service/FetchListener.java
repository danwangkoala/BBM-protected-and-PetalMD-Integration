package bbm.uw.com.simbbm.service;

/**
 * Created by jiy on 4/6/16.
 */
public interface FetchListener {
    void onFetchSuccess(String response);
    void onFetchFaile(int status);
}
