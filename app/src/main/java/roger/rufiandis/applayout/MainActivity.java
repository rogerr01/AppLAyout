package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    Button btEsborrar;
    Button btTargeta;
    Button btRegistrar;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarElements();
        generarNovaId();

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
        btEsborrar = findViewById(R.id.btEsborrar);
        btTargeta = findViewById(R.id.btTargeta);
        btRegistrar = findViewById(R.id.btRegistrar);
    }


    // Accions generals

    public void generarNovaId()
    {
        String novaId = String.valueOf(new Random().nextInt(9999)) + "ABC";
        tfCodi.setText(novaId);
    }

    // Accions dels botons

    public void esborrar(View view)
    {

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


    // Genera la targeta de client

    public void crearTargeta(View v)
    {

      try {
            Intent i = new Intent(this, Targeta.class);

            String codi = tfCodi.getText().toString();
            String nom = tfNom.getText().toString();
            String cognoms = tfCognoms.getText().toString();
            int telefon = Integer.parseInt(tfTelefon.getText().toString());
            String email = tfMail.getText().toString();
            String genere = rbHome.isChecked() ? "Home" : "Dona";

            i.putExtra("codi", codi);
            i.putExtra("nom", nom);
            i.putExtra("cognoms", cognoms);
            i.putExtra("telefon", telefon);
            i.putExtra("email", email);
            i.putExtra("genere", genere);

            startActivity(i);

        } catch (Exception e) {

          new MessageBox().show("Error","S'han d'emplenar tots els camps amb un format valid");

      }

    }

    public void email (View v)
    {
        try
        {
            Intent i = new Intent(this, EmailActivity.class);
            i.putExtra("email",tfMail.getText());
            startActivity(i);
        }
        catch (Exception e)
        {
            new MessageBox().show("Error",
                    "S'ha d'introduir un email");
        }
    }

    public void guardar(View v)
    {
        new MessageBox().show("Client guardat","S'ha guardat el registre del client " + tfCodi.getText());
        esborrar(v);
    }

    public class MessageBox
    {
        void show(String title, String message)
        {
            dialog = new AlertDialog.Builder(MainActivity.this) // Pass a reference to your main
                    // activity here
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            dialog.cancel();
                        }
                    })
                    .show();
        }

        private AlertDialog dialog;
    }



}























