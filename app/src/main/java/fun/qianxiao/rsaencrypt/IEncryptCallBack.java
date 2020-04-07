package fun.qianxiao.rsaencrypt;

/**
 * Create by Administrator
 * On 2020/4/7
 */
public interface IEncryptCallBack {
    void EncryptSuccess(String c);
    void EncryptError(Throwable throwable);
}
