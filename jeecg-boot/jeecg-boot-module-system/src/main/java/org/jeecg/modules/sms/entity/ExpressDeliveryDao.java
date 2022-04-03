package org.jeecg.modules.sms.entity;

public class ExpressDeliveryDao {
    private String name;
    private String exname;
    private String ico;
    private String url;
    private String phone;

    public ExpressDeliveryDao(String name, String exname, String ico, String url, String phone) {
        this.name = name;
        this.exname = exname;
        this.ico = ico;
        this.url = url;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExname() {
        return exname;
    }

    public void setExname(String exname) {
        this.exname = exname;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
