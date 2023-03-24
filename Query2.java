import java.sql.Date;

//Datatype that which recieves data from JDBC for query 2
public class Query2{
    public String manager_name;
    public String duration;


    public Query2() {
    }


    public Query2(String var1, String var2) {
        this.manager_name = var1;
        this.duration = var2;

    }
}