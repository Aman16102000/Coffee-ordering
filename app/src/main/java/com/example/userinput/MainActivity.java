package com.example.userinput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view)
    {
        quantity++;
        display(quantity);
    }
    public void minus(View view)
    {
        if(quantity==0)
        quantity=0;
        else
            quantity--;
        display(quantity);
    }
    public void SubmitOrder(View view) {


//        Intent intent= new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:30.325471259328104,76.40441894531251"));
//        if(intent.resolveActivity(getPackageManager())!=null)
//        {
//            startActivity(intent);
//        }
     int price;

     price= cal_price(quantity);

     boolean twcStatus=toppingv(R.id.wc);
        boolean tchStatus=toppingv(R.id.ch);
        boolean tvStatus=toppingv(R.id.vn);
     if(twcStatus==true)
         price=price+10;
     if(tchStatus==true)
         price+=15;
     if(tvStatus==true)
         price+=20;
     displayOrderPrice(price);



        dispalyOrderSummary(quantity,twcStatus);
    }
    public boolean toppingv(int id_name)
    {
        CheckBox status=findViewById(id_name);
        boolean ll=status.isChecked() ;
        return  ll;
    }
    /*public boolean toppingsCh()
    {
        CheckBox ch=findViewById(R.id.ch);
        boolean ss=ch.isChecked();
            return  ss;
    }
    public boolean toppingswc()
    {
        CheckBox wc=findViewById(R.id.wc);
        boolean whip_cream=wc.isChecked();
        return whip_cream;
    }*/
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.Quantity);
        quantityTextView.setText("" + number);
    }
    private void displayOrderPrice(int price)
    {
        TextView price_textView=findViewById(R.id.price);
        price_textView.setText("Total Rs: "+price);
    }
    private void dispalyOrderSummary(int quantity,boolean tstatus)
    {
        EditText CName=findViewById(R.id.name);
        String CustomerName=CName.getText().toString();
        String orderSummary="";
        orderSummary=orderSummary+CustomerName+"\n";
        orderSummary=orderSummary+"Quantity:"+quantity+"\n";
        if(tstatus==true)
        orderSummary=orderSummary+"Toppings:Whip Cream \n";
        //Intent to start an activity of email  
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Thanks for shopping in Coffe App");
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        TextView os=(TextView) findViewById(R.id.ordersummary);
        os.setText(orderSummary);
    }
    private int cal_price(int number) {
        return number*20;
    }

}