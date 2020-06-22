package kolaric.parametri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {

    private List<Parametar> podaci;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Podatke proslijedimo kroz konstruktor
    public AdapterListe(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    // napuni predložak red (datoteka red_liste.xml)
    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        View view = mInflater.inflate(R.layout.red_liste, roditelj, false);
        return new Red(view);
    }

    // Veže podatke za svaki red
    @Override
    public void onBindViewHolder(Red red, int position) {
        Parametar o = podaci.get(position);
        red.ime.setText(o.getIme());
        red.grad.setText(o.getGrad());
    }

    // Ukupan broj redova (mora biti implementirano)
    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    // Pohranjuje i reciklira pogled kako se prolazi kroz listu
    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ime;
        private TextView grad;

        Red(View itemView) {
            super(itemView);
            ime = itemView.findViewById(R.id.ime);
            grad = itemView.findViewById(R.id.grad);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // klikom na listu dobijemo samo poziciju koju stavku liste smo odabrali.
    // Ova metoda pomaže da na osnovu pozicije dobijemo cijeli objekt u toj stavci
    public Parametar getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Parametar> itemList) {
        this.podaci = itemList;
    }

    // dopusti hvatanje odabira (klik/dotakni)
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // potrebno kako bi mogli hvatati klikove/dodire
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
