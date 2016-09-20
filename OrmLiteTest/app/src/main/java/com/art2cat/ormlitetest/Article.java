package com.art2cat.ormlitetest;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by art2c on 6/28/2016.
 */
@DatabaseTable(tableName = "orm_article")
public class Article {
    // 配置字段名为id，并设为主键
    @DatabaseField(columnName="id",generatedId=true)
    private int id;
    // 配置字段名为name，并且该字段不能为空
    @DatabaseField(columnName="uid",canBeNull = false)
    private int uid;
    // 使用本身变量名为字段名
    @DatabaseField
    private String title;
    // 使用本身变量名为字段名
    @DatabaseField
    private String content;
    // 使用本身变量名为字段名
    @DatabaseField
    private String date;

    // 外部对象字段，并指定这个字段的对象是一个外键，外键值是这个对象的id
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Users mUsers;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the mUsers
     */
    public Users getmUsers() {
        return mUsers;
    }

    /**
     * @param mUsers the mUsers to set
     */
    public void setmUsers(Users mUsers) {
        this.mUsers = mUsers;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Article [id=" + id + ", uid=" + uid + ", title=" + title
                + ", content=" + content + ", date=" + date + "]";
    }
}
