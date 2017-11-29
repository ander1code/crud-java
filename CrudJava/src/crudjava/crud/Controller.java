package crudjava.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final PhysicalPerson pp;
    private final Connection conn;

    public Controller(PhysicalPerson pp) {
        this.pp = pp;
        this.conn = ConnectionClass.Connect();
    }

    private int GenerateID() {
        try {
            int id = 0;
            try (Statement stmt = this.conn.createStatement()) {
                String sql = "SELECT MAX(ID) + 1 FROM PERSON";
                ResultSet result;
                result = stmt.executeQuery(sql);
                if (result.getInt(1) > 0) {
                    id = result.getInt(1);
                } else {
                    id = 1;
                }
            }
            return id;
        } catch (SQLException e) {
            return -1;
        }
    }

    private int InsertPerson(int id, String name, String email) {
        try {
            String sql = "INSERT INTO PERSON VALUES(?, ?, ?)";
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, email);
                stmt.execute();
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    private int EditPerson(int id, String name, String email) {
        try {
            String sql = "UPDATE PERSON SET NAME = ?, EMAIL = ? WHERE ID = ?";
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                stmt.setInt(3, id);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.execute();
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    private int DeletePerson(int id) {
        try {
            String sql = "DELETE FROM PERSON WHERE ID = ?";
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.execute();
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    private int InsertPhysicalPerson() {

        try {
            String sql = "INSERT INTO PHYSICALPERSON VALUES (?,?,?,?,?)";
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                stmt.setInt(1, this.pp.getId());
                stmt.setInt(2, this.pp.getId());
                stmt.setFloat(3, this.pp.getSalary());
                stmt.setDate(4, this.pp.getBirthday());
                stmt.setString(5, this.pp.getGender());
                stmt.execute();
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    private int EditPhysicalPerson() {

        try {
            String sql = "UPDATE PHYSICALPERSON SET SALARY = ?, BIRTHDAY = ?, GENDER  = ? WHERE ID = ?";
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                stmt.setFloat(1, this.pp.getSalary());
                stmt.setDate(2, this.pp.getBirthday());
                stmt.setString(3, this.pp.getGender());
                stmt.setInt(4, this.pp.getId());
                stmt.execute();
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    private int DeletePhysicalPerson() {

        try {
            String sql = "DELETE FROM PHYSICALPERSON WHERE ID = ?";
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                stmt.setInt(1, this.pp.getId());
                stmt.execute();
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    public int Insert_PhysicalPerson() {

        try {
            int id = this.GenerateID();
            this.pp.setId(id);
            if (id > 0) {
                if (this.InsertPerson(this.pp.getId(), this.pp.getName(), this.pp.getEmail()) == 1) {
                    if (this.InsertPhysicalPerson() == 1) {
                        this.conn.commit();
                        this.conn.close();
                        return 1;
                    } else {
                        this.conn.rollback();
                        this.conn.close();
                        return -1;
                    }
                } else {
                    this.conn.rollback();
                    this.conn.close();
                    return -1;
                }
            } else {
                this.conn.close();
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    public int Edit_PhysicalPerson() {

        try {
            if (this.EditPerson(this.pp.getId(), this.pp.getName(), this.pp.getEmail()) == 1) {
                if (this.EditPhysicalPerson() == 1) {
                    this.conn.commit();
                    this.conn.close();
                    return 1;
                } else {
                    this.conn.rollback();
                    this.conn.close();
                    return -1;
                }
            } else {
                this.conn.rollback();
                this.conn.close();
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    public int Delete_PhysicalPerson() {

        try {
            if (this.DeletePhysicalPerson() == 1) {
                if (this.DeletePerson(this.pp.getId()) == 1) {
                    this.conn.commit();
                    this.conn.close();
                    return 1;
                } else {
                    this.conn.rollback();
                    this.conn.close();
                    return -1;
                }
            } else {
                this.conn.rollback();
                this.conn.close();
                return -1;
            }

        } catch (SQLException e) {
            return -1;
        }
    }

    public PhysicalPerson GetPhysicalPersonByID() {
        try {
            String sql = "SELECT * FROM PERSON INNER JOIN PHYSICALPERSON ON PERSON.ID = PHYSICALPERSON.PERSON_ID WHERE PERSON.ID = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, this.pp.getId());
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                this.pp.setName(res.getString(2));
                this.pp.setEmail(res.getString(3));
                this.pp.setSalary(res.getFloat(6));
                this.pp.setBirthday(res.getDate(7));
                this.pp.setGender(res.getString(8));
                stmt.close();
                this.conn.close();
                return this.pp;
            }
            stmt.close();
            this.conn.close();
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<PhysicalPerson> GetPhysicalPersonByName() {
        try {
            String sql = "SELECT * FROM PERSON INNER JOIN PHYSICALPERSON ON PERSON.ID = PHYSICALPERSON.PERSON_ID WHERE PERSON.NAME LIKE ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, this.pp.getName() + '%');
            ResultSet res = stmt.executeQuery();
            List<PhysicalPerson> list = new ArrayList<PhysicalPerson>();
            while (res.next()) {
                list.add(new PhysicalPerson(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(6), res.getDate(7), res.getString(8)));
            }
            stmt.close();
            this.conn.close();
            return list;
        } catch (SQLException e) {
            return null;
        }

    }

    public boolean CheckEmailRegistered() {
        try {
            String sql = "SELECT * FROM PERSON INNER JOIN PHYSICALPERSON ON PERSON.ID = PHYSICALPERSON.PERSON_ID WHERE PERSON.EMAIL = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, this.pp.getEmail());
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return true;
            }
            stmt.close();
            this.conn.close();
            return false;
        } catch (SQLException e) {
            return false;
        }
    }
}
