package com.savypan.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.savypan.latte.app.Latte;
import com.savypan.latte.net.callback.IRequest;
import com.savypan.latte.net.callback.ISuccess;
import com.savypan.latte.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by savypan on 2018/11/11.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object[] params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];

        final InputStream is = body.byteStream();
        if (downloadDir != null || downloadDir.equals("")) {
            downloadDir = "downloads";
        }

        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null || name.equals("")) {
            return FileUtil.writeToDisk(is,downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        autoInstallApk(file);
    }

    /**
     * 自动安装apk
     * @param file
     */
    private void autoInstallApk(File file) {

        if (file == null) {
            return;
        }

        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplication().startActivity(intent);
        }
    }
}
