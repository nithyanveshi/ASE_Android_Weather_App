package com.raparthiss.weatherapp.service;

import com.raparthiss.weatherapp.data.Channel;

/**
 * Created by rapar on 2/3/2016.
 */
public interface CallBackInterface {
    void success(Channel channel);
    void failure(Exception exp);
}
