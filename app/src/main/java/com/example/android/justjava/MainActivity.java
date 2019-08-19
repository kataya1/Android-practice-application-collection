/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 0;
    double coffeprice = 0;
    double priceofonecoffe= 1;
    double wippedcreamprice = 100;
    boolean checked , checked2 ;
    double choclateprice = 10000;
    String namemessage;
    String addressss = "mermaid.resturant@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(numberOfCoffees);
        displayMessage("$" + String.format("%.2f",coffeprice));
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);

        priceTextView.setText( message);
    }

    /**
     * increments the numberofcoffees when the + button is clicked
     *
     * @param view
     */

    public void increment(View view) {

        numberOfCoffees++;
        coffeprice += priceofonecoffe;
        wippedCreaminc(view);
        Choclateinc(view);
        submitOrder(view);
    }

    public void decrement(View view) {
        if (numberOfCoffees == 0)
            numberOfCoffees = 0;


        else
        {   numberOfCoffees--;
            coffeprice -= priceofonecoffe;
            wippedcreamdec(view);
            Choclatedec(view);
        }
        /*if (numberOfCoffees == 0)
        {
            CheckBox wip = (CheckBox) findViewById(R.id.checkBox);
            wip.setChecked(false);
        }*/

        submitOrder(view);
    }

    public void selfdistruct(View view) {


        String message =  namemessage +"\n" + getString(R.string.total) + coffeprice;
        if (checked)
            message = message + "\n" + numberOfCoffees+ " " + getString(R.string.wipcrm) + wippedcreamprice + getString(R.string.each);
        if (checked2)
            message = message + "\n" + numberOfCoffees + " " + getString(R.string.choctop) + choclateprice + getString(R.string.each);
        message = message + "\n" + getString(R.string.getplayed);
//        displayMessage(message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" )); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"mermaid.restaurant@gmail.com"});
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.putExtra(Intent.EXTRA_SUBJECT, namemessage + getString(R.string.order));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

   public void wippedcream(View view) {
       CheckBox wip = (CheckBox) findViewById(R.id.checkBox);
       checked = wip.isChecked();

       if (wip.isChecked()) {
           coffeprice += wippedcreamprice * numberOfCoffees;
           submitOrder(view);

       } else {
           coffeprice -= wippedcreamprice * numberOfCoffees;
           submitOrder(view);}

       if(numberOfCoffees==0)
       {
           wip.setChecked(false);
       }
   }
   public void wippedcreamdec(View view)
   {
       CheckBox wip = (CheckBox) findViewById(R.id.checkBox);
       if (wip.isChecked()) {
           coffeprice = coffeprice - wippedcreamprice ;}
       if (numberOfCoffees==0)
           wip.setChecked(false);

   }
   public void wippedCreaminc(View view)
   {
       CheckBox wip = (CheckBox) findViewById(R.id.checkBox);
       if(wip.isChecked())
           coffeprice += wippedcreamprice;
   }
    public void Choclate(View view) {
        CheckBox coco = (CheckBox) findViewById(R.id.checkBox2);
        checked2 = coco.isChecked();

        if (coco.isChecked()) {
            coffeprice += choclateprice * numberOfCoffees;
            submitOrder(view);

        } else {
            coffeprice -= choclateprice * numberOfCoffees;
            submitOrder(view);}

        if(numberOfCoffees==0)
        {
            coco.setChecked(false);
        }
    }
    public void Choclatedec(View view)
    {
        CheckBox coco = (CheckBox) findViewById(R.id.checkBox2);
        if (coco.isChecked()) {
            coffeprice = coffeprice - choclateprice ;}
        if (numberOfCoffees==0)
            coco.setChecked(false);

    }
    public void Choclateinc(View view)
    {
        CheckBox coco = (CheckBox) findViewById(R.id.checkBox2);
        if(coco.isChecked())
            coffeprice += choclateprice;
    }
    public void mgetname(View view)
    {
        EditText text = findViewById(R.id.personName);
        namemessage =  text.getText().toString();


    }
}