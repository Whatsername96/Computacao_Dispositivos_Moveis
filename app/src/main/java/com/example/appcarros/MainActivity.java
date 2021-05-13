package com.example.appcarros;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvCarros;
    private AdapterCarros adapter;
    private List<Carro> listaCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });

        lvCarros = (ListView) findViewById(R.id.lvCarros);

        carregarCarros();

        configurarListView();

    }

    private void configurarListView(){
        lvCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carroSelecionado = listaCarros.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idCarro", carroSelecionado.getId());
                startActivity(intent);
            }
        });

        lvCarros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carroSelecionado = listaCarros.get(position);
                excluirCarro(carroSelecionado);
                return true;
            }
        });
    }

    private void excluirCarro(Carro carro){

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage(getString(R.string.txtConfirmacao) + " " + carro.getModelo() + "?");

        alerta.setNeutralButton(R.string.txtCancelar, null);
        alerta.setPositiveButton(R.string.txtConfirmacao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CarroDAO.excluir(carro.getId(), MainActivity.this);
                carregarCarros();
            }
        });

        alerta.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarCarros();
    }

    private void carregarCarros(){
        listaCarros = CarroDAO.getCarros(this);
        adapter = new AdapterCarros(this, listaCarros);
        lvCarros.setAdapter(adapter);
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