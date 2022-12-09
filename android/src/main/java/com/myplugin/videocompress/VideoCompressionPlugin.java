package com.myplugin.videocompress;

import static android.os.Environment.getExternalStoragePublicDirectory;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.File;


@CapacitorPlugin(name = "VideoCompression")
public class VideoCompressionPlugin extends Plugin {

    private VideoCompression implementation = new VideoCompression();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @Override
    public void load() {
        super.load();
    }


    @PluginMethod
    public void compressVideo(PluginCall pluginCall) {

        String filePath = pluginCall.getString("filePath");

        Log.d("Video_Compression", "File Path -->" + filePath);

        new AsyncTaskRunner(getContext(), filePath, pluginCall).execute();
    }

    @PluginMethod
    public void compressImage(PluginCall pluginCall) {

        String filePath = pluginCall.getString("filePath");

        Log.d("Video_Compression", "File Path -->" + filePath);

        new AsyncTaskImage(getContext(), filePath, pluginCall).execute();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;
        Context mContext;
        String mPath;
        PluginCall pluginCall;

        AsyncTaskRunner(Context mContext, String mPath, PluginCall pluginCall) {
            this.mContext = mContext;
            this.mPath = mPath;
            this.pluginCall = pluginCall;
        }

        @Override
        protected String doInBackground(String... params) {

            String filepath;
            try {
                filepath = SiliCompressor.with(mContext).compressVideo(mPath, getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/dhaiyur/videos");
            } catch (Exception e) {
                filepath = e.getMessage();
            }
            return filepath;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            JSObject ret = new JSObject();
            ret.put("value", result);
            pluginCall.resolve(ret);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(mContext,
                    "ProgressDialog",
                    "Wait for seconds");
        }
    }

    private class AsyncTaskImage extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;
        Context mContext;
        String mPath;
        PluginCall pluginCall;

        AsyncTaskImage(Context mContext, String mPath, PluginCall pluginCall) {
            this.mContext = mContext;
            this.mPath = mPath;
            this.pluginCall = pluginCall;
        }

        @Override
        protected String doInBackground(String... params) {

            String filepath;
            try {
                filepath = SiliCompressor.with(mContext).compress(mPath, new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/compressImage"));
            } catch (Exception e) {
                filepath = e.getMessage();
            }
            return filepath;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            JSObject ret = new JSObject();
            ret.put("value", result);
            pluginCall.resolve(ret);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(mContext,
                    "ProgressDialog",
                    "Wait for seconds");
        }
    }
}