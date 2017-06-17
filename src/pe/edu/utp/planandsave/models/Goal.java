package pe.edu.utp.planandsave.models;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by usuario on 16/06/2017.
 */
public class Goal {
    private int id;
    private String name;
    private float progress;
    private String image;
    private float cost;
    private User user;
    private Currency currency;

    public int getId() {
        return id;
    }

    public String getIdAsString(){
        return String.valueOf(getId());
    }

    public Goal setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getNameAsValue(){
        return "'" + getName() + "'";
    }

    public Goal setName(String name) {
        this.name = name;
        return this;
    }

    public float getProgress() {
        return progress;
    }

    public String getProgressAsString(){
        return String.valueOf(getProgress());
    }

    public Goal setProgress(float progress) {
        this.progress = progress;
        return this;
    }

    public String getImage() {
        return image;
    }

    public String getImageAsValue(){
        return "'" + getImage() + "'";
    }

    public Goal setImage(String image) {
        this.image = image;
        return this;
    }

    public float getCost() {
        return cost;
    }

    public String getCostAsString(){
        return String.valueOf(getCost());
    }

    public Goal setCost(float cost) {
        this.cost = cost;
        return this;
    }

    public User getUser() {
        return user;
    }


    public Goal setUser(User user) {
        this.user = user;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }


    public Goal setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public static Goal build(ResultSet resultSet, UsersEntity usersEntity, CurrenciesEntity currenciesEntity){
        try {
            return (new Goal())
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setProgress(resultSet.getFloat("progress"))
                    .setImage(resultSet.getString("image"))
                    .setCost(resultSet.getString("cost"))
                    .setUser(usersEntity.findById(resultSet.getInt("user_id")))
                    .setCurrency(currenciesEntity.findById(resultSet.getInt("currency_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
