package crudjava.crud;

import java.sql.Date;

public class PhysicalPerson implements IPerson {

    private int id;
    private String name;
    private String email;
    private float salary;
    private Date birthday;
    private String gender;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PhysicalPerson(int id, String name, String email, float salary, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.birthday = birthday;
        this.gender = gender;
    }

    public PhysicalPerson() {
    }
}
