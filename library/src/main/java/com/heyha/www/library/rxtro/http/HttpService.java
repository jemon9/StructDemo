package com.heyha.www.library.rxtro.http;


import com.heyha.www.library.rxtro.bean.HttpResult;
import com.heyha.www.library.rxtro.bean.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Heyha on 2016/12/20.
 */

public interface HttpService {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
