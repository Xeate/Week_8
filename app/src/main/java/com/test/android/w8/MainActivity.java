package com.test.android.w8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView text;

    Context context = null;

    Integer amount = 0;

    static int input;

    private Spinner spinner1;
    private Button btnBuy;

    ArrayList<Bottle> bottles = new ArrayList<Bottle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        //############Täytetään automaattiin erilaisia pulloja
        for (int i = 0; i< 1; i++) {
            Bottle bottle = new Bottle("Pepsi Max", "Pepsi", 0.3, 0.5, 1.8);
            bottles.add(bottle);

        }

        for (int i = 0; i< 1; i++) {
            Bottle bottle = new Bottle("Pepsi Max", "Pepsi", 0.3, 1.5, 2.2);
            bottles.add(bottle);

        }

        for (int i = 0; i< 1; i++) {
            Bottle bottle = new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3, 0.5, 2.0);
            bottles.add(bottle);

        }

        for (int i = 0; i< 1; i++) {
            Bottle bottle = new Bottle("Coca-Cola Zero", "Coca-Cola", 0.3, 1.5, 2.5);
            bottles.add(bottle);

        }

        for (int i = 0; i< 1; i++) {
            Bottle bottle = new Bottle("Fanta Zero", "Fanta", 0.3, 0.5, 1.95);
            bottles.add(bottle);

        }
        //############

        text = (TextView) findViewById(R.id.textView2);

        addListenerOnButton();

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView seekBarValue = (TextView)findViewById(R.id.seekbarvalue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarValue.setText(String.valueOf(progress));
                amount = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

        });

    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner);
        btnBuy = (Button) findViewById(R.id.osta);

        btnBuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Tarkastetaan onko pulloja jäljellä ollenkaan
                if (bottledispenser.getBottles() == 0) {
                    System.out.println("Unlucky keissi. Kaikki pullot loppu!");
                }

                else {

                    int check = 0;

                    for (int i=0; i<bottledispenser.getBottles(); i++) {

                        int lenght = String.valueOf(spinner1.getSelectedItem()).length();

                        String listName = bottles.get(i).getName();

                        //Otetaan haluttu pullo spinneristä
                        String spinnerName = String.valueOf(spinner1.getSelectedItem());

                        if (spinnerName.substring(0, lenght - 4).equals(listName) && (Double.valueOf(spinnerName.substring(lenght - 4, lenght)) == (bottles.get(i).getSize()))) {

                            if (bottledispenser.buyBottle(listName, bottles.get(i).getPrice()) == 1) {
                                //Käynnistetään metodi kuitin luomiseen
                                writeFile(spinnerName.substring(0, lenght - 4), bottles.get(i).getPrice());
                                //Poistetaan pullo ArrayLististä
                                bottles.remove(i);
                                bottledispenser.setBottles();
                                check = 1;

                            }
                            else {
                                check = 1;
                            }
                        }

                    }

                    //Ilmoitetaan halutun pullon loppumisesta
                    if (check == 0) {
                        System.out.println("Unlucky keissi. Haluamasi pullo loppu!");
                    }

                }
            }

        });
    }

    BottleDispenser bottledispenser = BottleDispenser.getInstance();

    public void addMoney(View view) {
        bottledispenser.addMoney(amount);
    }

    public void returnMoney(View view) {
        bottledispenser.returnMoney();
    }

    //Luodaan kuitti
    public void writeFile(String name, Double price) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("Kuitti.txt", Context.MODE_PRIVATE));

            String s = "";

            s = "Ostettu tuote: " + name + "\nHinta: " + price;
            ows.write(s);
            ows.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }
    }
}