package com.example.alex.headhunter;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class NetService extends IntentService {

    public static final String EXTRA_RECEIVER = "com.example.alex.headhunter.extra.RECEIVER";

    private static final String ACTION_FOO = "com.example.alex.headhunter.action.FOO";
    private static final String ACTION_BAZ = "com.example.alex.headhunter.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.example.alex.headhunter.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.alex.headhunter.extra.PARAM2";


    public NetService() {
        super("NetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
