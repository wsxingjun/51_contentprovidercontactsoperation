package it.oztaking.com.a51_contentprovidercontactsoperation;

/**
 * Created by Administrator on 2017-11-17.
 */

public class ContactBean {
    public String contactId;
    public String name;
    public String phoneNum;
    public String mailAddress;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return ("Contact[id="+contactId+"--name:"+name+"--phoneNum:"+phoneNum+"--mailAddress:"+mailAddress+"]");

    }
}
