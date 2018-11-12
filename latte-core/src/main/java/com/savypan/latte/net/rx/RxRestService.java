package com.savypan.latte.net.rx;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by savypan on 2018/11/11.
 */

public interface RxRestService {

    @GET
    Observable<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @PUT
    @FormUrlEncoded
    Observable<String> put(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @PUT
    @FormUrlEncoded
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<String> delete(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Streaming //避免一次性在内容写入大块内容防止OOM,所以要在异步线程中处理，不可以在主线程中处理
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);
}
