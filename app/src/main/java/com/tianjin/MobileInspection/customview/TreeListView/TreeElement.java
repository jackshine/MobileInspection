package com.tianjin.MobileInspection.customview.TreeListView;
//javaapk.com提供测试
/**
 * 类名：TreeElement.java
 * 类描述：树形结构节点类
 * @author wader
 * 创建时间：2011-11-03 16:32
 */
public class TreeElement {
    String id = null;// 当前节点id
    String title = null;// 当前节点文字
    boolean hasParent = false;// 当前节点是否有父节点
    boolean hasChild = false;// 当前节点是否有子节点
    boolean childShow = false;// 如果子节点，字节当当前是否已显示
    String parentId = null;// 父节点id
    int level = -1;// 当前界面层级
    boolean fold = false;// 是否处于展开状态

    String content;//节点内容
    boolean chosed=false;

    public boolean isChosed() {
        return chosed;
    }

    public void setChosed(boolean chosed) {
        this.chosed = chosed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChildShow() {
        return childShow;
    }

    public void setChildShow(boolean childShow) {
        this.childShow = childShow;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isFold() {
        return fold;
    }

    public void setFold(boolean fold) {
        this.fold = fold;
    }

    @Override
    public String toString() {
        return "id:" + this.id + "-level:" + this.level + "-title:"
                + this.title + "-fold:" + this.fold + "-hasChidl:"
                + this.hasChild + "-hasParent:" + this.hasParent + "-parentId:"+ this.parentId;
    }
}