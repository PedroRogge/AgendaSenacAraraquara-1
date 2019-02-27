package arithomazini.senac.br.agendasenacararaquara.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import arithomazini.senac.br.agendasenacararaquara.R;
import arithomazini.senac.br.agendasenacararaquara.dao.ContatoDAO;
import arithomazini.senac.br.agendasenacararaquara.dao.EnderecoDAO;
import arithomazini.senac.br.agendasenacararaquara.model.ContatoEntity;
import arithomazini.senac.br.agendasenacararaquara.model.EnderecoEntity;


public class ContatoActivity extends AppCompatActivity {

ContatoEntity contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Intent intent = getIntent();

        contato  = (ContatoEntity) intent.getSerializableExtra("contato");

        if(contato != null){
            EditText nomeEditText = findViewById(R.id.nomeEditText);
            EditText telefoneEditText = findViewById(R.id.telefoneEditText);
            RatingBar pontuacaoRatingBar = findViewById(R.id.pontuacaoRatingBar);


            nomeEditText.setText(contato.getNome());
            telefoneEditText.setText(contato.getTelefone());
            pontuacaoRatingBar.setProgress(contato.getPontuacao().intValue());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contato_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.salvar_contato_menu_item:
                //Recuperar o texto dos EditText
                //Objetos da tela
                //Edit Text - Contato
                EditText nomeEditText = findViewById(R.id.nomeEditText);
                EditText telefoneEditText = findViewById(R.id.telefoneEditText);

                //Edit Text - Endereco
                EditText ruaEditText = findViewById(R.id.ruaEditText);
                EditText numeroEditText = findViewById(R.id.numeroEditText);
                EditText cidadeEstadoEditText = findViewById(R.id.cidadeEstadoEditText);

                //Edit Text - Buscar


                //Rating Bar
                RatingBar pontuacaoRatingBar = findViewById(R.id.pontuacaoRatingBar);

                //Texto que estavam presentes nos objetos
                if (contato != null) {
                    contato.setNome(nomeEditText.getText().toString());
                    contato.setTelefone(telefoneEditText.getText().toString());
                    contato.setPontuacao(Double.valueOf(pontuacaoRatingBar.getProgress()));
                }else {
                 contato = new ContatoEntity(nomeEditText.getText().toString(),
                 telefoneEditText.getText().toString(),Double.valueOf(pontuacaoRatingBar.getProgress()));

                }

                    ContatoEntity contato = new ContatoEntity(nomeEditText.getText().toString(),
                            telefoneEditText.getText().toString(),
                            Double.valueOf(pontuacaoRatingBar.getProgress()));

                    ContatoDAO contatoDAO = new ContatoDAO(ContatoActivity.this);
                    contatoDAO.salvar(contato);

                    EnderecoEntity endereco = new EnderecoEntity(ruaEditText.getText().toString(),
                            numeroEditText.getText().toString(),
                            cidadeEstadoEditText.getText().toString());

                    EnderecoDAO enderecoDAO = new EnderecoDAO(ContatoActivity.this);
                    enderecoDAO.salvar(endereco);



                //Exibe uma mensagem
                Toast.makeText(ContatoActivity.this,
                        "Contato Salvo!",
                        Toast.LENGTH_LONG).show();

                //Finaliza a activity atual e volta para a MainActivity
                Intent main = new Intent(ContatoActivity.this, MainActivity.class);
                startActivity(main);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

