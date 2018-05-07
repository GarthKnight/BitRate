package com.bitrate.mvp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by seishu on 05.05.18.
 */

public class Coin extends RealmObject {
    /**
     * {
     "id": "VME",
     "fullName": "Verime Mobile",
     "crypto": true,
     "payinEnabled": true,
     "payinPaymentId": false,
     "payinConfirmations": 2,
     "payoutEnabled": true,
     "payoutIsPaymentId": false,
     "transferEnabled": true,
     "delisted": false,
     "payoutFee": "154"
     },
     */

    @PrimaryKey
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCrypto(boolean crypto) {
        this.crypto = crypto;
    }

    private String fullName;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isCrypto() {
        return crypto;
    }

    boolean crypto;
}
