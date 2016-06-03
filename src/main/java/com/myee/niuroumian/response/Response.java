package com.myee.niuroumian.response;

import com.alibaba.fastjson.JSON;

import java.io.File;

public class Response<T> extends Message implements Status {
    private           int code;
    private transient T   result;

    public Response() {
    }

    public Response(String message, int code) {
        this.setBody(message);
        this.setCode(code);
    }

    public Response(T result) {
        this.setCode(RETURN_SUCCESS);
        this.setResult(result);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Response successResult() {
        return new Response("OK");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Response errorResult(String message) {
        return new Response(message, RETURN_ERROR);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.ACK;
    }

    public String errorMessage() {
        return getBody();
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.setBody(JSON.toJSONString(result));
        this.result = result;
    }

    public boolean success() {
        return code == RETURN_SUCCESS;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("E:\\download_home\\100\\aaa");
        file.mkdirs();

//        OrgProfileVo profileVo = new OrgProfileVo();
//        profileVo.setOrgID(104L);
//        profileVo.setProfileMd5("木爷实业");
//        Message message = Message.create().messageType(MessageType.NOTIFICATION).header("Notice-Type", "FETCH_ORG_INFO").json(profileVo).build();
////        URL url = new URL("http://localhost:8080/cleverm/sockjs/execCommand");
//        URL url = new URL("http://192.168.1.110:10008/web");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//        conn.setRequestMethod("POST");
//        conn.connect();
//        OutputStream os = conn.getOutputStream();
//        String input = JSON.toJSONString(message);
//        os.write(input.getBytes("utf-8"));
//        os.flush();
//        os.close();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf8"));
//        String line = reader.readLine();
//        reader.close();
//        System.out.println(line);
    }
}
