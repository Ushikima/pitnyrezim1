package com.example.pitnyrezim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView glassImageView;  //sklenicka
    private TextView totalWaterAmountTextView;  //kolik dohromady vypito
    private EditText waterInputEditText; //pridani kolik jsem pila
    private Button addButton; //pridat mnozstvi
    private Button resetButton; //reset celkove
    private Button checkWaterButton; //tlacitko napise jak jsem si vedla
    private TextView historyTextView; //kolik jsem pridala

    private int totalWaterAmount = 0;  //Ze zacatku je to 0ml
    private ArrayList<String> historyList = new ArrayList<>(); //historie je v retezci chronol.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glassImageView = findViewById(R.id.glassImageView);
        totalWaterAmountTextView = findViewById(R.id.totalWaterAmountTextView);
        waterInputEditText = findViewById(R.id.waterInputEditText);
        addButton = findViewById(R.id.addButton);
        resetButton = findViewById(R.id.resetButton);
        checkWaterButton = findViewById(R.id.checkWaterButton);
        historyTextView = findViewById(R.id.historyTextView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = waterInputEditText.getText().toString();
                if (!inputText.isEmpty()) {
                    int addedAmount = Integer.parseInt(inputText);
                    totalWaterAmount += addedAmount;  //updateovani pridani tekutin
                    historyList.add(addedAmount + " ml");  //historie tekutin v ml
                    updateUI();
                    waterInputEditText.setText("");
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalWaterAmount = 0;  //resetuje celkove tekutiny
                historyList.clear();  //Smaze historii zapsani
                updateUI();
            }
        });

        checkWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Dnes jste vypila " + totalWaterAmount + " ml vody. ";
                if (totalWaterAmount < 1500) {
                    message += "Pila jste málo vody.";
                } else {
                    message += "Pila jste dostatečně vody.";
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });


    }

    private void updateUI() {
        totalWaterAmountTextView.setText("Celkem tekutin: " + totalWaterAmount + " ml");
        historyTextView.setText("Historie:\n" + String.join("\n", historyList));

        if (totalWaterAmount == 0) {
            glassImageView.setImageResource(R.drawable.sklenice);//0ml je prazdna
        } else if (totalWaterAmount > 0 && totalWaterAmount < 1500) {
            glassImageView.setImageResource(R.drawable.pulkasklenice);//pod 1500 je pulka
        } else if (totalWaterAmount >= 1500) {
            glassImageView.setImageResource(R.drawable.plnasklenice);//nad 1500 je plna
        }
    }
}
