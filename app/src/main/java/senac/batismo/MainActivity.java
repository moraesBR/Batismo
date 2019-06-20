package senac.batismo;

import android.os.Bundle;

import com.github.javafaker.Faker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import senac.batismo.adapters.AdapterNome;
import senac.batismo.models.GeraNomes;

public class MainActivity extends AppCompatActivity {
    protected static GeraNomes nomes;
    EditText etQtd;
    Spinner spTipo;
    RecyclerView listaNomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etQtd = findViewById(R.id.etQtd);
        spTipo = findViewById(R.id.spTipo);
        listaNomes = findViewById(R.id.rvListaNomes);
        nomes = new GeraNomes();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    nomes.setTipo(spTipo.getSelectedItem().toString());
                    nomes.setQuantidade(Integer.parseInt(etQtd.getText().toString()));
                    nomes.setNomes();

                    listaNomes.setAdapter(new AdapterNome(nomes.getNomes(),getBaseContext()));
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getBaseContext(),RecyclerView.VERTICAL, false);
                    listaNomes.addItemDecoration(new DividerItemDecoration(getBaseContext(),DividerItemDecoration.VERTICAL));

                }
                catch(Exception ex) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
