package it.oztaking.com.a51_contentprovidercontactsoperation;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContactQuery contactQuery = new ContactQuery();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_QueryContacts = (Button) findViewById(R.id.bt_QueryContacts);
        Button bt_InsertContacts = (Button) findViewById(R.id.bt_InsertContacts);
        bt_InsertContacts.setOnClickListener(this);
        bt_QueryContacts.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_QueryContacts:
                List<ContactBean> contactBeanList = contactQuery.QueryContacts(getApplicationContext());
                for (ContactBean contactBean:contactBeanList){
                    System.out.println("--------------------------------");
                    System.out.println("contact:"+contactBean);
                }
                break;
            case R.id.bt_InsertContacts:
                InsertContacts();
                break;
            default:
                break;
        }
    }



    public void InsertContacts() {

    }
}
