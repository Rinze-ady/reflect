package reflectTest2.bean;

import java.time.LocalDate;

public class ManBean {
    private int id;
    private String name;
    private String sex;
    private LocalDate birth;
    private int money;

    public ManBean() {
    }

    public ManBean(String name, String sex, LocalDate birth, int money) {
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.money = money;
    }

    @Override
    public String toString() {
        return "ManBean{" +
                "manId=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", money=" + money +
                '}' + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
