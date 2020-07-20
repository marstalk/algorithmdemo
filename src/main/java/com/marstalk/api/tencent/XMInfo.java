package com.marstalk.api.tencent;

public class XMInfo {
    private String name;
    private String addr;

    private String area1;
    private String area2;
    private String area3;
    private String funcArea;
    private String AreaGroup;
    private String commitee;

    public XMInfo() {
    }

    public XMInfo(String name, String addr, String area1, String area2, String area3, String funcArea, String areaGroup, String commitee) {
        this.name = name;
        this.addr = addr;
        this.area1 = area1;
        this.area2 = area2;
        this.area3 = area3;
        this.funcArea = funcArea;
        AreaGroup = areaGroup;
        this.commitee = commitee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getArea3() {
        return area3;
    }

    public void setArea3(String area3) {
        this.area3 = area3;
    }

    public String getFuncArea() {
        return funcArea;
    }

    public void setFuncArea(String funcArea) {
        this.funcArea = funcArea;
    }

    public String getAreaGroup() {
        return AreaGroup;
    }

    public void setAreaGroup(String areaGroup) {
        AreaGroup = areaGroup;
    }

    public String getCommitee() {
        return commitee;
    }

    public void setCommitee(String commitee) {
        this.commitee = commitee;
    }
}
