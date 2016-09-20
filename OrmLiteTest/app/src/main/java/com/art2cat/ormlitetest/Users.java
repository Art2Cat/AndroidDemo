package com.art2cat.ormlitetest;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by art2c on 6/28/2016.
 */

// 配置该User实体类对应的表名为orm_user，如果不加tableName="orm_user"属性，则表名为类名
@DatabaseTable(tableName = "orm_user")
public class Users {
    // 配置字段名为id，并设为主键
    @DatabaseField(columnName="id",generatedId=true)
    private int id;
    // 配置字段名为name，并且该字段不能为空
    @DatabaseField(columnName="name",canBeNull = false)
    private String name;
    // 使用本身变量名为字段名
    @DatabaseField
    private int age;
    // 使用本身变量名为字段名，并且设置默认值
    @DatabaseField(defaultValue="这个人很懒，什么也没说")
    private String desc;
    // 一个用户对应多篇文章，eager = true表示可以进行懒加载
    // 注：这里是一对多的关系，如果是1对1，我们要用@DatabaseField注解，还要指定(foreign = true)表示是一个外键
    @ForeignCollectionField(eager = true)
    public ForeignCollection<Article> articles;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the articles
     */
    public ForeignCollection<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(ForeignCollection<Article> articles) {
        this.articles = articles;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + ", age=" + age + ", desc="
                + desc + "]";
    }
}
