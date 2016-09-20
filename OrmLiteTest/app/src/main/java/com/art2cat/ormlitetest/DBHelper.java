package com.art2cat.ormlitetest;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by art2c on 2016/6/27.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名字
     */
    private static final String DB_NAME = "ormlite.db";

    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 2;

    /**
     * 用来存放Dao的Map集合，之所以创建Map集合，是为了方便对数据库资源的管理
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    /**
     * 数据库连接对象
     */
    private AndroidConnectionSource connectionSource;

    /**
     * 单例对象
     */
    private  static DBHelper instance;

    /**
     * <p>Title: DBHelper </>
     * <p>Description: 构造方法</p>
     * @param context 上下文资源
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 使用单例模式获取数据库对象
     */
    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 通过类名获得指定的Dao,并将取得的Dao放入Map集合
     */
    public synchronized Dao getDao(Class cls) throws SQLException {
        Dao dao = null;
        //获得类名
        String className = cls.getSimpleName();
        //判断类名是否为空
        if (!className.equals("") || !className.equals(null)) {
            //如果不为空则获取Dao对象
            dao = super.getDao(cls);
            //将Dao对象放入Map集合
            daos.put(className, dao);
        } else {
            return null;
        }
        return dao;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //创建操作
        try {
            //创建用户表
            TableUtils.createTable(connectionSource, Users.class);
            //创建文章表
            TableUtils.createTable(connectionSource, Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource,
                          int i, int i1) {
        //更新操作
        try {
            //更新用户表
            TableUtils.dropTable(connectionSource, Users.class, true);
            //更新文章表
            TableUtils.dropTable(connectionSource, Article.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            //释放Dao资源
            dao = null;
        }
    }
}
