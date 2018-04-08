package mob.jamaldev.dz.koala;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static mob.jamaldev.dz.koala.MainActivity.BASE_URL;

/**
 * Created by bjama on 5/3/2017.
 */

public class CategorieBackgroundTask {
    Context context;
    ArrayList<Categorie> categories = new ArrayList<>();
    String url = BASE_URL + "_categories.php";

    public CategorieBackgroundTask(Context context) {
        this.context = context;
    }

    public void getCategories(final ArrayCallBack onCallBack) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while ( count < response.length() ) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Categorie categorie = new Categorie(
                                        jsonObject.getString("idCategorie"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("nom"),
                                        urlToBitMap(jsonObject.getString("idCategorie"))
                                );
                                categories.add(categorie);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        onCallBack.onSuccess(categories);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, "error from categorie back task !", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                        onCallBack.onFail(error.toString());


                    }
                }
        );
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueSingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

    }

    public interface ArrayCallBack {
        void onSuccess(ArrayList<Categorie> categories);

        void onFail(String msg);
    }
    public Bitmap urlToBitMap(String idCategorie){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Log.d("hello", "urlToBitMap: id idCategorie = " + idCategorie);
            URL url = new URL(BASE_URL + "_get_categorie_image.php?ID=" + idCategorie);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }
}
