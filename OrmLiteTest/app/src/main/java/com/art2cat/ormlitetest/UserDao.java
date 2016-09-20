package com.art2cat.ormlitetest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import com.j256.ormlite.dao.Dao;



/**
 * Created by art2c on 6/28/2016.
 */
public class UserDao {
    private Dao<Users, Integer> userDao;
    private DBHelper dbHelper;

    /**
     *
     * <p>Title: UserDao</p>
     * <p>Description: 构造方法</p>
     * @param context  上下午资源对象
     */
    public UserDao(Context context) {
        try {
            // 使用单例模式
            dbHelper = DBHelper.getHelper(context);
            userDao = dbHelper.getDao(Users.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: add
     * @Description: TODO(添加用户)
     * @param  users
     * @return void
     */
    public void add(Users users) {
        try {
            userDao.create(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: delete
     * @Description: TODO(根据ID删除单条记录)
     * @param  id
     * @return void
     */
    public void delete(int id) {
        try {
            userDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Title: update
     * @Description: TODO(更新单条记录)
     * @param  users
     * @return void
     */
    public void update(Users users) {
        try {
            userDao.createOrUpdate(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: queryForId
     * @Description: TODO(根据ID查询单条记录)
     * @param  id
     * @return Users
     */
    public Users queryForId(int id) {
        Users users = null;
        try {
            users = userDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * @Title: queryForAll
     * @Description: TODO(查询所有记录)
     * @return List<Users>
     */
    public List<Users> queryForAll() {
        List<Users> mUserses = new ArrayList<Users>();
        try {
            mUserses = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mUserses;
    }
}
