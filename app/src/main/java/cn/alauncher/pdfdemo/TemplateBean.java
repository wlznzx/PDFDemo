package cn.alauncher.pdfdemo;

import java.util.List;

public class TemplateBean {

    // 标题;
    public List<String> titleList;

    // AQL
    public List<String> AQLList;

    // RoHS
    public List<String> RoHSList;

    public String headerLeft;

    public String headerMid;

    public String headerRight;

    public String footerLeft;

    public String footerMid;

    public String footerRight;

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<String> getAQLList() {
        return AQLList;
    }

    public void setAQLList(List<String> AQLList) {
        this.AQLList = AQLList;
    }

    public List<String> getRoHSList() {
        return RoHSList;
    }

    public void setRoHSList(List<String> roHSList) {
        RoHSList = roHSList;
    }

    public String getHeaderLeft() {
        return headerLeft;
    }

    public void setHeaderLeft(String headerLeft) {
        this.headerLeft = headerLeft;
    }

    public String getHeaderMid() {
        return headerMid;
    }

    public void setHeaderMid(String headerMid) {
        this.headerMid = headerMid;
    }

    public String getHeaderRight() {
        return headerRight;
    }

    public void setHeaderRight(String headerRight) {
        this.headerRight = headerRight;
    }

    public String getFooterLeft() {
        return footerLeft;
    }

    public void setFooterLeft(String footerLeft) {
        this.footerLeft = footerLeft;
    }

    public String getFooterMid() {
        return footerMid;
    }

    public void setFooterMid(String footerMid) {
        this.footerMid = footerMid;
    }

    public String getFooterRight() {
        return footerRight;
    }

    public void setFooterRight(String footerRight) {
        this.footerRight = footerRight;
    }
}
