package io.gagan.rest_demo;

public class WelcomeBean {
    private String msg;

    public WelcomeBean(String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }

    public void setMessage(String msg){
        this.msg = msg;
    }
}
