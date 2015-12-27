package com.example.alex.headhunter.utils;

import android.content.Intent;
import android.os.Bundle;

public interface ServiceCallbackListener {
//    void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle data);
    void onServiceCallback(int requestId, int resultCode, Bundle data);
}