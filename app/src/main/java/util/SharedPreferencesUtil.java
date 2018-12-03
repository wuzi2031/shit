package util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by msy on 2017/6/22.
 */

public class SharedPreferencesUtil {
    public static final String SP_NAME = "shit";
    public static final String TOKEN = "token";


    /**
     * 保存数据到文件
     *
     * @param filename
     * @param key
     * @param data
     */
    public static void saveData(Context context,String filename, String key, Object data) {

        String type = data.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }
        editor.commit();
    }

    /**
     * 从文件中读取数据
     *
     * @param filename
     * @param key
     * @param defValue
     * @return
     */
    public static Object getData(Context context,String filename, String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);

        //defValue为默认值，如果当前获取不到数据就返回null
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 从文件中删除数据
     *
     * @param filename
     * @param key
     * @return
     */
    public static void deleteData(Context context,String filename, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

//    /**
//     * 获取登录的用户
//     *
//     * @return
//     */
//    public static UserEntity getUser(Context context) {
//        Object object = readObject(context, KEY_CURRENT_USER, SP_NAME);
//        UserEntity userEntity=null;
//        if(object!=null){
//            userEntity = new UserEntity();
//                String userIdenty = (String) getFieldValueByName("userIdenty",object);
//                String userName =  (String) getFieldValueByName("userName",object);
//                String userNickName =  (String) getFieldValueByName("userNickName",object);
//                String userId =  (String) getFieldValueByName("userId",object);
//                userEntity.setUserId(userId);
//                userEntity.setUserIdenty(userIdenty);
//                userEntity.setUserName(userName);
//                userEntity.setUserNickName(userNickName);
//        }
//        return userEntity;
//    }
//    /**
//     * 根据属性名获取属性值
//     * */
//    private static Object getFieldValueByName(String fieldName, Object o) {
//        try {
//            String firstLetter = fieldName.substring(0, 1).toUpperCase();
//            String getter = "get" + firstLetter + fieldName.substring(1);
//            Method method = o.getClass().getMethod(getter, new Class[] {});
//            Object value = method.invoke(o, new Object[] {});
//            return value;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//    /**
//     * desc:保存对象
//     *
//     * @param context
//     * @param key
//     * @param obj     要保存的对象，只能保存实现了serializable的对象
//     *                modified:
//     */
//    public static void saveObject(Context context, Object obj, String key, String fileName) {
//        ByteArrayOutputStream baops = null;
//        ObjectOutputStream oos = null;
//        try {
//            // 保存对象
//            SharedPreferences.Editor sharedata = context
//                    .getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
//            baops = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(baops);
//            oos.writeObject(obj);
//            String objBody = Base64.encodeToString(baops.toByteArray(), Base64.DEFAULT);
//            sharedata.putString(key, objBody);
//            sharedata.commit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (oos != null)
//                    oos.close();
//                if (baops != null)
//                    baops.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    /**
//     * desc:获取保存的Object对象
//     *
//     * @param context
//     * @param key
//     * @return modified:
//     */
//    public static Object readObject(Context context, String key, String fileName) {
//        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
//        String objBody = sp.getString(key, "");
//        if (TextUtils.isEmpty(objBody))
//            return null;
//
//        byte[] bytes = Base64.decode(objBody.getBytes(), Base64.DEFAULT);
//        ObjectInputStream ois = null;
//        Object obj = null;
//        try {
//            ois = new ObjectInputStream((new ByteArrayInputStream(bytes)));
//            obj = ois.readObject();
//            Log.e("retrofit", "readObject-" + obj.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (ois != null)
//                    ois.close();
//            } catch (IOException e) {
//
//            }
//        }
//        return obj;
//    }

}
