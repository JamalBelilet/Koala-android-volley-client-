package mob.jamaldev.dz.koala;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static mob.jamaldev.dz.koala.MainActivity.BASE_URL;

public class plat_detail extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_detail);

        final ImageView plat_image = (ImageView) findViewById(R.id.plat_image);
        final TextView plat_nom = (TextView) findViewById(R.id.plat_nom);
        final TextView plat_prix = (TextView) findViewById(R.id.plat_prix);
        final TextView plat_description = (TextView) findViewById(R.id.plat_description);
        final Button add_to_queue = (Button) findViewById(R.id.add_to_queue);


        String url = BASE_URL + "_plat.php?ID=" + getIntent().getExtras().get("plat_id");
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while ( count < response.length() ) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);

                                plat_description.setText(jsonObject.getString("description"));
                                plat_prix.setText(jsonObject.getString("prix"));
                                plat_nom.setText(jsonObject.getString("nom"));
                                plat_image.setImageBitmap(urlToBitMap(getIntent().getExtras().get("plat_id").toString()));
                                add_to_queue.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (MainActivity.queue.containsKey(plat_nom.getText())){

                                            MainActivity.queue.put(plat_nom.getText().toString(),
                                                    MainActivity.queue.get(plat_nom.getText()) + 1);

                                        } else  {

                                            MainActivity.queue.put(plat_nom.getText().toString(), 1);

                                        }
                                        finishActivity(0);
                                    }
                                });


                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueSingleton.getInstance(getBaseContext()).addToRequestQueue(jsonArrayRequest);



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
