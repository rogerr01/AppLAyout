package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Targeta extends AppCompatActivity
{

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void inici(MenuItem m)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    public void sortir(MenuItem v)
    {
        System.exit(0);
    }


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targeta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        TextView tvTargeta = findViewById(R.id.tvTargeta);

        String codi = b.getString("codi");
        String nom = b.getString("nom");
        String cognoms = b.getString("cognoms");
        int telefon = b.getInt("telefon");
        String email = b.getString("email");
        String genere = b.getString("genere");

        String contingut = "Targeta del client " + codi + "\n\n"+
                "Nom: " + nom + "\n\n" +
                "Cognoms: " + cognoms + "\n\n" +
                "Telefon: " + telefon + "\n\n" +
                "Correu: " + email + "\n\n" +
                "Genere: " + genere;

        tvTargeta.setText(contingut);

    }



}