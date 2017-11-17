package it.oztaking.com.a51_contentprovidercontactsoperation;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
                QueryContacts();
                break;
            case R.id.bt_InsertContacts:
                InsertContacts();
                break;
            default:
                break;
        }
    }

    public void QueryContacts() {

        //1.根据raw_contacts表中的contact_id得知其中存在几条联系人
        //使用内容解析者取数据
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(0);
                System.out.println("contact_id:" + contact_id);

                //2.根据contact_id在data表中查询minitype_id和data1的内容；
                Uri uri1 = Uri.parse("content://com.android.contacts/data");
                Cursor cursor1 = getContentResolver().query(uri1, new String[]{"data1","mimetype"},"raw_contact_id=?",new String[]{contact_id}, null);
                if (cursor1 != null){
                    while (cursor1.moveToNext()){
                        String data1 = cursor1.getString(0);
                        String minetype = cursor1.getString(1);
                        System.out.println("data1:"+data1+"---"+"minitype:"+minetype);
                    }
                }
            }
        }
    }

    public void InsertContacts() {

    }
}
