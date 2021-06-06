package reflectTest2.dao;

import reflectTest2.bean.ManBean;

import java.time.LocalDate;
import java.util.List;

public interface IManDao {
    /**
     * 添加
     * @param man
     */
    public void add(ManBean man);


    /**
     * 按编号删除
     * @param id
     */
    public void del(int id);


    /**
     * 按编号修改工资
     * @param id
     * @param money
     */
    public void update(int id, int money);


    /**
     * 查询所有人
     * @return
     */
    public List<ManBean> findAll();

    /**
     * 按姓名查询
     * @param name
     * @return
     */
    public List<ManBean> findByName(String name);

    /**
     * 按生日查询
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ManBean> findByBirth(LocalDate startDate, LocalDate endDate);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public List<ManBean> findById(int id);

}
