package com.membership_score.baselib.image.listener;

import com.bumptech.glide.load.engine.GlideException;


public interface OnProgressListener {
    /**
     * @param imageUrl   图片地址
     * @param percent    下载进度百分百
     * @param bytesRead  下载了多少字节
     * @param totalBytes 总共的大小
     * @param isDone     是否完成
     * @param exception  异常
     */
    void onProgress(String imageUrl, int percent, long bytesRead, long totalBytes, boolean isDone, GlideException exception);
}
