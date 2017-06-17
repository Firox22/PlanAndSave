package pe.edu.utp.planandsave.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 16/06/2017.
 */
public class DebtsEntity extends BaseEntity{

    public DebtsEntity(Connection connection) {
        super(connection, "debts");
    }

    public DebtsEntity() {
        super();
    }

    List<Debt> findAll(CurrenciesEntity currenciesEntity, ExpenseCategoryEntity expenseCategoryEntity, UsersEntity usersEntity){
        return findByCriteria("", currenciesEntity, expenseCategoryEntity, usersEntity);
    }

    public Debt findById(int id, CurrenciesEntity currenciesEntity, ExpenseCategoryEntity expenseCategoryEntity, UsersEntity usersEntity){
        String criteria = " id = " + id;
        return findByCriteria(criteria, currenciesEntity, expenseCategoryEntity, usersEntity).get(0);
    }

    public Debt findByTotal_amount(float total_amount, CurrenciesEntity currenciesEntity, ExpenseCategoryEntity expenseCategoryEntity, UsersEntity usersEntity) {
        String criteria = " total_amount = '" + total_amount + "'";
        return findByCriteria(criteria, currenciesEntity, expenseCategoryEntity, usersEntity).get(0);
    }

    public Debt findByStart_date(Date start_date, CurrenciesEntity currenciesEntity, ExpenseCategoryEntity expenseCategoryEntity, UsersEntity usersEntity) {
        String criteria = " start_date = '" + start_date + "'";
        return findByCriteria(criteria, currenciesEntity, expenseCategoryEntity, usersEntity).get(0);
    }

    public List<Debt> findByCriteria(String criteria, CurrenciesEntity currenciesEntity, ExpenseCategoryEntity expenseCategoryEntity, UsersEntity usersEntity){
        String sql = getDefaultQuery() + (criteria.isEmpty() ? "" : " WHERE " + criteria);
        List<Debt> debts = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (resultSet == null) return null;
            while (resultSet.next()){
                debts.add(Debt.build(resultSet, currenciesEntity, expenseCategoryEntity, usersEntity));
            }
            return debts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Debt debt){
        String sql = "INSERT INTO debts(id, description, payment_period, payment_time, total_amount, start_date, user_id, category_id, currency_id)" +
                " VALUES(" +
                debt.getIdAsString() + ", " +
                debt.getDescriptionAsValue() + ", " +
                debt.getPayment_periodAsString() + ", " +
                debt.getPayment_timeAsString() + ", " +
                debt.getTotal_amountAsString() + ", " +
                debt.getStart_dateAsString() + ", " +
                debt.getUser().getIdAsString() + ", " +
                debt.getExpenseCategory().getIdAsString() + ", " +
                debt.getCurrency().getIdAsString() + ")";
        return change(sql);
    }
