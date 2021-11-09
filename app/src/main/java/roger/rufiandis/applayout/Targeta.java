package roger.rufiandis.applayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Targeta extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targeta);

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