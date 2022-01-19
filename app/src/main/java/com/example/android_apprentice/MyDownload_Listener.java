package com.example.android_apprentice;

public interface MyDownload_Listener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
