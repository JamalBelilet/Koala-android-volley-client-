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

public class PlatRecyclerAdaprer extends RecyclerView.Adapter<PlatRecyclerAdaprer.MViewHolder> {
    ArrayList<Plat> plats = new ArrayList<>();
    final private PlatItemListener platItemOnClickListener;

    public PlatRecyclerAdaprer(ArrayList<Plat> plats, PlatItemListener platItemOnClickListener) {
        this.plats = plats;
        this.platItemOnClickListener = platItemOnClickListener;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_plat, parent, false);
        MViewHolder mViewHolder = new MViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {

        holder.idPlat.setText(plats.get(position).getIdPlat());
//        holder.idCategorie.setText(plats.get(position).getIdCategorie());
        holder.prix.setText(plats.get(position).getPrix());
//        holder.disponible.setText(plats.get(position).getDisponible());
        holder.description.setText(plats.get(position).getDescription());
        holder.nom.setText(plats.get(position).getNom());
//        System.err.println(plats.get(position).getImage());
        holder.imageView.setImageBitmap(plats.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return plats.size();
    }

    public class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        psPreparation;
        //        TextView disponible;
        TextView description;
        TextView nom;
        ImageView imageView;

        TextView idPlat;
//        TextView idCategorie;
        TextView prix;
        TextView tem

        public MViewHolder(View itemView) {
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

        @Override
        public void onClick(View v) {
            platItemOnClickListener.onplatItemClick(Integer.parseInt(idPlat.getText().toString()));
        }
    }
    public interface PlatItemListener {ew) {
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

        void onplatItemClick(int clickedItemIndex);
    }
}
