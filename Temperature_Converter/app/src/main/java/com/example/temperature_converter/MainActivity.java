package com.example.temperature_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeTestEvent(View view) {
        //텍스트 박스
        final TextView tempPannel$ = (TextView) findViewById(R.id.TempPannel);
        final TextView test$ = (TextView) findViewById(R.id.testText);

        //라디오 그룹
        final RadioGroup fromGroup$ = (RadioGroup) findViewById(R.id.fromGroup);
        final RadioGroup toGroup$ = (RadioGroup) findViewById(R.id.toGroup);
        int fromId = fromGroup$.getCheckedRadioButtonId();
        int toId = toGroup$.getCheckedRadioButtonId();
        RadioButton fromRb = (RadioButton) findViewById(fromId);
        RadioButton toRb = (RadioButton) findViewById(toId);

        //입력 소수
        final EditText InputNum$ = (EditText) findViewById(R.id.editTextNumber);


        if (InputNum$.getText().toString().length() != 0 && fromRb != null && toRb != null) {
            tempPannel$.setText(convertTemp(InputNum$.getText().toString(),fromRb.getText().toString() ,toRb.getText().toString() ));
//            test$.setText("Input Number: " + InputNum$.getText() + "라디오버튼: " + fromRb.getText().toString() + ", " + toRb.getText().toString());
        } else {
            test$.setText("부족한 항목이 있습니다.");
        }
    }

    public String stringToSymbol(String tempString){
        String symbol = null;

        switch (tempString){
            case "Celsius":
                symbol = "℃";
                break;
            case "Fahrenheit":
                symbol = "°F";
                break;
            case "Kelvin":
                symbol= "K";
                break;
            case "Rankine":
                symbol = "°R";
                break;
            default:
                return "ERR";
        }
        return symbol;
    }
    public String convertTemp(String num, String from, String to){

        String tempA, tempB;


        tempA = stringToSymbol(from);
        tempB = stringToSymbol(to);
        final TextView test$ = (TextView) findViewById(R.id.testText);
        if ( tempA.equals(tempB)){
            test$.setText("["+tempA+"] = ["+tempB+"]");
            return (num+tempA+" = "+num+tempB);
        }


        if ( tempA.equals("℃")){
            switch (tempB){
                case "°F":
                    // 섭씨를 화씨로
                    test$.setText("[°F] = [℃] x 5/9 + 32");
                    return (num+tempA+" = "+Math.round((Double.parseDouble(num)*1.8 + 32)*1000) / 1000.0+tempB);
                case "K":
                    // 섭씨를 켈빈으로
                    test$.setText("[K] = [℃] + 273.15");
                    return (num+tempA+" = "+Math.round((Double.parseDouble(num)+ 273.15)*1000)/1000.0+tempB);
                case "°R":
                    // 섭씨를 란씨로
                    test$.setText("[°R] = [℃] * 9/5 + 491.67");
                    return (num+tempA+" = "+Math.round((Double.parseDouble(num)*1.8+ 491.67)*1000)/1000.0+tempB);
            }
        }

        if ( tempA.equals("°F")){
            switch (tempB){
                case "℃":
                    // 화씨를 섭씨로
                    test$.setText("[℃] = ( [°F] - 32 ) * 5/9");
                    return (num+tempA+" = "+Math.round(((Double.parseDouble(num) - 32) * ((double)5/9) )*1000) / 1000.0+tempB);
                case "K":
                    // 화씨를 켈빈으로
                    test$.setText("[K] = ( [°F] - 32 ) * 5/9 + 273.15");
                    return (num+tempA+" = "+Math.round(((Double.parseDouble(num) - 32 ) * ((double)5/9) + 273.15) * 1000) / 1000.0+tempB);
                case "°R":
                    // 화씨를 란씨로
                    test$.setText("[°R] = [°F] + 459.67");
                    return (num+tempA+" = "+Math.round((Double.parseDouble(num)+ 459.67)*1000)/1000.0+tempB);
            }
        }

        if ( tempA.equals("K")){
            switch (tempB){
                case "℃":
                    // 켈빈을 섭씨로
                    test$.setText("[℃] = [K] - 273.15");
                    return (num+tempA+" = "+Math.round( ( (Double.parseDouble(num) - 273.15)) * 1000) / 1000.0+tempB);
                case "°F":
                    // 켈빈을 화씨로
                    test$.setText("[°F] = ( [K] - 273.15 ) x 9/5 + 32");
                    return (num+tempA+" = "+Math.round(((Double.parseDouble(num) - 273.15 ) * ((double)9/5) + 32) * 1000) / 1000.0+tempB);
                case "°R":
                    // 켈빈을 란씨로
                    test$.setText("[°R] = [K] * 1.8");
                    return (num+tempA+" = "+Math.round((Double.parseDouble(num) * 1.8 )*1000)/1000.0+tempB);
            }
        }

        if ( tempA.equals("°R")){
            switch (tempB){
                case "℃":
                    // 란씨를 섭씨로
                    test$.setText("[℃] = ( [°R] - 491.67 ) x 5/9");
                    return (num+tempA+" = "+Math.round( ( (Double.parseDouble(num) - 491.67) * (double)5/9) * 1000) / 1000.0+tempB);
                case "°F":
                    // 란씨를 화씨로
                    test$.setText("[°F] = [°R] - 459.67");
                    return (num+tempA+" = "+Math.round(((Double.parseDouble(num) - 459.67 )) * 1000) / 1000.0+tempB);
                case "K":
                    // 란씨를 켈빈으로
                    test$.setText("[K] = [°R] * 5/9");
                    return (num+tempA+" = "+Math.round((Double.parseDouble(num) * (double)5/9 )*1000)/1000.0+tempB);
            }
        }

        return "ERR";
    }
}