package com.art2cat.dev.serializableandparcelabletest;

import java.io.Serializable;

/**
 * Created by Rorschach
 * on 12/10/16 7:37 PM.
 */

public class SerializableBean implements Serializable {

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
