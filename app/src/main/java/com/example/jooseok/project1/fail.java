package com.example.jooseok.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//// 기회를 소진해 실패했다는 gameover액티비티의 자바코드다.
public class fail extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);

        TextView answer = (TextView) findViewById(R.id.answer);
        //// 메인액티비티로부터 실행되고 방금 사용자가 실패했던 정답 숫자들을 받아 저장한 후 view에 출력한다.
        Intent intent = getIntent();
        int num1= intent.getIntExtra("num1",0);
        int num2= intent.getIntExtra("num2",0);
        int num3= intent.getIntExtra("num3",0);
        int num4= intent.getIntExtra("num4",0);
        answer.setText("Answer was -> "+num1+num2+num3+num4);

        //// 재시도 버튼을 누르면 난수를 새롭게 생성하고 현재 액티비티를 닫는다.
        Button restart2 = (Button) findViewById(R.id.restart2);
        restart2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                MainActivity.makenum();
                finish();
            }
        });
    }
}