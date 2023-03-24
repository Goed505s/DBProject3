import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;

public class RunDBProject {
    public static void main(String[] args) {

        //Establish Connection with database
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        try{

        Boolean done = false;
        while(!done){

            
            System.out.println("Enter a query to perform (0 terminates)");  // Output user input

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object

            String querySelected = myObj.nextLine();  // Read user input
            System.out.println("Query Selected: " +  querySelected);  // Output user input
            System.out.println("===============");  // Output user input


            String url = "jdbc:mysql://localhost:3306/employees";
            Connection connection = DriverManager.getConnection(url, "root", "");

            Statement statement=connection.createStatement();



            //--------------------------------------------------------------------------------------------------
            switch (querySelected) {

            case "1":
                //limit to 1 1ST QUESTION
                //List department(s) with maximum ratio of average female salaries to average men salaries
                String statement1 = "SELECT dept_name, AVG(CASE WHEN gender = 'F' THEN salary END) / AVG(CASE WHEN gender = 'M' THEN salary END) AS avg_salary_ratio\r\n"
                + "FROM employees \r\n"         +
                "JOIN salaries USING (emp_no) JOIN dept_emp USING (emp_no)  JOIN departments USING (dept_no)  GROUP BY dept_name  ORDER BY avg_salary_ratio DESC limit 1" ;

                //System.out.println(statement.executeQuery(statement1));
                ResultSet resultSet1 = statement.executeQuery(statement1);
                List<Query1> q1list = new ArrayList<Query1>();

                while(resultSet1.next())
                {
                    Query1 query1 = new Query1(resultSet1.getString(1), resultSet1.getString(2));
                q1list.add(query1);
                } 

                System.out.println("dept_name   avg_salary_ratio");
                for(int i = 0;i<q1list.size();i++)
                {
                    Query1 e = new Query1();
                    e = q1list.get(i);
                    System.out.print(e.dept_name+"\t");
                    System.out.println(e.avg_salary_ratio+"\t");
                    System.out.println("==============");


                }
                if(q1list.size() == 0)
                {
                    System.out.println("0 row(s) returned");
                }
            break;
            //--------------------------------------------------------------------------------------------------


        //limit to 1 2nd QUESTION
        case "2":
                //List manager(s) who holds office for the longest duration
                String statement2 = " SELECT CONCAT_WS(' ', m.first_name, m.last_name) AS manager_name, DATEDIFF(MAX(e.to_date), MIN(e.from_date)) AS duration \r\n"         +
                "FROM dept_manager e \r\n" + " JOIN employees m ON e.emp_no = m.emp_no \r\n" + "  GROUP BY e.emp_no \r\n" + "ORDER BY duration DESC  limit 1\r\n" ;
                
                ResultSet resultSet2 = statement.executeQuery(statement2);
                List<Query2> q2list = new ArrayList<Query2>();
                
                while(resultSet2.next())
                {
                    Query2 query2 = new Query2(resultSet2.getString(1), resultSet2.getString(2));
                q2list.add(query2);
                } 

                System.out.println("manager_name    duration");
                for(int i = 0;i<q2list.size();i++)
                {
                    Query2 e = new Query2();
                    e = q2list.get(i);
                    System.out.print(e.manager_name+"\t");
                    System.out.println(e.duration+"\t");
                    System.out.println("==============");

                }  
                if(q2list.size() == 0)
                {
                    System.out.println("0 row(s) returned");
                }
            break;

            //--------------------------------------------------------------------------------------------------

        // 3nd QUESTION
        case "3":
                //For each department, list number of employees born in each decade and their average salaries
                String statement3 = "SELECT dept_name, CONCAT((YEAR(birth_date) DIV 10) * 10, 's') AS decade, COUNT(*) AS num_employees, AVG(salary) AS avg_salary \r\n" +  

                "FROM employees JOIN salaries USING (emp_no) JOIN dept_emp USING (emp_no) JOIN departments USING (dept_no)  GROUP BY dept_name, decade";
                
                
            // System.out.println(statement.executeQuery(statement3));
                ResultSet resultSet3 = statement.executeQuery(statement3);
                List<Query3> q3list = new ArrayList<Query3>();
                
                while(resultSet3.next())
                {
                    Query3 query3 = new Query3(resultSet3.getString(1), resultSet3.getString(2), resultSet3.getInt(3), resultSet3.getFloat(4));
                q3list.add(query3);
                } 

                System.out.println("dept_name   decade  num_employees   avg_salary");
                for(int i = 0;i<q3list.size();i++)
                {
                    Query3 e = new Query3();
                    e = q3list.get(i);


                    System.out.print(e.dept_name+"\t");
                    System.out.print(e.decade+"\t");
                    System.out.print(e.num_employees+"\t");
                    System.out.println(e.avg_salary+"\t");
                    System.out.println("==============");

                }  
                if(q3list.size() == 0)
                {
                    System.out.println("0 row(s) returned");
                }  

        break;

        case "4":
                // 4th QUESTION
                //List employees, who are female, born before Jan 1, 1990, makes more than 80K
                //annually and hold a manager position
                String statement4 = "SELECT distinct CONCAT_WS(' ', e.first_name, e.last_name) AS employee_name \r\n" + "FROM employees e"+" JOIN salaries s ON e.emp_no = s.emp_no \r\n"

                + "JOIN dept_manager dm ON e.emp_no = dm.emp_no \r\n"+" WHERE e.birth_date < '1990-01-01' \r\n" + 
                "AND e.gender = 'F' \r\n" 
                + "AND s.salary > 80000";
                
                
            // System.out.println(statement.executeQuery(statement4));
                ResultSet resultSet4 = statement.executeQuery(statement4);
                List<Query4> q4list = new ArrayList<Query4>();
                
                while(resultSet4.next())
                {
                    Query4 query4 = new Query4(resultSet4.getString(1));
                q4list.add(query4);
                } 

                System.out.println("emp_name");
                for(int i = 0;i<q4list.size();i++)
                {
                    Query4 e = new Query4();
                    e = q4list.get(i);
        
                    
                    System.out.println(e.emp_name+"\t");
                    System.out.println("==============");

        
                }  
                if(q4list.size() == 0)
                {
                    System.out.println("0 row(s) returned");
                }
            break;

            case "5":
            // 5th QUESTION
            //Find 1 degree of separation between 2 given employees E1 and E2:
            //1 degree: E1 --> D1 <-- E2 (E1 and E2 work at department D1 at the same
            //time)
            System.out.print("Enter the first employee's ID: ");
            String employeeA = myObj.nextLine();
            System.out.print("Enter the second employee's ID: ");
            String employeeB = myObj.nextLine();
            System.out.println("==============");


            String statement5 = "SELECT a.emp_no AS EmployeeA,  d.dept_no AS DepartmentID, b.emp_no AS EmployeeB\r\n"+
            "FROM dept_emp d, employees a, employees b WHERE a.emp_no = "+employeeA+" AND b.emp_no = "+employeeB+" AND d.emp_no = "+employeeA+" AND d.dept_no\r\n"+
            "IN ( SELECT dept_no FROM dept_emp  WHERE emp_no = "+employeeB+" AND from_date <= NOW() AND to_date >= NOW()) \r\n"+
            "AND d.from_date <= NOW() AND d.to_date >= NOW()";
            
            
            ResultSet resultSet5 = statement.executeQuery(statement5);
            List<Query5> q5list = new ArrayList<Query5>();
            
            while(resultSet5.next())
            {
                Query5 query5 = new Query5(resultSet5.getInt(1), resultSet5.getString(2), resultSet5.getInt(3));
                q5list.add(query5);
            } 

            System.out.println("emp_no  department  emp_no");

            for(int i = 0;i<q5list.size();i++)
            {
                Query5 e = new Query5();
                e = q5list.get(i);
                
                System.out.print(e.employeeA+"\t");
                System.out.print(e.department+"\t");
                System.out.println(e.employeeB+"\t");

                System.out.println("==============");

    
            }  

            if(q5list.size() == 0)
            {
                System.out.println("0 row(s) returned");
            }

        break;


        case "6":
            // 6th QUESTION
            //Find 2 degrees of separation between 2 given employees E1 and E2:
            //2 degrees: E1 --> D1 <-- E3 --> D2 <-- E2 (E1 and E3 work at D1 at the same
            //time; E3 and E2 work at D2 at the same time)

            System.out.print("Enter the first employee's ID: ");
            String employee6A = myObj.nextLine();
            System.out.print("Enter the second employee's ID: ");
            String employee6B = myObj.nextLine();
            System.out.println("==============");


            String statement6 = 
            
            " SELECT e1.emp_no, d1.dept_no,e3.emp_no,  d2.dept_no, e2.emp_no\r\n"+
            "  FROM employees e1\r\n"+
            " JOIN dept_emp de1 ON e1.emp_no = de1.emp_no\r\n"+
            "  JOIN departments d1 ON de1.dept_no = d1.dept_no\r\n"+
            " JOIN dept_emp de2 ON d1.dept_no = de2.dept_no\r\n"+
            " JOIN employees e3 ON de2.emp_no = e3.emp_no\r\n"+
            " JOIN dept_emp de3 ON e3.emp_no = de3.emp_no\r\n"+
            " JOIN departments d2 ON de3.dept_no = d2.dept_no\r\n"+
            " JOIN dept_emp de4 ON d2.dept_no = de4.dept_no\r\n"+
            " JOIN employees e2 ON de4.emp_no = e2.emp_no\r\n"+
            " WHERE e1.emp_no != e2.emp_no\r\n"+
            " AND e1.emp_no = " + employee6A + "\r\n"+
            " AND e2.emp_no = " + employee6B + "\r\n"+
            "  AND de1.from_date <= de2.to_date\r\n"+
            " AND de1.to_date >= de2.from_date\r\n"+
            "  AND de3.from_date <= de4.to_date\r\n"+
            " AND de3.to_date >= de4.from_date\r\n"+
            " AND de2.from_date <= NOW()\r\n"+
            " AND de2.to_date >= NOW()";
            
            
        // System.out.println(statement.executeQuery(statement4));
            ResultSet resultSet6 = statement.executeQuery(statement6);
            List<Query6> q6list = new ArrayList<Query6>();
            
            while(resultSet6.next())
            {
                Query6 query6 = new Query6(resultSet6.getInt(1), resultSet6.getString(2), resultSet6.getInt(3), resultSet6.getString(4), resultSet6.getInt(5));
                q6list.add(query6);
            } 

            System.out.println("emp_no  department  emp_no");

            for(int i = 0;i<q6list.size();i++)
            {
                Query6 e = new Query6();
                e = q6list.get(i);
                
                System.out.print(e.employeeA+"\t");
                System.out.print(e.departmentA+"\t");
                System.out.print(e.employeeB+"\t");
                System.out.print(e.departmentB+"\t");
                System.out.println(e.employeeC+"\t");

                System.out.println("==============");


            }  

            if(q6list.size() == 0)
            {
                System.out.println("0 row(s) returned");
            }

        break;


        case "0":
            done = true;
        break;

                

        default: 
         System.out.println("Please enter a valid query statment. Enter a number 1 - 6 (0 to quit)");
        break;

        }
    }


        //--------------------------------------------------------------------------------------------------










    }


        catch (Exception e){
            System.out.println(e);
        }



       
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



