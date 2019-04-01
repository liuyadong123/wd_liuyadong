package com.bw.movie.net;

public interface RequestCallback {
    void Success(String result);
    void Failure(String msg);
}
