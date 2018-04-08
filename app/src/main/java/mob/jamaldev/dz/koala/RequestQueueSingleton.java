package mob.jamaldev.dz.koala;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by bjama on 5/3/2017.
 */
public class RequestQueueSingleton {

    private Cache cache;
    private Network network;
    private RequestQueue requestQueue;

    private static Context context;

    private static RequestQueueSingleton ourInstance;

    public static synchronized RequestQueueSingleton getInstance(Context _context) {
        if (ourInstance == null) {
            ourInstance = new RequestQueueSingleton(_context);
        }
        return ourInstance;
    }

    private RequestQueueSingleton(Context _context) {
        context = _context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {

            network = new BasicNetwork(new HurlStack());
            cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024 * 40);
            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

        }

        return requestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request) {
        requestQueue.add(request);
    }

}
