package cn.edu.s07150839gdmec.mycalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText weigthEdittext;
    private RadioButton manRadiobutton;
    private RadioButton womanRadiobutton;
    private Button calculateButton;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weigthEdittext = (EditText) findViewById(R.id.weight);
        manRadiobutton = (RadioButton) findViewById(R.id.man);
        womanRadiobutton = (RadioButton) findViewById(R.id.woman);
        calculateButton = (Button) findViewById(R.id.calculate);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent(){
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //体重不为空
                if(!weigthEdittext.getText().toString().trim().equals("")){
                    //有选择性别
                    if(manRadiobutton.isChecked()||womanRadiobutton.isChecked()){
                        Double weigth = Double.parseDouble(weigthEdittext.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("---------------评估结果---------------\n");
                        if(manRadiobutton.isChecked()){
                            sb.append("男性标准身高:");
                            //计算
                            double result = evaluateHeight(weigth,"男");
                            sb.append( (int)result+"厘米");
                        }else{
                            sb.append("女性标准身高:");
                            //计算
                            double result = evaluateHeight(weigth,"女");
                            sb.append( (int)result+"厘米");
                        }
                        //显示结果
                        resultTextView.setText(sb.toString());
                    }else{
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请输入体重");
                }
            }
        });
    }

    private void showMessage(String message) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private double evaluateHeight(double weigth, String sex) {
        double height;
        if(sex=="男"){
            height = 170-(62-weigth)/0.6;
        }else{
            height = 158-(52-weigth)/0.5;
        }
        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
