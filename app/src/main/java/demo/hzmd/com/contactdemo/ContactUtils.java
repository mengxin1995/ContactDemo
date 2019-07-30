package demo.hzmd.com.contactdemo;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/6/21.
 */

public class ContactUtils {
    public static HashMap<String, ArrayList<String>> getAllContacts(Context context) {
        //{"17898745612": ["张三"，"李四"], "110":["警察叔叔"]}
        HashMap<String, ArrayList<String>> contactsMap = new HashMap<>();
        //获取联系人电话号码
        Cursor phoneCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        while (phoneCursor.moveToNext()) {
            String phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phone = phone.replace("-", "");
            phone = phone.replace(" ", "");
            String name = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            if (contactsMap.containsKey(phone)) {
                ArrayList<String> names = contactsMap.get(phone);
                names.add(name);
            } else {
                ArrayList<String> names = new ArrayList<>();
                names.add(name);
                contactsMap.put(phone, names);
            }
        }
        //记得要把cursor给close掉
        phoneCursor.close();
        return contactsMap;
    }
}
