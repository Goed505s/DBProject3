import java.sql.Date;


//Datatype that which recieves data from JDBC for query 14
public class Query3{
    public String dept_name;
    public String decade;
    public int num_employees;
    public float avg_salary;



    public Query3() {
    }


    public Query3(String var1, String var2, int var3, float var4) {
        this.dept_name = var1;
        this.decade = var2;
        this.num_employees = var3;
        this.avg_salary = var4;
        

    }
}