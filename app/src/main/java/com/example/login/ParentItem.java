package com.example.login;

import java.util.List;

public class ParentItem {
    // Declaration of the variables
    private String ParentItemTitle;
    private String ParentCalorie;
    private List<ChildItem> ChildItemList;

    // Constructor of the class
    // to initialize the variables
    public ParentItem(
            String ParentItemTitle,
            String ParentCalorie,
            List<ChildItem> ChildItemList)
    {

        this.ParentItemTitle = ParentItemTitle;
        this.ParentCalorie = ParentCalorie;
        this.ChildItemList = ChildItemList;
    }

    // Getter and Setter methods
    // for each parameter
    public String getParentItemTitle()
    {
        return ParentItemTitle;
    }

    public void setParentItemTitle(
            String parentItemTitle)
    {
        ParentItemTitle = parentItemTitle;
    }

    public String getParentCalorie()
    {
        return ParentCalorie;
    }

    public void setParentCalorie(
            String parentCalorie)
    {
        ParentCalorie = parentCalorie;
    }

    public List<ChildItem> getChildItemList()
    {
        return ChildItemList;
    }

    public void setChildItemList(
            List<ChildItem> childItemList)
    {
        ChildItemList = childItemList;
    }
}
