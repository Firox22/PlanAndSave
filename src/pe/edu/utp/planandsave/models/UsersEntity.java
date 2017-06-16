package pe.edu.utp.planandsave.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 16/06/2017.
 */
public class UsersEntity extends BaseEntity{

    public UsersEntity(Connection connection) {
        super(connection, "users");
    }

    public UsersEntity() {
        super();
    }

    List<User> findAll(UserCategoryEntity userCategoryEntity){
        return findByCriteria("", userCategoryEntity);
    }

    public User findById(int id, UserCategoryEntity userCategoryEntity){
        String criteria = " id = " + id;
        return findByCriteria(criteria, userCategoryEntity).get(0);
    }

    public User findByFirstName(String firstName, UserCategoryEntity userCategoryEntity){
        String criteria = " first_name = '" + firstName + "'";
        return findByCriteria(criteria, userCategoryEntity).get(0);
    }

    public User findByLastName(String lastName, UserCategoryEntity userCategoryEntity){
        String criteria = " last_name = '" + lastName + "'";
        return findByCriteria(criteria, userCategoryEntity).get(0);
    }

    public User findByEmail(String email, UserCategoryEntity userCategoryEntity){
        String criteria = " email = '" + email + "'";
        return findByCriteria(criteria, userCategoryEntity).get(0);
    }

    public List<User> findByCriteria(String criteria, UserCategoryEntity userCategoryEntity){
        String sql = getDefaultQuery() + (criteria.isEmpty() ? "" : " WHERE " + criteria);
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (resultSet == null) return null;
            while (resultSet.next()){
                users.add(User.build(resultSet, userCategoryEntity));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(User user){
        String sql = "INSERT INTO users(id, first_name, last_name, email, password, salary, user_category_id)" +
                " VALUES(" +
                    user.getIdAsString() + ", " +
                    user.getFirstNameAsValue() + ", " +
                    user.getLastNameAsValue() + ", " +
                    user.getEmailAsValue() + ", " +
                    user.getPasswordAsValue() + ", " +
                    user.getSalaryAsString() + ", " +
                    user.getUserCategory().getIdAsString() + ")";
        return change(sql);
    }

    /*
    public boolean add(User user){
        String sql = "INSERT INTO users(id, first_name, last_name, email, password, salary, user_category_id)" +
                " VALUES(" +
                    user.getIdAsString() + ", " +
                    user.getFirstNameAsValue() + ", " +
                    user.getLastNameAsValue() + ", " +
                    user.getEmailAsValue() + ", " +
                    user.getPasswordAsValue() + ", " +
                    user.getSalaryAsString() + ", " +
                    user.getUserCategory().getIdAsString() + ")";
        return change(sql);
    }
    */
}
