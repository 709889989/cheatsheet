package com.retrofit.demo;

public class Uid {
    private String uId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Uid{");
        sb.append("uId='").append(uId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
