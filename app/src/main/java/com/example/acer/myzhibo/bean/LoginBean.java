package com.example.acer.myzhibo.bean;

/**
 * Created by acer on 2016/11/23.
 */

public class LoginBean {
 //{"os":"1","v":"2.2.4","p":
 //{"device":"AknYJ6H1svk5AhUoGvUAuBZRAkPW56PHz_AMlFX7FGHN","challenge":"37fe8d29055239eb4936742a57bbfe7f3d","password":"zyc987654","seccode":"3f246dfbb4f632
   //  f32cffbd9e5d6dee3a|jordan","validate":"3f246dfbb4f632f32cffbd9e5d6dee3a","name":"15804267967"}}
    public  int os =1;
    public  String v = "2.2.4";
    public p p ;
    public class p{


        public String device ;
        public String challenge;
        public  String password;
        public  String seccode ;
        public  String validate;
        public  String name ;

        public p(String password, String name) {
            this.password = password;
            this.name = name;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getChallenge() {
            return challenge;
        }

        public void setChallenge(String challenge) {
            this.challenge = challenge;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSeccode() {
            return seccode;
        }

        public void setSeccode(String seccode) {
            this.seccode = seccode;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "p{" +
                    "device='" + device + '\'' +
                    ", challenge='" + challenge + '\'' +
                    ", password='" + password + '\'' +
                    ", seccode='" + seccode + '\'' +
                    ", validate='" + validate + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public LoginBean.p getP() {
        return p;
    }

    public void setP(LoginBean.p p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "os=" + os +
                ", v='" + v + '\'' +
                ", p=" + p +
                '}';
    }
}
