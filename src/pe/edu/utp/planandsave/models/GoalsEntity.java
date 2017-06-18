package pe.edu.utp.planandsave.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 16/06/2017.
 */
public class GoalsEntity extends BaseEntity{

    public GoalsEntity(Connection connection) {
        super(connection, "goals");
    }

    public GoalsEntity() {
        super();
    }

    List<Goal> findAll(UsersEntity usersEntity, CurrenciesEntity currenciesEntity){
        return findByCriteria("", usersEntity, currenciesEntity);
    }

    public Goal findById(int id, UsersEntity usersEntity, CurrenciesEntity currenciesEntity){
        String criteria = " id = " + id;
        return findByCriteria(criteria, usersEntity, currenciesEntity).get(0);
    }

    public Goal findByName(String name, UsersEntity usersEntity, CurrenciesEntity currenciesEntity){
        String criteria = " name = '" + name + "'";
        return findByCriteria(criteria, usersEntity, currenciesEntity).get(0);
    }

    public Goal findByCost(String cost, UsersEntity usersEntity, CurrenciesEntity currenciesEntity){
        String criteria = " cost = '" + cost + "'";
        return findByCriteria(criteria, usersEntity, currenciesEntity).get(0);
    }

    public List<Goal> findByCriteria(String criteria, UsersEntity usersEntity, CurrenciesEntity currenciesEntity){
        String sql = getDefaultQuery() + (criteria.isEmpty() ? "" : " WHERE " + criteria);
        List<Goal> goals = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (resultSet == null) return null;
            while (resultSet.next()){
                goals.add(Goal.build(resultSet, usersEntity, currenciesEntity));
            }
            return goals;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Goal goal){
        String sql = "INSERT INTO goals(id, name, progress, image, cost, user_id, currency_id)" +
                " VALUES(" +
                goal.getIdAsString() + ", " +
                goal.getNameAsValue() + ", " +
                goal.getProgressAsString() + ", " +
                goal.getImageAsValue() + ", " +
                goal.getCostAsString() + ", " +
                goal.getUser().getIdAsString() + ", " +
                goal.getCurrency().getIdAsString() + ")";
        return change(sql);
    }
