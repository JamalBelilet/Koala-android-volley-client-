package mob.jamaldev.dz.koala;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import static mob.jamaldev.dz.koala.PlatBackgroundTask.SELECTED_CATEGORIE_ID;


public class DisplayCategorieList extends Activity implements CategorieRecyclerAdaprer.CategorieItemListener{

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Categorie> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_categorie_list);

        recyclerView = ((RecyclerView) findViewById(R.id.categorieRecyclerView));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        CategorieBackgroundTask backgroundTask = new CategorieBackgroundTask(DisplayCategorieList.this);
        backgroundTask.getCategories(new CategorieBackgroundTask.ArrayCallBack() {
            @Override
            public void onSuccess(ArrayList<Categorie> categories) {
                adapter = new CategorieRecyclerAdaprer(categories, DisplayCategorieList.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
            }
        });



    }

    @Override
    public void oncategorieItemClick(int clickedItemIndex) {
        Intent platsUI = new Intent(this, DisplayPlatList.class);
        platsUI.putExtra("categorie_id", clickedItemIndex);


//        urlwithcategorie = urlwithcategorie.replaceAll("\\d","").replaceAll("^\\s+|\\s+$", "")+clickedItemIndex;
        SELECTED_CATEGORIE_ID =  clickedItemIndex;

        startActivity(platsUI);
    }
}
