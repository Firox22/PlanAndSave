package pe.edu.utp.planandsave.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by usuario on 16/06/2017.
 */
public class Currency {
    private int id;
    private String name;
    private float exchange_rate;

    public int getId() {
        return id;
    }

    public String getIdAsString(){
        return String.valueOf(getId());
    }

    public Currency setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getNameAsValue(){
        return "'" + getName() + "'";
    }

    public Currency setName(String name) {
        this.name = name;
        return this;
    }

    public float getExchange_rate() {
        return exchange_rate;
    }

    public String getExchange_rateAsString(){
        return String.valueOf(getExchange_rate());
    }

    public Currency setPrice(float exchange_rate) {
        this.exchange_rate = exchange_rate;
        return this;
    }

    public static Currency build(ResultSet resultSet){
        try {
            return (new Currency())
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setPrice(resultSet.getFloat("exchange_rate"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
}
