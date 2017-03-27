package com.emv.myapplication;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.emv.myapplication.dto.RequestResponse;
import com.google.gson.Gson;

/**
 * Created by g on 26/03/17.
 */

public class GsonRequest extends Request<RequestResponse> {

    private ResponseRequest responseRequest;

    public GsonRequest(int method, String url, Response.ErrorListener listener, ResponseRequest responseRequest) {
        super(method, url, listener);
        this.responseRequest = responseRequest;
    }

    @Override
    protected Response<RequestResponse> parseNetworkResponse(NetworkResponse response) {
        Gson gson = new Gson();
        RequestResponse requestResponse1 = gson.fromJson(new String(response.data), RequestResponse.class);
        Log.i("http", requestResponse1.toString());
        return Response.success(requestResponse1, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(RequestResponse requestResponse) {
        responseRequest.getResponse(requestResponse);
    }

    public interface ResponseRequest {
        void getResponse(RequestResponse requestResponse);
    }

}
