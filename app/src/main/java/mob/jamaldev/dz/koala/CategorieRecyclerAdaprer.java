package mob.jamaldev.dz.koala;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bjama on 5/3/2017.
 */

public class CategorieRecyclerAdaprer extends RecyclerView.Adapter<CategorieRecyclerAdaprer.MViewHolder> {
    ArrayList<Categorie> categories = new ArrayList<>();
    final private CategorieItemListener categorieItemOnClickListener;


    public CategorieRecyclerAdaprer(ArrayList<Categorie> categories, CategorieItemListener categorieItemOnClickListener) {
        this.categories = categories;
        this.categorieItemOnClickListener = categorieItemOnClickListener;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_categorie, parent, false);
        MViewHolder mViewHolder = new MViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {


        holder.idCategorie.setText(categories.get(position).getIdCategorie());
        holder.nom.setText(categories.get(position).getNom());
        holder.description.setText(categories.get(position).getDescription());
        System.out.println(categories.get(position).getImage() + "=====================");
        System.out.println(holder.imageView + "===========holder.imageView==========");
        holder.imageView.setImageBitmap(categories.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView idCategorie;
        TextView description;
        TextView nom;
        ImageView imageView;

        public MViewHolder(View itemView) {
            super(itemView);

            idCategorie = ((TextView) itemView.findViewById(R.id.idCategorie));
            description = ((TextView) itemView.findViewById(R.id.description));
            nom = ((TextView) itemView.findViewById(R.id.nom));
            imageView = ((ImageView) itemView.findViewById(R.id.image));

            itemView.setOnClickListener(this);



        }
        @Override
        public void onClick(View v) {
            categorieItemOnClickListener.oncategorieItemClick(Integer.parseInt(idCategorie.getText().toString()));
        }
    }
    public interface CategorieItemListener {
        void oncategorieItemClick(int clickedItemIndex);
    }
}
