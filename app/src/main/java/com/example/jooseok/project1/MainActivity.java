package com.example.jooseok.project1;

import android.content.Intent;
import android.graphics.Color;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;;import java.util.Random;
//// 메인 액티비티
public class MainActivity extends Activity {
    int[] arr = new int[4];
    int strike=0, ball=0;
    static int i, chance;
    static int[] answer = new int[4];

    ////  중복되지 않는 4자리 난수생성과 기회를 다시 10번으로 세팅을 초기화하는 메소드. answer에 숫자들을 저장한다.
    public static void makenum(){
        Random random = new Random();
        i=0;
        chance=10;
        for(int k=0; k<4; k++){
            answer[k]=random.nextInt(9);
                for(int l=0; l<k; l++){
                    if(answer[l]==answer[k]){
                        k--;
                        break;}
                }
        }
    }

    //// 액티비티 구현
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////난수생성, 처음 액티비티 뷰 생성. 아이디 참조.
        makenum();
        Button one = (Button) findViewById(R.id.one);
        Button two = (Button) findViewById(R.id.two);
        Button three = (Button) findViewById(R.id.three);
        Button four = (Button) findViewById(R.id.four);
        Button five = (Button) findViewById(R.id.five);
        Button six = (Button) findViewById(R.id.six);
        Button seven = (Button) findViewById(R.id.seven);
        Button eight = (Button) findViewById(R.id.eight);
        Button nine = (Button) findViewById(R.id.nine);
        Button zero = (Button) findViewById(R.id.zero);
        Button reset = (Button) findViewById(R.id.reset);

        one.setOnClickListener(listener);
        two.setOnClickListener(listener);
        three.setOnClickListener(listener);
        four.setOnClickListener(listener);
        five.setOnClickListener(listener);
        six.setOnClickListener(listener);
        seven.setOnClickListener(listener);
        eight.setOnClickListener(listener);
        nine.setOnClickListener(listener);
        zero.setOnClickListener(listener);
        reset.setOnClickListener(listener);

    }
    //// click event의 listener 등록
    View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                TextView result = (TextView) findViewById(R.id.result);
                TextView num1 = (TextView) findViewById(R.id.num1);
                TextView num2 = (TextView) findViewById(R.id.num2);
                TextView num3 = (TextView) findViewById(R.id.num3);
                TextView num4 = (TextView) findViewById(R.id.num4);
                //// case문으로 다르게 작동하도록 onClick구현
                switch (v.getId()) {
                    case R.id.one:
                        {if(i!=4) arr[i++]=1; break;}         //// arr는 사용자의 입력을 저장한다. 4자리를 전부 입력했다면 아무작동도 하지않고 reset키를 눌러야한다.
                    case R.id.two:
                        {if(i!=4) arr[i++]=2; break;}
                    case R.id.three:
                        {if(i!=4) arr[i++]=3; break;}
                    case R.id.four:
                        {if(i!=4) arr[i++]=4; break;}
                    case R.id.five:
                        {if(i!=4) arr[i++]=5; break;}
                    case R.id.six:
                        {if(i!=4) arr[i++]=6; break;}
                    case R.id.seven:
                        {if(i!=4) arr[i++]=7; break;}
                    case R.id.eight:
                        {if(i!=4) arr[i++]=8; break;}
                    case R.id.nine:
                        {if(i!=4) arr[i++]=9; break;}
                    case R.id.zero:
                        {if(i!=4) arr[i++]=0; break;}
                    case R.id.reset: {                         //// reset 키를 누르면 arr를 처음부터 다시 저장하고 입력상태가 초기화됬음을 표시한다.
                        i=0;
                        num1.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        num2.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        num3.setBackgroundColor(Color.parseColor("#EEEEEE"));
                        num4.setBackgroundColor(Color.parseColor("#EEEEEE")); break;
                    }
                }


                ///// 저장후 i 즉, 입력된 숫자개수에 따라 액티비티에 입력상태를 표시하거나 4개가 입력됬으면 정답인 answer어레이와 비교한다.
                switch(i){
                    case 0:  result.setText("reset"); break;
                    case 1:  num1.setBackgroundColor(Color.parseColor("#FF0000")); break;
                    case 2:  num2.setBackgroundColor(Color.parseColor("#FF0000")); break;
                    case 3:  num3.setBackgroundColor(Color.parseColor("#FF0000")); break;
                    case 4: {
                        num4.setBackgroundColor(Color.parseColor("#FF0000"));
                        i=0; chance--;                   //// 4개 입력이면 어레이를 다시 입력하게 하고 기회를 한번 소진한다.
                        while(i<4){                     //// 4개 숫자를 비교하여 자리까지 같으면 strike, 숫자만 같으면 ball
                        for(int j=0; j<4; j++){
                            if(arr[i]==answer[j]){
                                if(i==j) strike++;
                                else     ball++;}
                        }
                        i++;
                        }

                        //// 4strike면 성공했다는 activity를 띄우고 몇번만의 기회로 성공했는지 데이터를 전송했다. 메인 액티비티는 초기입력상태로 다시 만들어 주었다.
                        if(strike==4){
                            result.setText("reset");
                            num1.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            num2.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            num3.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            num4.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            Intent intent = new Intent(MainActivity.this, success.class);
                            intent.putExtra("count",10-chance);
                            startActivity(intent);}

                        //// 4strike가 아닌데 기회가 0이되었으면 실패했다는 activity를 띄우고 정답이 무엇이었는지를 전송했다.
                        else if(chance==0){
                            result.setText("reset");
                            num1.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            num2.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            num3.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            num4.setBackgroundColor(Color.parseColor("#EEEEEE"));
                            Intent intent = new Intent(MainActivity.this, fail.class);
                            intent.putExtra("num1",MainActivity.answer[0]);
                            intent.putExtra("num2",MainActivity.answer[1]);
                            intent.putExtra("num3",MainActivity.answer[2]);
                            intent.putExtra("num4",MainActivity.answer[3]);
                            startActivity(intent);}

                        //// 정답을 맞춘것도, 기회를 소진한 것도 아니면 몇번의 기회가 남았는지와 reset을 누르도록 잠시 안내(toast)해주고 strike와 ball을 view에 띄워 사용자에게 알려준다.
                        else {
                            Toast.makeText(MainActivity.this, chance + "chances lefted (*click reset*)", Toast.LENGTH_SHORT).show();
                            if ((strike == 0) && (ball == 0))
                                result.setText("out!!");
                            else
                                result.setText(strike + "S  " + ball + "B");
                        }
                        //// strike ball 초기화
                        strike=0;
                        ball=0;
                    break;}
                }
            }
    };

    //// 옵션메뉴를 추가했다.
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        menu.add(Menu.NONE,1, Menu.NONE,"ANSWER");
        menu.add(Menu.NONE,2, Menu.NONE,"HINT1");
        menu.add(Menu.NONE,3, Menu.NONE,"HINT2");
        return true;
    }
    //// 메뉴 버튼 구현. 각각 toast를 활용하여 푸쉬 알람으로 알려 주게끔 했다.
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            //// ANSWER를 누르면 현재의 정답을 전부 알려준다.
            case 1:{
                Toast.makeText(MainActivity.this,"ANSWER:"+answer[0]+answer[1]+answer[2]+answer[3],Toast.LENGTH_SHORT).show();
                return true;
            }
            ////  HINT1과 HINT2는 각각 첫째와 마지막 자리 정답 숫자를 알려준다.
            case 2:{
                Toast.makeText(MainActivity.this,"First number:"+answer[0],Toast.LENGTH_SHORT).show();
                return true;
            }
            case 3:{
                Toast.makeText(MainActivity.this,"Last number:"+answer[3],Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

}



