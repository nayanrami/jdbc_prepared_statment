package dao;


import in.ac.adit.practice.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeDAOImpl implements EmployeeDAO{

    static Logger logger = Logger.getLogger("EmployeeDAOImpl");
    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private String query;

    public EmployeeDAOImpl(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp_db", "root", "");

            logger.info("Database Connection Successful :");
        }catch (Exception ex){
            logger.warning("Database Connection Fail :"+ex.getMessage());
        }
    }

    @Override
    public boolean saveEmployee(Employee employee) {
        query =  "insert into employee_tbl values(?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(query);

            try {
                BufferedReader bif = new BufferedReader(new FileReader(new File("/Users/nayanmali/Downloads/MOCK_DATA.csv")));
                String data;
                while((data = bif.readLine())!=null){
                    System.out.println(data);
                    String dataprocess[] = data.split(",");
                    statement.setLong(1, Long.parseLong(dataprocess[0]));
                    statement.setString(2, dataprocess[1].toString());
                    statement.setString(3, dataprocess[1].toString());
                    statement.setString(4, dataprocess[1].toString());
                    statement.setString(5, dataprocess[1].toString());
                    statement.addBatch();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            statement.executeBatch();
            logger.info("Query : "+query);
            return true;
        }catch (SQLException exception){
            logger.warning(exception.getMessage());
        }
        return false;
    }

    @Override
    public Employee getEmployee(long id) {
        query = "SELECT * FROM employee_tbl where id = "+id;
        Employee employee = null;
        logger.info(query);
        try {
            resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));;
                employee.setFirstname(resultSet.getString("firstname"));
                employee.setLastname(resultSet.getString("lastname"));
                employee.setEmail(resultSet.getString("email"));
                employee.setMobileno(resultSet.getString("mobileno"));
            }
            logger.info(query);
        }catch (Exception exception){
           exception.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee oldObj, Employee newObj) {
        return null;
    }

    @Override
    public boolean removeEmployee(long id) {
        return false;
    }

    @Override
    public List<Employee> getAllEmployee() {
        query = "SELECT * FROM employee_tbl";
        List<Employee> employeeList = new ArrayList<Employee>();
        logger.info(query);
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Employee employee = new Employee();
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));;
                employee.setFirstname(resultSet.getString("firstname"));
                employee.setLastname(resultSet.getString("lastname"));
                employee.setEmail(resultSet.getString("email"));
                employee.setMobileno(resultSet.getString("mobileno"));
                employeeList.add(employee);
            }
            logger.info(query);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return employeeList;
    }
}
