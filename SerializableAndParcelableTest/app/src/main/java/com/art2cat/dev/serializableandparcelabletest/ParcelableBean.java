package com.art2cat.dev.serializableandparcelabletest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rorschach
 * on 12/10/16 7:37 PM.
 */

public class ParcelableBean implements Parcelable {

    public String message;

    protected ParcelableBean(Parcel in) {
        message = in.readString();
    }

    public static final Creator<ParcelableBean> CREATOR = new Creator<ParcelableBean>() {
        @Override
        public ParcelableBean createFromParcel(Parcel in) {
            return new ParcelableBean(in);
        }

        @Override
        public ParcelableBean[] newArray(int size) {
            return new ParcelableBean[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
    }
}
