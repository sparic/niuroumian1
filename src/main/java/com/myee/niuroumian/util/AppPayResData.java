package com.myee.niuroumian.util;

public class AppPayResData {

    //Э���
    /**
     * ����״̬��
     */
    private String return_code  = "";

    /**
     * ������Ϣ
     */
    private String return_msg   = "";

    //Э�鷵�صľ������ݣ������ֶ���return_code ΪSUCCESS ��ʱ���з��أ�
    /**
     * �����˺�ID
     */
    private String appid        = "";

    /**
     * �̻���
     */
    private String mch_id       = "";

    /**
     * ����ַ���
     */
    private String nonce_str    = "";

    /**
     * ǩ��
     */
    private String sign         = "";

    /**
     * ҵ����
     */
    private String result_code  = "";

    /**
     * �������
     */
    private String err_code     = "";

    /**
     * �����������
     */
    private String err_code_des = "";

    /**
     * �豸��
     */
    private String device_info  = "";

    //ҵ�񷵻صľ������ݣ������ֶ���return_code ��result_code ��ΪSUCCESS ��ʱ���з��أ�
    /**
     * ��������
     */
    private String trade_type   = "";

    /**
     * Ԥ֧�����׻Ự��ʶ
     */
    private String prepay_id    = "";

    /**
     * ��ά������
     */
    private String code_url     = "";

    private String   timeStamp;        //����΢��H5ǰ�˹��ں�֧����ʱ���

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

}