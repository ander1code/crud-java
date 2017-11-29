package crudjava.crud;

import java.sql.Date;
import java.util.List;

public class Crud {

    public static int Insert_PhysicalPerson(String name, String email, float salary, Date birthday, String gender) {

        PhysicalPerson pp = new PhysicalPerson();
        pp.setName(name);
        pp.setEmail(email);
        pp.setSalary(salary);
        pp.setBirthday(birthday);
        pp.setGender(gender);
        Controller ctr = new Controller(pp);
        if (ctr.Insert_PhysicalPerson() == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public static int Edit_PhysicalPerson(int id, String name, String email, float salary, Date birthday, String gender) {

        PhysicalPerson pp = new PhysicalPerson();
        pp.setId(id);
        pp.setName(name);
        pp.setEmail(email);
        pp.setSalary(salary);
        pp.setBirthday(birthday);
        pp.setGender(gender);
        Controller ctr = new Controller(pp);
        if (ctr.Edit_PhysicalPerson() == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public static int Delete_PhysicalPerson(int id) {

        PhysicalPerson pp = new PhysicalPerson();
        pp.setId(id);
        Controller ctr = new Controller(pp);
        if (ctr.Delete_PhysicalPerson() == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public static PhysicalPerson GetPhysicalPersonByID(int id) {

        PhysicalPerson pp = new PhysicalPerson();
        pp.setId(id);
        Controller ctr = new Controller(pp);
        return ctr.GetPhysicalPersonByID();
    }

    public static List<PhysicalPerson> GetPhysicalPersonByName(String name) {

        PhysicalPerson pp = new PhysicalPerson();
        pp.setName(name);
        Controller ctr = new Controller(pp);
        return ctr.GetPhysicalPersonByName();
    }
    
    public static boolean CheckEmailRegistered(String email) {

        PhysicalPerson pp = new PhysicalPerson();
        pp.setEmail(email);
        Controller ctr = new Controller(pp);
        return ctr.CheckEmailRegistered();
    }
}
