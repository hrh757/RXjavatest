package hrh.commonlib.commonlib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import hrh.commonlib.commonlib.api.IProgressCallback;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.ResponseBody;

public class ToolUtils {
    /**
     * 将文件写入本地
     *
     * @param responseBody     请求结果全体
     * @param destFileDir      目标文件夹
     * @param destFileName     目标文件名
     * @param progressCallback 进度监听器
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    public static File saveFile(@NonNull ResponseBody responseBody, @NonNull String destFileDir,
                                @NonNull String destFileName, @Nullable IProgressCallback progressCallback) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = responseBody.byteStream();
            final long total = responseBody.contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                //这里就是对进度的监听回调
                if (progressCallback != null)
                    progressCallback.onProgress(destFileName, (int) (finalSum * 100 / total), total);
            }
            fos.flush();

            return file;

        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 批量删除文件
     * <p>
     * 所需权限：<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     */
    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }

    /**
     * 静默安装或升级
     *
     * @param apkAbsolutePath
     * @return
     */
    public static boolean silenceInstallApk(String apkAbsolutePath) {
        String[] args = {"pm", "install", "-r", apkAbsolutePath};
        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read;
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            baos.write("/n".getBytes());
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            byte[] data = baos.toByteArray();
            result = new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (errIs != null) {
                    errIs.close();
                }
                if (inIs != null) {
                    inIs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return !TextUtils.isEmpty(result) && result.lastIndexOf("Success") > 0;
    }

    /**
     * 静默卸载应用
     *
     * @param packageName
     * @return
     */
    public static boolean silenceUninstall(String packageName) {
        String[] args = {"pm", "uninstall", packageName};
        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read;
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            baos.write("\n".getBytes());
            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }
            byte[] data = baos.toByteArray();
            result = new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (errIs != null) {
                    errIs.close();
                }
                if (inIs != null) {
                    inIs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return !TextUtils.isEmpty(result) && result.lastIndexOf("Success") > 0;
    }

    /**
     * 判断APP是否安装了
     *
     * @param context
     * @param packageName 包名
     * @return 返回已安装版本号，如果返回{@code -1}则为未安装
     */
    public static int getInstalledVersionCode(Context context, String packageName) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            PackageInfo info = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    /**
     * 获取MAC地址
     *
     * @return 获取成功返回MAC地址，失败返回null
     */
    public static String getMac() {
        try {
            String mac = null;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader("/sys/class/net/eth0/address");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                if (fileReader != null) {
                    in = new BufferedReader(fileReader, 1024);
                    mac = in.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileReader != null)
                        fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return mac;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
