package pe.edu.utp.hremployees.models;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by usuario on 16/06/2017.
 */
public class Expenses {
    private int id;
    private double amount;
    private Date registration_date;
    private String description;
    private UserEntity user;
    private UserCategory category;
    private Currencies currency;

    public Expenses() {
    }

    public Expenses(int id, double amount, Date registration_date, String description, UserEntity user, UserCategory category,
                  Currencies currency) {
        this.id = id;
        this.amount = amount;
        this.registration_date = registration_date;
        this.description = description;
        this.user = user;
        this.category = category;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public Expenses setId(int id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Expenses setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public Expenses setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Expenses setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public Expenses setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public UserCategory getUserCategory() {
        return category;
    }

    public Expenses setUserCategory(UserCategory category) {
        this.category = category;
        return this;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public Expenses setUserCategory(Currencies currency) {
        this.currency = currency;
        return this;
    }

    public static Expenses build(ResultSet resultSet) {
        try {
            return (new Expenses())
                    .setId(resultSet.getInt("expense_id"))
                    .setAmount(resultSet.getDouble("expense_amount"))
                    .setAmount(resultSet.getDouble("expense_amount"))
                    .setDescription(resultSet.getString("expense_descripcion"))
                    .setUserEntity(UserEntity.findById(resultSet.getString("user_id")))
                    .setUserCategory(UserCategory.findById(resultSet.getString("category_id")))
                    .setCurrencies(Currencies.findById(resultSet.getString("currency_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
