package reflectTest2.dao;

import reflectTest2.bean.ManBean;

import java.time.LocalDate;
import java.util.List;

public class ManDaoImpl extends BaseDao implements IManDao{
    @Override
    public void add(ManBean man) {

        this.updateData("INSERT INTO t_man(m_name, m_sex, m_birth, m_money) VALUES(?,?,?,?)",
                man.getName(), man.getSex(), man.getBirth(), man.getMoney());
    }

    @Override
    public void del(int id) {

        this.updateData("DELETE FROM t_man WHERE pk_manId = ?",id);
    }

    @Override
    public void update(int id, int money) {

        this.updateData("update t_man set m_money = ? where pk_manId = ?",money,id);
    }

    @Override
    public List<ManBean> findAll() {
        //这里的别名必须和实体Bean中的属性名相同
        return this.selectData(ManBean.class,"select pk_manId id, m_name name, m_sex sex, m_birth birth, m_money money from t_man");
//        return this.selectData(ManBean.class,"select pk_manId id,  m_birth birth, m_money money from t_man");
    }

    @Override
    public List<ManBean> findByName(String name) {
        return this.selectData(ManBean.class,"select pk_manId id, m_name name, m_sex sex, m_birth birth, m_money money from t_man where m_name like ?", "%"+name+"%");
    }

    @Override
    public List<ManBean> findByBirth(LocalDate startDate, LocalDate endDate) {
        return this.selectData(ManBean.class,"select pk_manId id, m_name name, m_sex sex, m_birth birth, m_money money from t_man where m_birth> ? and m_birth < ?", startDate, endDate);
    }

    @Override
    public List<ManBean> findById(int id) {
        return this.selectData(ManBean.class,"select pk_manId id, m_name name, m_sex sex, m_birth birth, m_money money from t_man where pk_manId = ?", id);
    }

    public static void main(String[] args) {
        IManDao dao = new ManDaoImpl();
//        dao.add(new ManBean("赵大夫","男",LocalDate.parse("1999-06-14"),5252));
//        dao.del(6);
//        dao.update(5,58588);

        System.out.println(dao.findAll());
//        System.out.println(dao.findByName("张"));
//        System.out.println(dao.findById(3));
    }
}
