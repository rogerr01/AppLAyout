package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

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
    }


    public void carregarElements ()
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
        btTargeta = findViewById(R.id.btEditar);
        btRegistrar = findViewById(R.id.btRegistrar);
    }

    // Accions dels botons
    public void esborrar (View view)
    {
        // Esborrar camps
        tfNom.setText("");
        tfCognoms.setText("");
        tfTelefon.setText("");
        tfMail.setText("");
        rgGenere.clearCheck();
        tbActivar.setChecked(false);
    }

    public void esborrarRegistre(View v)
    {
        String id = tfCodi.getText().toString();
        esborrar(v);

        SQLiteDatabase db = new ControladorBD(this).getWritableDatabase();

        db.execSQL("DELETE FROM CLIENT WHERE CODI = " + id);
        new MessageBox().show("S'ha esborrat el registre del contacte amb ID " + tfCodi.getText());
    }

    public void activar (View view)
    {
        tbActivar.setBackgroundColor(tbActivar.isChecked() ? Color.RED : Color.GREEN);
    }

    public void editar (View v)
    {
        String codi = tfCodi.getText().toString();
        String nom = quotes(tfNom.getText().toString());
        String cognoms = quotes(tfCognoms.getText().toString());
        int telefon = Integer.parseInt(tfTelefon.getText().toString());
        String email = quotes(tfMail.getText().toString());
        String genere = quotes(rbHome.isChecked() ? "Home" : "Dona");
        int actiu = tbActivar.isChecked() ? 1 : 0;

        SQLiteDatabase db = new ControladorBD(this).getWritableDatabase();

        db.execSQL("UPDATE CLIENT " +
                "SET NOM = " + nom  + ", " +
                "COGNOMS = " + cognoms + ", " +
                "TELEFON = " + telefon + ", " +
                "EMAIL = " + email + ", " +
                "GENERE = " + genere + ", " +
                "ACTIU = " + actiu +
                " WHERE CODI = " + codi
        );

        new MessageBox().show("S'ha actualitzat el registre del contacte amb ID " + tfCodi.getText());
        esborrar(v);
    }

    public void buscar (View v)
    {
        String id = tfCodi.getText().toString();

        esborrar(v);

        SQLiteDatabase db = new ControladorBD(this).getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM CLIENT WHERE CODI = " + id
                , null);

        if (c.moveToFirst()){
            do {

                tfCodi.setText(c.getString(0));
                tfNom.setText(c.getString(1));
                tfCognoms.setText(c.getString(2));
                tfTelefon.setText(c.getString(3));
                tfMail.setText(c.getString(4));
                rbHome.setChecked(c.getString(5).equals("Home"));
                rbDona.setChecked(c.getString(5).equals("Dona"));
                tbActivar.setChecked(c.getInt(6) == 1);

            } while(c.moveToNext());
        }
        c.close();
        db.close();
    }

    public void guardar (View v)
    {
        String codi = tfCodi.getText().toString();
        String nom = quotes(tfNom.getText().toString());
        String cognoms = quotes(tfCognoms.getText().toString());
        int telefon = Integer.parseInt(tfTelefon.getText().toString());
        String email = quotes(tfMail.getText().toString());
        String genere = quotes(rbHome.isChecked() ? "Home" : "Dona");
        int actiu = tbActivar.isChecked() ? 1 : 0;

        SQLiteDatabase db = new ControladorBD(this).getWritableDatabase();

       try
       {
            db.execSQL("INSERT INTO CLIENT(CODI, NOM, COGNOMS, TELEFON, EMAIL, GENERE, ACTIU) VALUES " +
                    "(" + codi + ", " + nom + ", " + cognoms + ", " + telefon + ", " + email + ", " + genere + ", " + actiu + ")");

            new MessageBox().show("S'ha guardat el registre del contacte amb ID " + tfCodi.getText());
           esborrar(v);
       }

       catch (Exception e)
       {
           new MessageBox().show("Ja existeix un contacte amb ID " + tfCodi.getText() + ", per " +
                   "editrar-lo usa el botó d'editar");
       }

    }

    public static String quotes(String input)
    {
        return "'" + input + "'";
    }

    public class MessageBox
    {
        void show (String message)
        {
            dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Atenció")
                    .setMessage(message)
                    .setPositiveButton("OK", (dialogInterface, i) -> dialog.cancel())
                    .show();
        }

        private AlertDialog dialog;
    }


}























