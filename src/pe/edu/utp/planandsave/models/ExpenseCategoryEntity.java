package pe.edu.utp.planandsave.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abraham on 17/06/2017.
 */
public class ExpenseCategoryEntity extends BaseEntity {

    public ExpenseCategoryEntity(Connection connection) {
        super(connection, "expense_category");
    }

    public ExpenseCategoryEntity() {
    }

    List<ExpenseCategory> findAll(){
        return findByCriteria("");
    }

    public ExpenseCategory findById(int id){
        String criteria = " id = " + String.valueOf(id);
        return findByCriteria(criteria).get(0);
    }

    public ExpenseCategory findByName(String name){
        String criteria = " name = '" + name + "'";
        return findByCriteria(criteria).get(0);
    }

    public List<ExpenseCategory> findByCriteria(String criteria){
        String sql = getDefaultQuery() + criteria == "" ? "" : " WHERE " + criteria;
        List<ExpenseCategory> expenseCategories = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (resultSet == null) return null;
            while (resultSet.next()){
                expenseCategories.add(ExpenseCategory.build(resultSet));
            }
            return expenseCategories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
