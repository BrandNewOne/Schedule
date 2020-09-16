package com.example.calpjt1.ui.home.list;

public class ListViewItem {
    private int iconDrawble;
    private String contentStr;
    private String titleStr;

    public void setTitle(String title){
        titleStr=title;
    }
    public void setIcon(int icon){
        iconDrawble=icon;
    }
    public void setContent(String content){
        contentStr=content;
    }

    public int getIcon(){
        return this.iconDrawble;
    }
    public String getContent(){
        return this.contentStr;
    }
    public String getTitle(){
        return this.titleStr;
    }
}
