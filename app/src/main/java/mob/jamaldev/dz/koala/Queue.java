package mob.jamaldev.dz.koala;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Liew) {
        super(itemView);
        idPlat = ((TextView) itemView.findViewById(R.id.idPlat));
//            idCategorie = ((TextView) itemView.findViewById(R.id.idCategorie));
        prix = ((TextView) itemView.findViewById(R.id.prix));
//            tempsPreparation = ((TextView) itemView.findViewById(R.id.tempsPreparation));
//            disponible = ((TextView) itemView.findViewById(R.id.disponible));
        description = ((TextView) itemView.findViewById(R.id.description));
        nom = ((TextView) itemView.findViewById(R.id.nom));
        imageView = ((ImageView) itemView.findViewById(R.id.image));

        itemView.setOnClickListener(this);
        }
        rror;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static mob.jamaldev.dz.koala.MainActivity.BASE_URL;
import static mob.jamaldev.dz.koala.MainActivity.queue;

public class Queue extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        final TextView text = (TextView) findViewById(R.id.text);
        Button commander = (Button) findViewById(R.id.commander);

        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Queue.this, "le commande a ete envoyer !", Toast.LENGTH_SHORT).show();



                String url = BASE_URL + "commander.php";

                final StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("content", text.getText().toString());

                        return params;
                    }

//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError {
//                        return super.getHeaders();
//                    }
                };

                stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueueSingleton.getInstance(getBaseContext()).addToRequestQueue(stringRequest);


                MainActivity.queue.clear();

//                text.setText(null);.
                Queue.this.finish();


            }
        });

        if (text.getText().toString().contains("the que is empty !")) {
            text.setText(null);
        } else if (queue.size() == 0) {
            text.setText("the que is empty !");
        }

        for (String key :
                queue.keySet()) {
            text.setText(text.getText() + "\n" + key + ":" + queue.get(key).toString()+ "\n" );
        }
    }
}
