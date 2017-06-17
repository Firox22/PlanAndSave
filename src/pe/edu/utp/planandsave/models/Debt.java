package pe.edu.utp.planandsave.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by usuario on 16/06/2017.
 */
public class Debt {
    private int id;
    private String description;
    private int payment_period;
    private Date payment_time;
    private float total_amount;
    private Date start_date;
    private User user;
    private ExpenseCategory expenseCategory;
    private Currency currency;

    public int getId() {
        return id;
    }

    public String getIdAsString(){
        return String.valueOf(getId());
    }

    public Debt setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAsValue(){
        return "'" + getDescription() + "'";
    }

    public Debt setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPayment_period() { return payment_period; }

    public String getPayment_periodAsString(){
        return String.valueOf(getPayment_period());
    }

    public Debt setPayment_period(int payment_period) {
        this.payment_period = payment_period;
        return this;
    }

    public Date getPayment_time() { return payment_time; }

    public String getPayment_timeAsString(){ return String.valueOf(getPayment_time()); }

    public Debt setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
        return this;
    }
    public float getTotal_amount() { return total_amount; }

    public String getTotal_amountAsString(){
        return String.valueOf(getTotal_amount());
    }

    public Debt setTotal_amount(float total_amount) {
        this.total_amount = total_amouny;
        return this;
    }

    public Date getStart_date() { return start_date; }

    public String getStart_dateAsString(){ return String.valueOf(getStart_date()); }

    public Debt setStart_date(Date start_date) {
        this.start_date = start_date;
        return this;
    }

    public User getUser() {
        return user;
    }


    public Debt setUser(User user) {
        this.user = user;
        return this;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }


    public Debt setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Debt setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public static Debt build(ResultSet resultSet, CurrenciesEntity currenciesEntity, ExpenseCategoryEntity expenseCategoryEntity, UsersEntity usersEntity){
        try {
            return (new User())
                    .setId(resultSet.getInt("id"))
                    .setDescription(resultSet.getString("description"))
                    .setPayment_period(resultSet.getString("payment_period"))
                    .setPayment_time(resultSet.getString("payment_time"))
                    .setTotal_amount(resultSet.getFloat("total_amount"))
                    .setStart_date(resultSet.getString("start_date"))
                    .setUser(usersEntity.findById(resultSet.getInt("user_id")))
                    .setExpenseCategory(expenseCategoryEntity.findById(resultSet.getInt("category_id")))
                    .setCurrency(currenciesEntity.findById(resultSet.getInt("currency_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

