package com.architecture.android.mydatabinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by yangsimin on 2017/12/11.
 */

public class User extends BaseObservable {

    private String name;

    public User(String name){
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }



}
