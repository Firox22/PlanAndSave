package pe.edu.utp.hremployees.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 16/06/2017.
 */
public class ExpensesEntity  extends  BaseEntity {

    public ExpensesEntity() {
        super();
        setTableName("expenses");
    }

    public ExpensesEntity(Connection connection) {
        super(connection, "expenses");
    }

    public List<Expenses> findByCriteria(
            String criteria,
            UserEntity userEntity,
            UserCategory userCategory,
            Currencies currencies) {
        String sql = getDefaultQuery() +
                (criteria.isEmpty() ? "" : " WHERE " + criteria);
        List<Expenses> expenses = new ArrayList<>();
        try {
            ResultSet rs = getConnection()
                    .createStatement()
                    .executeQuery(sql);
            if(rs == null) return null;
            while(rs.next()) {
                expenses.add(
                        Expenses.build(rs, userEntity, userCategory, currencies));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public List<Expenses> findAll(UserEntity userEntity, UserCategory userCategory, Currencies currencies) {
        return findByCriteria("", userEntity, userCategory, currencies);
    }

    public Expenses findById(int id, UserEntity userEntity, UserCategory userCategory, Currencies currencies) {
        try {
            String sql = "expense_id = " + String.valueOf(id);
            return findByCriteria(sql, userEntity, userCategory, currencies ).get(0);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
