package it.oztaking.com.a51_contentprovidercontactsoperation;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-11-17.
 */

public class ContactQuery {

    public static List<ContactBean> QueryContacts(Context context) {
        //0.创建集合
        List<ContactBean> contactBeanlist = new ArrayList<>();
        //1.根据raw_contacts表中的contact_id得知其中存在几条联系人
        //使用内容解析者取数据
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(0);
                System.out.println("contact_id:" + contact_id);
                if (contact_id != null){

                    ContactBean contactBean = new ContactBean();
                    contactBean.setContactId(contact_id);

                    //2.根据contact_id在data表中查询minitype_id和data1的内容；
                    Uri uri1 = Uri.parse("content://com.android.contacts/data");
                    Cursor cursor1 = context.getContentResolver().query(uri1, new String[]{"data1","mimetype"},"raw_contact_id=?",new String[]{contact_id}, null);
                    if (cursor1 != null){

                        while (cursor1.moveToNext()){
                            String data1 = cursor1.getString(0);
                            String minetype = cursor1.getString(1);
                            if ("vnd.android.cursor.item/name".equals(minetype)){
                                contactBean.setName(data1);
                            }else if("vnd.android.cursor.item/phone_v2".equals(minetype)){
                                contactBean.setPhoneNum(data1);
                            }else if("vnd.android.cursor.item/email_v2".equals(minetype)){
                                contactBean.setMailAddress(data1);
                            }

                            System.out.println("data1:"+data1+"---"+"minitype:"+minetype);
                        }
                    }
                    contactBeanlist.add(contactBean);
                }
            }
        }
        return contactBeanlist;
    }

}
