package pers.jarome.redis.wclient.common.web.encrypt.pojo;

public class AesData {

    private String iv;
    private String data;

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
