package pe.edu.utp.planandsave.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 16/06/2017.
 */
public class UserCategoryEntity extends BaseEntity {

    public UserCategoryEntity(Connection connection) {
        super(connection, "user_category");
    }

    public UserCategoryEntity() {
    }

    List<UserCategory> findAll(){
        return findByCriteria("");
    }

    public UserCategory findById(int id){
        String criteria = " id = " + String.valueOf(id);
        return findByCriteria(criteria).get(0);
    }

    public UserCategory findByName(String name){
        String criteria = " name = '" + name + "'";
        return findByCriteria(criteria).get(0);
    }

    public List<UserCategory> findByCriteria(String criteria){
        String sql = getDefaultQuery() + criteria == "" ? "" : " WHERE " + criteria;
        List<UserCategory> userCategories = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (resultSet == null) return null;
            while (resultSet.next()){
                userCategories.add(UserCategory.build(resultSet));
            }
            return userCategories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
