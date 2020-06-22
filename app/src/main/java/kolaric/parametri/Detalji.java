package kolaric.parametri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Detalji extends AppCompatActivity {

    private Parametar paramtar;
    private TextView sifra;
    private TextView ime;
    private TextView grad;
    private Button nazad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);
        sifra = findViewById(R.id.sifra);
        ime = findViewById(R.id.ime);
        grad = findViewById(R.id.grad);
        nazad = findViewById(R.id.nazad);

        Intent i = getIntent();
        Parametar parametar = (Parametar) i.getSerializableExtra("parametar");
        sifra.setText(String.valueOf(parametar.getSifra()));
        ime.setText(String.valueOf(parametar.getIme()));
        grad.setText(String.valueOf(parametar.getGrad()));
        nazad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                nazad();
            }
        });

    }

    void nazad(){
        finish();
    }
}
