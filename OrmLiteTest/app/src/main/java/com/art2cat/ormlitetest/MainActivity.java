package com.art2cat.ormlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_show;      // 控制台显示文本
    private Button btn_add;          // 新增
    private Button btn_query;          // 查询
    private Button btn_update;          // 更新
    private Button btn_delete;          // 删除
    private Users users;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        userDao = new UserDao(this);

        // 实例化控件
        init();

        // 设置监听事件
        setListener();
    }

    /**
     * @Title: init
     * @Description: TODO(实例化控件)
     * @param
     * @return void
     */
    private void init() {
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
    }

    /**
     * @Title: setListener
     * @Description: TODO(设置监听事件)
     * @param
     * @return void
     */
    private void setListener() {
        btn_add.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 新增操作
            case R.id.btn_add:
                add();
                break;
            // 查询操作
            case R.id.btn_query:
                query();
                break;
            // 更新操作
            case R.id.btn_update:
                update();
                break;
            // 删除操作
            case R.id.btn_delete:
                delete();
                break;

            default:
                break;
        }

    }

    /**
     * @Title: add
     * @Description: TODO(添加数据)
     * @param
     * @return void
     */
    private void add() {
        for (int i = 1; i <= 10; i++) {
            users = new Users();
            String name = "Test_" + i;
            Log.i("TAG", name);
            users.setName(name);   // 设置姓名
            users.setAge(10 + i);  // 设置年龄
            userDao.add(users);

        }
        tv_show.setText("新增了" + userDao.queryForAll().size() + "位用户\n第1位用户名为：" +
                userDao.queryForAll().get(0).getName() +
                "\n" + "第" + userDao.queryForAll().size() + "位用户名为：" +
                "" + userDao.queryForAll().get(userDao.queryForAll().size() - 1).getName());
    }

    /**
     * @Title: query
     * @Description: TODO(查询数据)
     * @param
     * @return void
     */
    private void query() {
        users = userDao.queryForId(5);
        tv_show.setText("第5位用户ID："+ users.getId()+"\n"
                +"第5位用户姓名："+ users.getName()+"\n"
                +"第5位用户年龄："+ users.getAge()+"\n"
                +"第5位用户描述："+ users.getDesc());
    }

    /**
     * @Title: update
     * @Description: TODO(更新操作)
     * @param
     * @return void
     */
    private void update() {
        users = new Users();
        users.setId(5);
        users.setName("我是第5位用户");
        users.setAge(100);
        users.setDesc("啦啦啦，我是卖报的小行家");
        userDao.update(users);

        users = userDao.queryForId(5);
        tv_show.setText("第5位用户ID："+ users.getId()+"\n"
                +"第5位用户姓名："+ users.getName()+"\n"
                +"第5位用户年龄："+ users.getAge()+"\n"
                +"第5位用户描述："+ users.getDesc());
    }

    /**
     * @Title: delete
     * @Description: TODO(删除操作)
     * @param
     * @return void
     */
    private void delete() {

        userDao.delete(1);
        tv_show.setText("数据库还有"+userDao.queryForAll().size()+"位用户");
    }
}
