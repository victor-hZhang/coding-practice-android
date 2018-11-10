package com.codingtest.deeplinktest.codingtest.apiService;

public interface ResponseSubscriber<T> {
    //This is a great place to build a common error handling Service,
    //For example if you have different category of error message and different colored display
    //and handling in App. This can all be wrapped around in this layer.
    //I'm just simply returning a errorMessage for now.

    void onError(String errorMessage);

    void onSuccess(T t);
}
