package senac.batismo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import senac.batismo.R;
import senac.batismo.models.Nome;

public class AdapterNome extends RecyclerView.Adapter {

    private List<Nome> nomes;
    private Context context;

    public AdapterNome(List<Nome> nomes, Context context) {
        this.nomes = nomes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nome, parent, false);

        NomeViewHolder holder = new NomeViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NomeViewHolder viewHolder = (NomeViewHolder) holder;

        Nome nome = nomes.get(position);

        viewHolder.tvNome.setText(nome.getPrimeiro());
        viewHolder.tvSobrenome.setText(nome.getUltimo());

    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }
}
