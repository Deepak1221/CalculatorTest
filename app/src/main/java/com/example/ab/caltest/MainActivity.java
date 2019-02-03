package com.example.ab.caltest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2,execute,btn3;
EditText num1, num2;
TextView result,sign;

String operation="add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        execute = findViewById(R.id.execute);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        result = findViewById(R.id.result);
        sign= findViewById(R.id.sign);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation="add";
                btn1.setBackgroundColor(Color.parseColor("#cccccc"));
                btn2.setBackgroundColor(Color.parseColor("#f1f1f1"));
                btn3.setBackgroundColor(Color.parseColor("#f1f1f1"));
                sign.setText("+");

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation="multiply";
                btn1.setBackgroundColor(Color.parseColor("#f1f1f1"));
                btn2.setBackgroundColor(Color.parseColor("#cccccc"));
                btn3.setBackgroundColor(Color.parseColor("#f1f1f1"));
                sign.setText("*");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation="subtract";
                btn1.setBackgroundColor(Color.parseColor("#f1f1f1"));
                btn2.setBackgroundColor(Color.parseColor("#f1f1f1"));
                btn3.setBackgroundColor(Color.parseColor("#cccccc"));
                sign.setText("-");

            }
        });



        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.getText().toString().trim().length()<1||num2.getText().toString().trim().length()<1){
                    Toast.makeText(MainActivity.this, "Please enter number", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (operation.equalsIgnoreCase("add")){
                        sum(num1.getText().toString().trim().replace(" ",""),num2.getText().toString().trim().replace(" ",""));

                    }

                    else if (operation.equalsIgnoreCase("multiply")){
                        multiply(num1.getText().toString().trim().replace(" ",""), num2.getText().toString().trim().replace(" ",""));
                    }


                    else if(operation.equalsIgnoreCase("subtract")){

                        int n1 = Integer.parseInt(String.valueOf(num1.getText().toString().trim().replace(" ","").length()));
                        int n2= Integer.parseInt(String.valueOf(num2.getText().toString().trim().replace(" ","").length()));

                        if(n2>n1){
                            Toast.makeText(getApplicationContext(), "number 2 having more digits", Toast.LENGTH_SHORT).show();
                            return;

                        }

                        else {
                            subtract(num1.getText().toString().trim().replace(" ", ""), num2.getText().toString().trim().replace(" ", ""));
                        }

                    }
                }
            }
        });


    }

    private void sum(String str1, String str2) {

        if (str1.length() > str2.length()){
            String t = str1;
            str1 = str2;
            str2 = t;
        }

        // Take an empty String for storing result
        String str = "";

        // Calculate lenght of both String
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both of Strings
        str1=new StringBuilder(str1).reverse().toString();
        str2=new StringBuilder(str2).reverse().toString();

        int carry = 0;
        for (int i = 0; i < n1; i++)
        {
            // Do school mathematics, compute sum of
            // current digits and carry
            int sum = ((int)(str1.charAt(i) - '0') +
                    (int)(str2.charAt(i) - '0') + carry);
            str += (char)(sum % 10 + '0');

            // Calculate carry for next step
            carry = sum / 10;
        }

        // Add remaining digits of larger number
        for (int i = n1; i < n2; i++)
        {
            int sum = ((int)(str2.charAt(i) - '0') + carry);
            str += (char)(sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry > 0)
            str += (char)(carry + '0');

        // reverse resultant String
        str = new StringBuilder(str).reverse().toString();


        result.setText(str);
    }

 private void subtract(String str2,String str1){


     // Before proceeding further, make sure str1
     // is not smaller
     if (isSmaller(str1, str2))
     {
         String t = str1;
         str1 = str2;
         str2 = t;
     }

     // Take an empty string for storing result
     String str = "";

     // Calculate lengths of both string
     int n1 = str1.length(), n2 = str2.length();
     int diff = n1 - n2;

     // Initially take carry zero
     int carry = 0;

     // Traverse from end of both strings
     for (int i = n2 - 1; i >= 0; i--)
     {
         // Do school mathematics, compute difference of
         // current digits and carry
         int sub = (((int)str1.charAt(i + diff) - (int)'0') -
                 ((int)str2.charAt(i) - (int)'0') - carry);
         if (sub < 0)
         {
             sub = sub+10;
             carry = 1;
         }
         else
             carry = 0;

         str += String.valueOf(sub);
     }

     // subtract remaining digits of str1[]
     for (int i = n1 - n2 - 1; i >= 0; i--)
     {
         if (str1.charAt(i) == '0' && carry > 0)
         {
             str += "9";
             continue;
         }
         int sub = (((int)str1.charAt(i) - (int)'0') - carry);
         if (i > 0 || sub > 0) // remove preceding 0's
             str += String.valueOf(sub);
         carry = 0;

     }


     result.setText(new StringBuilder(str).reverse().toString());


 }

     private boolean isSmaller(String str1, String str2)
    {
        // Calculate lengths of both string
        int n1 = str1.length(), n2 = str2.length();

        if (n1 < n2)
            return true;
        if (n2 > n1)
            return false;

        for (int i = 0; i < n1; i++)
        {
            if (str1.charAt(i) < str2.charAt(i))
                return true;
            else if (str1.charAt(i) > str2.charAt(i))
                return false;
        }
        return false;
    }



    private  void multiply(String num1,String num2){

        String tempnum1 = num1;

        String tempnum2 = num2;

        // Check condition if one string is negative
        if(num1.charAt(0) == '-' && num2.charAt(0)!='-')
        {
            num1 = num1.substring(1);
        }
        else if(num1.charAt(0) != '-' && num2.charAt(0) == '-')
        {
            num2 = num2.substring(1);
        }
        else if(num1.charAt(0) == '-' && num2.charAt(0) == '-')
        {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        }
        String s1 = new StringBuffer(num1).reverse().toString();
        String s2 = new StringBuffer(num2).reverse().toString();

        int[] m = new int[s1.length()+s2.length()];

        // Go from right to left in num1
        for (int i = 0; i < s1.length(); i++)
        {
            // Go from right to left in num2
            for (int j = 0; j < s2.length(); j++)
            {
                m[i+j] = m[i+j]+(s1.charAt(i)-'0')*(s2.charAt(j)-'0');

            }
        }


        String product = new String();
        // Multiply with current digit of first number
        // and add result to previously stored product
        // at current position.
        for (int i = 0; i < m.length; i++)
        {
            int digit = m[i]%10;
            int carry = m[i]/10;
            if(i+1<m.length)
            {
                m[i+1] = m[i+1] + carry;
            }
            product = digit+product;

        }

        // ignore '0's from the right
        while(product.length()>1 && product.charAt(0) == '0')
        {
            product = product.substring(1);
        }

        // Check condition if one string is negative
        if(tempnum1.charAt(0) == '-' && tempnum2.charAt(0)!='-')
        {
            product = new StringBuffer(product).insert(0,'-').toString();
        }
        else if(tempnum1.charAt(0) != '-' && tempnum2.charAt(0) == '-')
        {
            product = new StringBuffer(product).insert(0,'-').toString();
        }
        else if(tempnum1.charAt(0) == '-' && tempnum2.charAt(0) == '-')
        {
            product = product;
        }

        result.setText(product);
        System.out.println("Product of the two numbers is :"+"\n"+product);
    }
}


