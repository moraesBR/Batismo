package senac.batismo.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import senac.batismo.R;

public class NomeViewHolder extends RecyclerView.ViewHolder {
    final TextView tvNome;
    final TextView tvSobrenome;
    final ImageView foto;

    public NomeViewHolder(@NonNull View itemView){
        super(itemView);
        tvNome = itemView.findViewById(R.id.tvNome);
        tvSobrenome = itemView.findViewById(R.id.tvSobrenome);
        foto = itemView.findViewById(R.id.foto);
    }
}
