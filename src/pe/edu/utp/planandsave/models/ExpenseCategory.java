package pe.edu.utp.planandsave.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by usuario on 16/06/2017.
 */
public class ExpenseCategory {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getIdAsString(){
        return String.valueOf(getId());
    }

    public ExpenseCategory setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getNameAsValue(){
        return "'" + getName() + "'";
    }

    public ExpenseCategory setName(String name) {
        this.name = name;
        return this;
    }

    public static ExpenseCategory build(ResultSet resultSet){
        try {
            return (new ExpenseCategory())
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
