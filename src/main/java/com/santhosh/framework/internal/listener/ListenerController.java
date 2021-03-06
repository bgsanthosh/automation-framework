package com.santhosh.framework.internal.listener;

import com.santhosh.framework.internal.config.ConfigName;
import com.santhosh.framework.internal.config.FrameworkConfig;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class ListenerController {

    private static boolean isListenerDisabled = false;

    static {

        isListenerDisabled = FrameworkConfig.getBoolean(ConfigName.DISABLE_LISTENER.getConfigName());
    }


    public static boolean isListenerDisabled(ConfigName configNameValue) {

        if (isListenerDisabled) return true;

        else return FrameworkConfig.getBoolean(configNameValue.getConfigName());


    }

}
