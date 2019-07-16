package cn.adminzero.passwordshield_demo0.util;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.adminzero.passwordshield_demo0.PasswordGeneratorActivity;
import cn.adminzero.passwordshield_demo0.R;

import static cn.adminzero.passwordshield_demo0.db.DbUtil.AddAccount;
import static cn.adminzero.passwordshield_demo0.db.DbUtil.deletePasswordItem;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText account_text;
    private EditText password_text;
    private EditText website_text;
    private EditText note_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏  好像没用

        setContentView(R.layout.activity_add_account);



        Button cancel=(Button) findViewById(R.id.cancel);
        Button commit=(Button) findViewById(R.id.commit);
        Button creat_password=(Button) findViewById(R.id.creat_password);
        //
        account_text=(EditText)findViewById(R.id.account);
        password_text=(EditText)findViewById(R.id.password);
        website_text=(EditText)findViewById(R.id.website);
        note_text=(EditText)findViewById(R.id.note);


        Intent intent=getIntent();
        //    account_text.setText("11111");//debug 使用
        account_text.setText(intent.getStringExtra("account"));
        password_text.setText(intent.getStringExtra("password"));
        website_text.setText(intent.getStringExtra("uri"));
        note_text.setText(intent.getStringExtra("note"));

        cancel.setOnClickListener(this);
        commit.setOnClickListener(this);
        creat_password.setOnClickListener(this);

    }
    @Override
    public  void  onClick (View v){
        switch (v.getId()){
            case R.id.commit:
                String input_account=account_text.getText().toString();
                String input_password=password_text.getText().toString();
                String input_website=website_text.getText().toString();
                String input_note=note_text.getText().toString();
                //删除原来的列表
                deletePasswordItem(input_account,input_website);
                //加入新的
                AddAccount(input_account,input_password,1,input_website,input_note);
                Toast.makeText(this,"已保存："+"账号"+input_account+"密码"+input_password+"网址"+input_website+"备注"+input_note,Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.cancel:
                finish();
                Toast.makeText(this,"取消添加账号信息",Toast.LENGTH_LONG).show();
                break;
            case R.id.creat_password:
                Intent intent_to_creat_password= new Intent(EditActivity.this, PasswordGeneratorActivity.class);
                startActivity(intent_to_creat_password);
                break;
            default:
                break;
        }
    }
}