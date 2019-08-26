package com.nhxy.sxs.demo.enums;

public enum StatusCode {
    Success(1, "数据获取成功"),
    DeleteSuccess(1, "删除成功"),
    Fail(0, "数据获取失败"),
    ParamFail(0, "参数错误"),
    VerifyFail(0, "验证失败"),
    ResignFail(0, "重签失败,可能是因为您太长久未登录"),
    DeleteFail(0,"删除错误"),
    ExistFail(0,"此条记录已经存在于数据库"),
    NoExistFail(0,"此条记录不存在"),
    AddFail(0,"参数错误"),
    UserNameError(0,"用户名错误"),
    UserNameLengthError(0,"用户名长度不在规定范围内"),
    PasswordError(0,"密码错误"),
    PasswordLengthError(0,"密码长度不在规定范围内");
    final private Integer Code;
    final private String msg;

    StatusCode(Integer Code, String msg) {
        this.Code = Code;
        this.msg = msg;
    }

    public Integer getCode() {
        return Code;
    }

    public String getMsg() {
        return msg;
    }

}
