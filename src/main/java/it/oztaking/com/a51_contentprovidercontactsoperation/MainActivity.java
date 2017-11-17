package it.oztaking.com.a51_contentprovidercontactsoperation;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContactQuery contactQuery = new ContactQuery();

    private EditText et_name;
    private EditText et_phoneNum;
    private EditText et_emaiAddress;

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


//
    public void InsertContacts() {
        //1.先查询现在有几条数据contact_id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
        //2.将contact_id值增加1

        if (cursor != null){
            int contactNum = cursor.getCount();
            int newContactId = contactNum+1;
            System.out.println("newContactId:"+newContactId);
            //3.将newContactId插入表中
            Uri uri1 = Uri.parse("content://com.android.contacts/data");
            ContentValues values = new ContentValues();
            values.put("contact_id",newContactId);
            getContentResolver().insert(uri,values);


            //3.将name phoneNum mailAddress数据插入表中
            et_name = (EditText) findViewById(R.id.et_name);
            et_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
            et_emaiAddress = (EditText) findViewById(R.id.et_emaiAddress);

            String name = et_name.getText().toString().trim();
            String phoneNum = et_phoneNum.getText().toString().trim();
            String mailAddress = et_emaiAddress.getText().toString().trim();

            System.out.println("--"+name+"--"+phoneNum+"--"+mailAddress);

            ContentValues nameValues = new ContentValues();
            nameValues.put("data1",name);
            nameValues.put("mimetype","vnd.android.cursor.item/name");
            nameValues.put("raw_contact_id",newContactId);
            getContentResolver().insert(uri1,nameValues);

            ContentValues phoneNumValues = new ContentValues();
            phoneNumValues.put("data1",phoneNum);
            phoneNumValues.put("mimetype","vnd.android.cursor.item/phone_v2");
            phoneNumValues.put("raw_contact_id",newContactId);
            getContentResolver().insert(uri1,phoneNumValues);

            ContentValues mailAddressValues = new ContentValues();
            mailAddressValues.put("data1",mailAddress);
            mailAddressValues.put("mimetype","vnd.android.cursor.item/email_v2");
            mailAddressValues.put("raw_contact_id",newContactId);
            getContentResolver().insert(uri1,mailAddressValues);


        }



    }
}
