package com.example.acer.myzhibo.bean;

/**
 * Created by acer on 2016/11/30.
 */

public class VersionBean {


    /**
     * status : 0
     * error : ok
     * data : {"version":2,"vsersion_url":"http://oh0vbg8a6.bkt.clouddn.com/app-debug.apk"}
     */

    private int status;
    private String error;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * version : 2
         * vsersion_url : http://oh0vbg8a6.bkt.clouddn.com/app-debug.apk
         */

        private int version;
        private String vsersion_url;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getVsersion_url() {
            return vsersion_url;
        }

        public void setVsersion_url(String vsersion_url) {
            this.vsersion_url = vsersion_url;
        }
    }
}
