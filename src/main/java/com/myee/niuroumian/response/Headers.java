package com.myee.niuroumian.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Headers {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String HEADER_STATUS_CODE = "Status-Code";

    public static final String HEADER_NOTICE_TYPE = "Notice-Type";

    public static final String HEADER_ACK_NOTICE_TYPE = "Ack-Notice-Type";

    public static final String HEADER_BOARD_NUMBER = "Board-Number";

    public static final String HEADER_REQUEST_ID = "Request-Id";

    public static final String HEADER_TAGS = "Tags";

    private Map<String, Object> attributes;

    public Headers() {
        this.attributes = new HashMap<String, Object>();
    }

    public Headers(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getHeaders() {
        return this.attributes;
    }

    public void set(String header, Object value) {
        this.attributes.put(header, value);
    }

    protected String getString(String headerName) throws HeaderNotFoundException {
        if (!attributes.containsKey(headerName)) {
            throw new HeaderNotFoundException();
        }
        Object value = attributes.get(headerName);
        return value.toString();
    }

    public String getContentType() throws HeaderNotFoundException {
        return getString(Headers.HEADER_CONTENT_TYPE);
    }

    public String getNoticeType() throws HeaderNotFoundException {
        return getString(Headers.HEADER_NOTICE_TYPE);
    }

    public String getAckNoticeType() throws HeaderNotFoundException {
        return getString(Headers.HEADER_ACK_NOTICE_TYPE);
    }

    public String getBoardNumber() throws HeaderNotFoundException {
        return getString(Headers.HEADER_BOARD_NUMBER);
    }

    public void setBoardNumber(String boardNumber) {
        this.attributes.put(HEADER_BOARD_NUMBER, boardNumber);
    }

    @SuppressWarnings("unchecked")
    public List<String> getTags() throws HeaderNotFoundException {
        if (!attributes.containsKey(Headers.HEADER_TAGS)) {
            throw new HeaderNotFoundException();
        }
        Object value = attributes.get(Headers.HEADER_TAGS);
        return (List<String>) value;
    }

    public void setTags(List<String> tags) {
        this.attributes.put(HEADER_TAGS, tags);
    }

    public void copyTo(Response response) {
        if (null == response) {
            return;
        }
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("Request-")) {
                response.headerWapper().set(key, entry.getValue());
            }
        }
    }
}

