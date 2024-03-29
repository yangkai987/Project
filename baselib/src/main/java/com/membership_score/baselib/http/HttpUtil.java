package com.membership_score.baselib.http;


import android.support.annotation.NonNull;
import com.membership_score.baselib.http.listener.DownLoadListener;
import com.membership_score.baselib.http.listener.OnTokenInvalidListener;
import com.membership_score.baselib.http.listener.RequestListener;
import com.membership_score.baselib.http.other.TimeOut;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;

/**
 * Author : zhoujiulong
 * Email : 754667445@qq.com
 * Time : 2017/03/24
 * Desc : 网络请求工具类
 */
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * 设置 Token 失效回调，全局只调用一次，在 Application 中设置
     */
    public static void initTokenInvalidListener(OnTokenInvalidListener onTokenInvalidListener) {
        RequestHelper.getInstance().setOnTokenInvalidListener(onTokenInvalidListener);
    }

    /**
     * 添加请求头部拦截器
     */
    public static void addHeaderInterceptor(Interceptor headerInterceptor) {
        ServiceManager.getInstance().setHeaderInterceptor(headerInterceptor);
    }

    /**
     * 获取BaseUrl
     */
    public static String getBaseUrl() {
        return ServiceManager.getInstance().getBaseUrl();
    }

    /**
     * 获取 Call ，使用默认的 BaseUrl
     *
     * @param callClass 需要获取的 Call 对应的 Class
     * @param timeOuts  TimeOut 超时设置，可变参数，不设置使用默认的
     * @param <T>       返回的 Call 类型
     */
    public static synchronized <T> T getService(@NonNull Class<T> callClass, TimeOut... timeOuts) {
        return ServiceManager.getInstance().getService(callClass, timeOuts);
    }

    /**
     * 获取 Call ，使用传入的 BaseUrl
     *
     * @param callClass 需要获取的 Call 对应的 Class
     * @param baseUrl   BaseUrl
     * @param timeOuts  TimeOut 超时设置，可变参数，不设置使用默认的
     * @param <T>       返回的 Call 类型
     */
    public static synchronized <T> T getService(@NonNull Class<T> callClass, @NonNull String baseUrl, TimeOut... timeOuts) {
        return ServiceManager.getInstance().getService(callClass, baseUrl, timeOuts);
    }

    /**
     * 发送请求
     *
     * @param tag      请求标记，用于取消请求用
     * @param listener 请求完成后的回调
     * @param <T>      请求返回的数据对应的类型，第一层必须继承 BaseResponse
     */
    public static <T> void sendRequest(@NonNull final String tag, @NonNull retrofit2.Call<T> call, @NonNull final RequestListener<T> listener) {
        RequestHelper.getInstance().sendRequest(tag, call, listener);
    }

    /**
     * 发送下载网络请求
     *
     * @param tag              请求标记，用于取消请求用
     * @param downLoadFilePath 下载文件保存路径
     * @param downloadListener 下载回调
     */
    public static void sendDownloadRequest(@NonNull final String tag, @NonNull retrofit2.Call<ResponseBody> call, @NonNull final String downLoadFilePath,
                                           @NonNull final String fileName, @NonNull final DownLoadListener downloadListener) {
        RequestHelper.getInstance().sendDownloadRequest(tag, call, downLoadFilePath, fileName, downloadListener);
    }

    /**
     * 根据请求的标记 tag 取消请求和 Observer
     */
    public static void cancelWithTag(String tag) {
        RequestManager.getInstance().cancelRequestWithTag(tag);
    }
}




















