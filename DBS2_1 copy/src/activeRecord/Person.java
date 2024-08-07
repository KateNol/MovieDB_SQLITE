package activeRecord;

import util.DBConnect;
import util.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {
    private Long personID;
    private String name;
    private char sex;

    public Long getPersonID() {
        return personID;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public char getSex() {
        return sex;
    }

    public void insert() throws SQLException {
        if (personID != null) {
            Logger.log("Object already persistent");
            return;
        }

        String sql = "INSERT INTO Person (Name, Sex) VALUES (?, ?);";

        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, sex + "");
        statement.executeUpdate();
        statement.close();

        sql = "SELECT last_insert_rowid() AS id";
        statement = DBConnect.getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        rs.next();
        personID = rs.getLong(1);
        statement.close();
        DBConnect.commit();
    }
}
