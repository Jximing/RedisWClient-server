package pers.jarome.redis.wclient.common.web.encrypt.method;

import pers.jarome.redis.wclient.common.web.encrypt.constants.EncryptMethod;
import pers.jarome.redis.wclient.common.web.encrypt.entity.AesEncrypt;
import pers.jarome.redis.wclient.common.web.encrypt.entity.Encrypt;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEcryptMappingHadler {
    private Map<EncryptMethod, Encrypt> encrypts;

    public AbstractEcryptMappingHadler() {
        initEncrypt();
    }

    private void initEncrypt() {
        this.encrypts = new HashMap<EncryptMethod, Encrypt>();
        encrypts.put(EncryptMethod.AES, new AesEncrypt());
    }

    public Encrypt getEncrypt(EncryptMethod method){
        return this.encrypts.get(method);
    }

    public void addEncrypt(EncryptMethod method, Encrypt encrypt) {
        if (encrypts == null) {
            initEncrypt();
        }
        encrypts.put(method, encrypt);
    }

    public void addEncrypt(Map<EncryptMethod, Encrypt> encrypts) {
        if (encrypts == null) {
            initEncrypt();
        }
        this.encrypts.putAll(encrypts);
    }
}
