package cn.adminzero.passwordshield_demo0;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {
    private String username;
    private String password;
    private String website;
    private String note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent_FromListActivity=getIntent();
        username=intent_FromListActivity.getStringExtra("username");
        password=intent_FromListActivity.getStringExtra("password");
        website=intent_FromListActivity.getStringExtra("webstie");
        note=intent_FromListActivity.getStringExtra("note");
        setContentView(R.layout.activity_modify);
        final Button passwod_button = (Button) findViewById(R.id.password_display);
        final Button username_button =(Button)findViewById(R.id.username);
        Button website_button= (Button) findViewById(R.id.websit_display);
        Button note_button=(Button) findViewById(R.id.note_display);

        note_button.setText(note);
        website_button.setText(website);
        passwod_button.setText("●●●●●●●●●●");
        username_button.setText(username);

        passwod_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showPopupMenu(passwod_button);
                PopupMenu popupMenu = new PopupMenu(ModifyActivity.this, passwod_button);
                // menu布局
                popupMenu.getMenuInflater().inflate(R.menu.password_display, popupMenu.getMenu());
                // menu的item点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.password_copy:
                                Toast.makeText(ModifyActivity.this,"调用API把密码复制到剪切板",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.password_display:
                                passwod_button.setText("123456789");
                                //passwod_button.setText(password);

                        }
                        //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
                // PopupMenu关闭事件
                    /*popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                        @Override
                        public void onDismiss(PopupMenu menu) {
                            Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
                        }
                    });*/
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.modify, menu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.modify_icon:
                Intent intent_ToEditActivity=new Intent(ModifyActivity.this,EditActivity.class);
                String debugdata="debug_data_1111";
                //   intent_ToEditActivity.putExtra("username","111111111111111111111");
                intent_ToEditActivity.putExtra("username",debugdata);
                intent_ToEditActivity.putExtra("password",password);
                intent_ToEditActivity.putExtra("website",website);
                intent_ToEditActivity.putExtra("note",note);
                Toast.makeText(ModifyActivity.this, "修改", Toast.LENGTH_SHORT).show();
                startActivity(intent_ToEditActivity);
                break;
            default:
                break;
        }
        return true;
    }

}
