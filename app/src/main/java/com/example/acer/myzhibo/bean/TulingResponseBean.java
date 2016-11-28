package com.example.acer.myzhibo.bean;

/**
 * Created by acer on 2016/11/26.
 */

public class TulingResponseBean {


    /**
     * reason : 成功的返回
     * result : {"code":100000,"text":"我不会说英语的啦，你还是说中文吧。"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * code : 100000
         * text : 我不会说英语的啦，你还是说中文吧。
         */

        private int code;
        private String text;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
