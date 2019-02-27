package arithomazini.senac.br.agendasenacararaquara.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import arithomazini.senac.br.agendasenacararaquara.R;
import arithomazini.senac.br.agendasenacararaquara.dao.ContatoDAO;
import arithomazini.senac.br.agendasenacararaquara.model.ContatoEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera a referencia da lista que tem no layout do aplicativo
        final ListView lista = findViewById(R.id.listaContatosListView);

        //Cria a lista de contatos como string
        ContatoDAO contatoDAO = new ContatoDAO(this);
        final List<ContatoEntity> contatos = contatoDAO.listar();


        //Para conseguirmos exibir a lista do listView, preciso
        //criar um adaptor
        ArrayAdapter<ContatoEntity> adapter =
                new ArrayAdapter<ContatoEntity>(this, android.R.layout.simple_list_item_1, contatos);

        //insere o adpter na lista de contatos
        lista.setAdapter(adapter);

        //Recuperar o botao e criar acao para ele
        Button novoContato = findViewById(R.id.novoContatoButton);

        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contato = new Intent(MainActivity.this, ContatoActivity.class);
                startActivity(contato);


            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              ContatoEntity contato = (ContatoEntity) lista.getItemAtPosition(position);

              Intent intentContatoActivy = new Intent(MainActivity.this, ContatoActivity.class);

              intentContatoActivy.putExtra("contato", contato);
              startActivity(intentContatoActivy);
            }
        });

        Button buttonBuscar = findViewById(R.id.ButtonBuscar);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperar buscar

                EditText BuscarEditText = findViewById(R.id.BuscarEditText);

                ContatoDAO contatoDAO = new ContatoDAO(MainActivity.this);
                List<ContatoEntity> contato = contatoDAO.Buscar(BuscarEditText.getText().toString());

                //Para conseguirmos exibir a lista do listView, preciso
                //criar um adaptor
                ArrayAdapter<ContatoEntity> adapter =
                        new ArrayAdapter<ContatoEntity>(MainActivity.this, android.R.layout.simple_list_item_1, contato);

                //insere o adpter na lista de contatos
                lista.setAdapter(adapter);


            }
        });
    }
}
