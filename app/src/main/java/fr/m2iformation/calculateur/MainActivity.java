package fr.m2iformation.calculateur;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvEcran;
    Double tauxEuro = 1.00;
    Double tauxDollars = 1.185;
    Double tauxLivres = 0.88;
    Double tauxBitcoins = 0.0001;
    Double tauxEntree = 1.00;
    Double angleDepart = 180.00;
    Double anglDegre = 180.00;
    Double anglRadian = Math.PI;
    Double anglGrade = 200.00;
    Evaluator evaluator = new Evaluator();
    RadioButton rbDollard;
    RadioButton rbLivre;
    RadioButton rbBitcoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEcran = findViewById(R.id.tvEcran);

        rbDollard = findViewById(R.id.rbDollard);
        rbDollard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxDollars = Double.parseDouble(tvEcran.getText().toString());
                tvEcran.setText("1");
                vibrate();
                return true;
            }
        });

        rbLivre = findViewById(R.id.rbLivre);
        rbLivre.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxLivres = Double.parseDouble(tvEcran.getText().toString());
                tvEcran.setText("1");
                vibrate();
                return true;
            }
        });

        rbBitcoin = findViewById(R.id.rbBitcoin);
        rbBitcoin.setOnLongClickListener((new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxBitcoins = Double.parseDouble(tvEcran.getText().toString());
                tvEcran.setText("1");
                vibrate();
                return true;
            }
        }));

    }

    public void Touche(View view) {
        Button bt = (Button) view;
        String ecr = tvEcran.getText().toString();
        if (ecr.equals("0")) {
            ecr = "";
        }
        tvEcran.setText(ecr + bt.getText());
    }

    public void Effacer(View view) {
        tvEcran.setText("0");
    }

    public void convEuros(View view) {
        convDevise(tauxEntree, tauxEuro);
        tauxEntree = tauxEuro;
    }

    public void convDollars(View view) {
        convDevise(tauxEntree, tauxDollars);
        tauxEntree = tauxDollars;
    }

    public void convLivres(View view) {
        convDevise(tauxEntree, tauxLivres);
        tauxEntree = tauxLivres;
    }

    public void convBitcoin(View view) {
        convDevise(tauxEntree, tauxBitcoins);
        tauxEntree = tauxBitcoins;
    }

    private void convDevise(Double tauxdep, Double tauxarr) {
        tauxdep = tauxEntree;
        Double calc = Double.parseDouble(tvEcran.getText().toString());
        Double resultat = calc * tauxarr / tauxdep;
        DecimalFormat df = new DecimalFormat("#.##");
        String str = df.format(resultat);
        str = str.replace(",", ".");
        tvEcran.setText(str);
    }

    public void convDegre(View view) {
        convAngle(angleDepart, anglDegre);
        angleDepart = anglDegre;
    }

    public void convRad(View view) {
        convAngle(angleDepart, anglRadian);
        angleDepart = anglRadian;
    }

    public void convGrade(View view) {
        convAngle(angleDepart, anglGrade);
        angleDepart = anglGrade;
    }

    private void convAngle(double angdep, double angarr) {
        angdep = angleDepart;
        Double calc = Double.parseDouble(tvEcran.getText().toString());
        Double resultat = calc * angarr / angdep;
        String str = String.valueOf(resultat);
        tvEcran.setText(str);
    }


    public void resultat(View view) {
        String calc = tvEcran.getText().toString();
        try {
            Double result = evaluator.getNumberResult(calc);
            tvEcran.setText(String.valueOf(result));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }
    }

    public void vibrate() {
        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vb.hasVibrator()) {
            int vibrationEffect = 250;
            vb.vibrate(vibrationEffect);
        }
    }
}

