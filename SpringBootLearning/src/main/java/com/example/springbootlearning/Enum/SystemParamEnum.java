package com.example.springbootlearning.Enum;

public enum SystemParamEnum {

    TOKENISNOTEXIST("token is not exist"),
    NOAUTHORIZED("no authorized"),
    SUCCESS("success"),
    NOPERMISSION("No Permission"),
    USERREPEAT("使用者帳號重複 請更換"),
    ERROR("使用者帳號或密碼錯誤 無法刪除"),
    ACCOUNTNOUP("帳號未啟用"),
    ACCOUNTNOPERMISSION("使用者帳號權限不足");

    private final String message;
    SystemParamEnum(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
