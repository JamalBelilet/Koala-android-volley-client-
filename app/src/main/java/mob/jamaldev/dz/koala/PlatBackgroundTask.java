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

public class PlatBackgroundTask {
    Context context;
    ArrayList<Plat> plats = new ArrayList<>();
    String url = BASE_URL +"/_plats.php";
    public static String SELECTED_CATEGORIE_BASE_URL = BASE_URL + "_platswithingcategorie.php?ID=";
    public static int SELECTED_CATEGORIE_ID = -1;

    public PlatBackgroundTask(Context context) {
        this.context = context;
    }

    public void getPlats(final ArrayCallBack onCallBack) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while ( count < response.length() ) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Plat plat = new Plat(
                                        jsonObject.getString("idPlat"),
                                        "id categorie : " + jsonObject.getString("idCategorie"),
                                        "prix : " + jsonObject.getString("prix") + " DA",
                                        "temps preparation : " + jsonObject.getString("tempsPreparation"),
                                        "disponible : " + jsonObject.getString("disponible"),
                                        "description : " + jsonObject.getString("description"),
                                        "plat : " + jsonObject.getString("nom"),
                                        urlToBitMap(jsonObject.getString("idPlat"))
                                );
                                plats.add(plat);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        onCallBack.onSuccess(plats);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, "error from plat back task !", Toast.LENGTH_SHORT).show();
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

    public void getPlatsFromCategorie(final ArrayCallBack onCallBack) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST, SELECTED_CATEGORIE_BASE_URL + SELECTED_CATEGORIE_ID,  null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while ( count < response.length() ) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Plat plat = new Plat(
                                        jsonObject.getString("idPlat"),
                                        "id categorie : " + jsonObject.getString("idCategorie"),
                                        "prix : " + jsonObject.getString("prix") + " DA",
                                        "temps preparation : " + jsonObject.getString("tempsPreparation"),
                                        "disponible : " + jsonObject.getString("disponible"),
                                        "description : " + jsonObject.getString("description"),
                                        "plat : " + jsonObject.getString("nom"),
                                        urlToBitMap(jsonObject.getString("idPlat"))
                                );
                                plats.add(plat);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        onCallBack.onSuccess(plats);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(context, "error from plat back task !", Toast.LENGTH_SHORT).show();
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
        void onSuccess(ArrayList<Plat> plats);

        void onFail(String msg);
    }


    public Bitmap urlToBitMap(String idPlat){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Log.d("hello", "urlToBitMap: id plat = " + idPlat);
            URL url = new URL(BASE_URL+"_get_plat_image.php?ID=" + idPlat);
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



