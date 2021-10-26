package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    // Camps de Text
    EditText tfCodi;
    EditText tfNom;
    EditText tfCognoms;
    EditText tfTelefon;
    EditText tfMail;

    // Botons
    RadioGroup rgGenere;
    RadioButton rbHome;
    RadioButton rbDona;
    ToggleButton tbActivar;
    Button btCancelar;
    Button btGuardar;
    Button btNou;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // carregarElements();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    public void carregarElements()
    {
        // Camps de text
        tfCodi = findViewById(R.id.tfCodi);
        tfNom = findViewById(R.id.tfNom);
        tfCognoms = findViewById(R.id.tfCognoms);
        tfTelefon = findViewById(R.id.tfTelefon);
        tfMail = findViewById(R.id.tfMail);

        // Botons
        rgGenere = findViewById(R.id.rgGenere);
        rbHome = findViewById(R.id.rbHome);
        rbDona = findViewById(R.id.rbDona);
        tbActivar = findViewById(R.id.tbActivar);
        btCancelar = findViewById(R.id.btCancelar);
//        btGuardar = findViewById(R.id.btGuardar);
  //      btNou = findViewById(R.id.btNou);
    }


    // Accions generals

    public void generarNovaId()
    {
        String novaId = String.valueOf(new Random().nextInt(9999)) + "ABC";
        tfCodi.setText(novaId);
    }

    // Accions dels botons

    public void nouRegistre(View view)
    {
        // Nova ID
        generarNovaId();

        // Esborrar camps
        tfNom.setText("");
        tfCognoms.setText("");
        tfTelefon.setText("");
        tfMail.setText("");
        rgGenere.clearCheck();
        tbActivar.setChecked(false);
    }

    public void activar(View view)
    {
        tbActivar.setBackgroundColor(tbActivar.isChecked() ? Color.RED : Color.GREEN);

    }


    public void guardar(View v)
    {

        Intent i = new Intent(this , SegonaActivity.class);
        i.putExtra("codi",tfCodi.getText().toString());

        startActivity(i);

    }

}























