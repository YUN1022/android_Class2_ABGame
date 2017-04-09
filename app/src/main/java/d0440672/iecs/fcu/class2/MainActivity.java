package d0440672.iecs.fcu.class2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView showAB;
    Button submitNum;
    EditText putNum;
    int answer[]=new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        randomNum();

        submitNum=(Button)findViewById(R.id.submit_btn);
        putNum=(EditText)findViewById(R.id.input_int);
        showAB=(TextView)findViewById(R.id.result_text);
        submitNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_str=putNum.getText().toString();
                putNum.setText("");

                if(input_str.length()!=4) {
                    Toast.makeText(MainActivity.this, "The length should be 4", Toast.LENGTH_SHORT).show();
                    return;
                }
                showAB.setText(compare(input_str));
            }
        });
    }

    private void randomNum(){
        boolean flag=true;
        while(flag){
            answer[0]=(int)(Math.random()*9);
            answer[1]=(int)(Math.random()*9);
            answer[2]=(int)(Math.random()*9);
            answer[3]=(int)(Math.random()*9);
            flag=false;

            for(int i=0;i<4;i++){
                for(int j=i+1;j<4;j++){
                    if(answer[i]==answer[j]){
                        flag=true;
                    }
                }
            }
        }

    }

      private String compare(String input_str){
        String result=new String();
        int guest=Integer.parseInt(input_str);
        int guestNum[]=new int[4];


        guestNum[0]=guest/1000;
        guestNum[1]=(guest%1000)/100;
        guestNum[2]=(guest%100)/10;
        guestNum[3]=(guest%10);

          for(int i=0;i<4;i++){
              for(int j=i+1;j<4;j++){
                  if(guestNum[i]==guestNum[j]){
                      result="數字不能重複";
                      return  result;
                  }
              }
          }


        int countA=0,countB=0;
        for(int i=0;i<4;i++){
            if(guestNum[i]==answer[i]){
                countA++;
            }
        }
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(i==j){
                    continue;
                }
                if(guestNum[i]==answer[j]){
                    countB++;
                }
            }

        }
        result=countA+"A"+countB+"B";
        return result;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_ans:
                AlertDialog.Builder ad_ans=new AlertDialog.Builder(this);
                ad_ans.setTitle("答案:");
                ad_ans.setMessage(""+answer[0]+answer[1]+answer[2]+answer[3]);
                ad_ans.show();
                break;
            case R.id.action_author:
                AlertDialog.Builder ad_author=new AlertDialog.Builder(this);
                ad_author.setTitle("作者:");
                ad_author.setMessage("孫青雲");
                ad_author.show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
