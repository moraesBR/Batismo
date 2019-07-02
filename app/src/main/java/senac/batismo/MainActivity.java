package senac.batismo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.List;

import senac.batismo.adapters.AdapterNome;
import senac.batismo.models.GeraNomes;
import senac.batismo.models.Nome;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Nome>> {
    protected static GeraNomes nomes;
    protected EditText etQtd;
    protected Spinner spTipo;
    protected RecyclerView listaNomes;
    protected ProgressBar loading;
    protected LoaderManager loaderManager;

    private static final int OPERATION_GERANOMES_LOADER = 15;

    /* Cria chave para ser usada no onSavedInstanceState */
    private static final String TIPO_TXT_KEY = "nomes";
    private static final String QTD_TXT_KEY = "quantidade";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etQtd = findViewById(R.id.etQtd);
        spTipo = findViewById(R.id.spTipo);
        listaNomes = findViewById(R.id.rvListaNomes);
        loading = findViewById(R.id.progressBar);
        loading.setVisibility(View.GONE);


        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(TIPO_TXT_KEY))
                spTipo.setSelection(savedInstanceState.getInt(TIPO_TXT_KEY));

            if(savedInstanceState.containsKey(QTD_TXT_KEY))
                etQtd.setSelection(savedInstanceState.getInt(QTD_TXT_KEY));
        }


        listaNomes.addItemDecoration(new DividerItemDecoration(getBaseContext(),DividerItemDecoration.VERTICAL));
        listaNomes.setLayoutManager(new LinearLayoutManager(getBaseContext(),RecyclerView.VERTICAL, false));

        loaderManager = getSupportLoaderManager();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    nomes = new GeraNomes();
                    nomes.setTipo(spTipo.getSelectedItem().toString());
                    nomes.setQuantidade(Integer.parseInt(etQtd.getText().toString()));

                    Loader<List<GeraNomes>> loader = loaderManager.getLoader(OPERATION_GERANOMES_LOADER);

                    if (loader == null) {
                        loaderManager.initLoader(OPERATION_GERANOMES_LOADER, null, MainActivity.this);
                    } else {
                        loaderManager.restartLoader(OPERATION_GERANOMES_LOADER, null, MainActivity.this);
                    }

                }
                catch(Exception ex) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    /* Salva as informações dos campos lidos */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(TIPO_TXT_KEY,spTipo.getSelectedItemPosition());
        outState.putInt(QTD_TXT_KEY,Integer.parseInt(etQtd.getText().toString()));
    }

    /*
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
    */

    public Loader<List<Nome>> onCreateLoader(int id, @Nullable Bundle args){
        return new AsyncTaskLoader<List<Nome>>(this) {
            @Nullable
            @Override
            public List<Nome> loadInBackground() {
                nomes.setNomes();
                return nomes.getNomes();
            }
            @Override
            protected void onStartLoading(){
                listaNomes.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@Nullable Loader<List<Nome>> loader, List<Nome> data){
        loading.setVisibility(View.GONE);
        listaNomes.setVisibility(View.VISIBLE);
        listaNomes.setAdapter(new AdapterNome(data,getBaseContext()));
    }

    @Override
    public void onLoaderReset(@Nullable Loader<List<Nome>> loader){

    }
}
