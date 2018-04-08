package mob.jamaldev.dz.koala;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayPlatList extends Activity implements PlatRecyclerAdaprer.PlatItemListener {

    FloatingActionButton queue_FloatingActionButton;

    EditText searchField;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Plat> plats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_plat_list);

        queue_FloatingActionButton = ((FloatingActionButton) findViewById(R.id.queue_FloatingActionButton));
        queue_FloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plat_detail = new Intent(DisplayPlatList.this, Queue.class);
                startActivity(plat_detail);
            }
        });

        searchField = ((EditText) findViewById(R.id.searchField));

        recyclerView = ((RecyclerView) findViewById(R.id.platRecyclerView));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        PlatBackgroundTask backgroundTask = new PlatBackgroundTask(DisplayPlatList.this);

        backgroundTask.getPlatsFromCategorie(new PlatBackgroundTask.ArrayCallBack() {
            @Override
            public void onSuccess(final ArrayList<Plat> plats) {
                System.out.println(plats.size());
                adapter = new PlatRecyclerAdaprer(plats, DisplayPlatList.this);
                recyclerView.setAdapter(adapter);

                searchField.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        ArrayList<Plat> filtredPlats = new ArrayList<>();
                        for (Plat plat :
                                plats) {
                            if (plat.getNom().contains(s)) {
                                filtredPlats.add(plat);
                            }
                            System.out.println("=========================");
                        }
                        adapter = new PlatRecyclerAdaprer(filtredPlats, DisplayPlatList.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
            }
        });

    }

    @Override
    public void onplatItemClick(int clickedItemIndex) {

        Intent plat_detail = new Intent(this, plat_detail.class);
        plat_detail.putExtra("plat_id", clickedItemIndex);
        startActivity(plat_detail);

    }
}
