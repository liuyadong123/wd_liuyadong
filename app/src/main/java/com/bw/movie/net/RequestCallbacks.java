package com.bw.movie.net;

public interface RequestCallbacks {
    void OnSuccess(Object o);
    void Failure(String msg);
}
