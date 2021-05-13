package com.example.appcarros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etModelo;
    private Spinner spMontadora;
    private Spinner spAno;
    private Button btnSalvar;
    private String acao;
    private Carro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        etModelo = findViewById(R.id.etModelo);
        spMontadora = findViewById(R.id.spMontadora);
        spAno = findViewById(R.id.spAno);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");

        if(acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarFormulario(){
        int idCarro = getIntent().getIntExtra("idCarro", 0);
        if(idCarro != 0){
            carro = CarroDAO.getCarroById(this, idCarro);
            etModelo.setText(carro.getModelo());

            String[] arrayMontadoras = getResources().getStringArray(R.array.arrayMontadoras);
            for(int i = 1; i < arrayMontadoras.length; i++){
                if(arrayMontadoras[i].equals(carro.getMontadora())){
                    spMontadora.setSelection(i);
                }
            }

            String[] arrayAno = getResources().getStringArray(R.array.arrayAno);
            for(int i = 1; i < arrayAno.length; i++){
                if(Integer.valueOf(arrayAno[i]) == carro.getAno()){
                    spAno.setSelection(i);
                }
            }
        }
    }

    private void salvar() {

            if (etModelo.getText().toString().trim().isEmpty() ||
                spMontadora.getSelectedItemPosition() == 0 ||
                spAno.getSelectedItemPosition() == 0) {

                Toast.makeText(this, R.string.txtToast, Toast.LENGTH_SHORT).show();

            } else {

                if (acao.equals("novo")) {
                    carro = new Carro();
                }

                carro.setModelo(etModelo.getText().toString());
                carro.setMontadora(spMontadora.getSelectedItem().toString());
                carro.setAno(Integer.valueOf(spAno.getSelectedItem().toString()));

                if (acao.equals("editar")) {
                    CarroDAO.editar(carro, this);
                    finish();

                } else {
                    if (acao.equals("novo")) {
                        CarroDAO.inserir(carro, this);
                        etModelo.setText("");
                        spMontadora.setSelection(0);
                        spAno.setSelection(0);
                    }
                }
            }
    }
}