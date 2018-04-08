package mob.jamaldev.dz.koala;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;

public class MainActivity extends Activity {

    Button btn_categories;
    Button btn_plats;
    CardView cardView;
    ImageView koala_image;
    public static String BASE_URL = "http://169.254.34.59/";

    public static HashMap<String, Integer> queue = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = ((CardView) findViewById(R.id.aPlat));
        koala_image = ((ImageView) findViewById(R.id.koala_image));


        koala_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayCategorieList.class));
//                startActivity(new Intent(MainActivity.this, DisplayCategorieList.class));
            }
        });

    }
}
