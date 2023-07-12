import java.sql.*;
public class TodoList {
    private static DatabaseSingleton database = DatabaseSingleton.getInstance();
    public static void addToDo(int id,String title, String description, String deadline, int priority,boolean done){
        String sql = "INSERT INTO todo VALUES (?,?,?,?,?,?) ";
        Timestamp dateString = Timestamp.valueOf(deadline);
        try {
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,title);
            statement.setString(3,description);
            statement.setTimestamp(4,dateString);
            statement.setInt(5,priority);
            statement.setBoolean(6,done);
            statement.executeUpdate();
            System.out.println("Todo added succesfully");
        } catch (SQLException e) {
       
            e.printStackTrace();
        }
        
    } 
    public static void findToDoById(int id){
        String sql = "SELECT * FROM todo WHERE id = ?";
        try {
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
            int Id = result.getInt("id");
            String title = result.getString("title");
            String description = result.getString("description");
            Timestamp deadline = result.getTimestamp("deadline");
            String dateString = String.valueOf(deadline);
            int priority = result.getInt("priority");
            boolean done = result.getBoolean("done");
            System.out.println("id:"+Id+"\nTitle:"+title+"\nDescription:"+description+
            "\nDeadline:"+dateString+"\nPriority:"+priority+"\nIs it done:"+done);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void findAllTodo(){
        try {
            String sql = "SELECT * FROM todo ORDER BY priority DESC";
            PreparedStatement statement = database.getPreparedStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
            int id = result.getInt("id");
            String title = result.getString("title");
            String description = result.getString("description");
            Timestamp deadline = result.getTimestamp("deadline");
            String dateString = String.valueOf(deadline);
            int priority = result.getInt("priority");
            boolean done = result.getBoolean("done");
            System.out.println("id:"+id+"\nTitle:"+title+"\nDescription:"+description+
            "\nDeadline:"+dateString+"\nPriority:"+priority+"\nIs it done:"+done);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateToDo(String title, String description, String deadline, int priority,boolean done,int id){
        String sql = "UPDATE todo SET title = ?,description = ?,deadline = ?,priority = ?,done = ? WHERE id = ? ";
        Timestamp dateString = Timestamp.valueOf(deadline);
        try {
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setInt(6,id);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setTimestamp(3,dateString);
            statement.setInt(4,priority);
            statement.setBoolean(5,done);
            statement.executeUpdate();
            System.out.println("Todo updated succesfully");
        } catch (SQLException e) {
       
            e.printStackTrace();
        }
        
    }
    public static void deleteTodo(int id){
        String sql = "DELETE FROM todo WHERE id=?";
        try {
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setInt(1, id);
            statement.executeQuery();
            System.out.println("Todo deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
