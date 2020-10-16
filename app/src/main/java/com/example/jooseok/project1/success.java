package com.example.jooseok.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//// 성공했다는 congratulations xml파일의 자바코드
public class success extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congratulations);

        //// 메인 액티비티로부터 실행된다. 그리고 몇번만에 성공했는지를 받아 저장해둔다.
        Intent intent = getIntent();
        int a= intent.getIntExtra("count",0);
        //// 시도횟수를 view에 띄운다.
        TextView count = (TextView) findViewById(R.id.count);
        count.setText("Success in : "+a+" times!");
        //// 재시도 버튼을 누르면 난수를 새롭게 생성하고 현재 액티비티를 닫아 다시 게임화면으로 돌아간다.
        Button restart1 = (Button) findViewById(R.id.restart1);
        restart1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                MainActivity.makenum();
                finish();
            }
        });
    }
}
