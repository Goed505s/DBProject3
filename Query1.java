import java.sql.Date;

//Datatype that which recieves data from JDBC for query 1
public class Query1{
    public String dept_name;
    public String avg_salary_ratio;


    public Query1() {
    }


    public Query1(String var1, String var2) {
        this.dept_name = var1;
        this.avg_salary_ratio = var2;

    }
}
