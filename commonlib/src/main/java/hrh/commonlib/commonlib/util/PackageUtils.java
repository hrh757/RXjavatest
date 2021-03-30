package hrh.commonlib.commonlib.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class PackageUtils {
    /**
     * 获取应用版本号
     *
     * @param context
     * @return 版本号或-1
     */
    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getVersionCode(Context context,String packageName){
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取应用版本名称
     *
     * @param context
     * @return 版本名称或null
     */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取App的名称
     *
     * @param context 上下文
     * @return 返回名称或null
     */
    public static String getAppName(Context context) {
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取应用 信息
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            //获取albelRes
            int labelRes = applicationInfo.labelRes;
            //返回App的名称
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取前台程序包名，该方法仅在android L之前有效
     */
    public static String getForegroundAppPackageName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> lr = am.getRunningAppProcesses();
        if (lr == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo ra : lr) {
            if (ra.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE || ra.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return ra.processName;
            }
        }
        return "";
    }
}
