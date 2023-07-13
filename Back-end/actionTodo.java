package todoPROG2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class actionTodo {
    private static DatabaseSingleton database = DatabaseSingleton.getInstance();

    public static void addTodo(int id,String title, String description, String deadline, int priority,boolean done){
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

    public static void findTodo() throws SQLException {
        Scanner sc = new Scanner(System.in);

        //L'utilisateur doit choisir une colonne afin de chercher le todo plus rapidement
        System.out.println("Choose a column to find your todo: id, title, description, priority, done");
        String wayToFind = sc.next();

        //Si le choix est String
        if(wayToFind.equals("title") || wayToFind.equals("description")){
            System.out.println("Enter the "+wayToFind+" of the todo: ");
            String choice = sc.next();
            String sql = "SELECT * FROM todo WHERE " + wayToFind + " ilike '%' || ? || '%'";
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setString(1, choice);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                String dateString = String.valueOf(deadline);
                int priority = resultSet.getInt("priority");
                boolean done = resultSet.getBoolean("done");

                //Afficher le résultat
                System.out.println("id:"+id+"\nTitle:"+title+"\nDescription:"+description+
                        "\nDeadline:"+dateString+"\nPriority:"+priority+"\nIs it done:"+done);
            }
        }

        //Si le choix est un Integer
        else if(wayToFind.equals("id") || wayToFind.equals("priority")){
            System.out.println("Enter the "+wayToFind+" of the todo: ");
            int choice = sc.nextInt();
            String sql = "SELECT * FROM todo WHERE " + wayToFind + " = ?";
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setInt(1, choice);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                String dateString = String.valueOf(deadline);
                int priority = resultSet.getInt("priority");
                boolean done = resultSet.getBoolean("done");

                //Afficher le résultat
                System.out.println("id:"+id+"\nTitle:"+title+"\nDescription:"+description+
                        "\nDeadline:"+dateString+"\nPriority:"+priority+"\nIs it done:"+done);
            }
        }
        //Si le choix est un boolean
        else if(wayToFind.equals("done") ){
            System.out.println("Enter the "+wayToFind+" of the todo: ");
            boolean choice = sc.nextBoolean();
            String sql = "SELECT * FROM todo WHERE " + wayToFind + "= ?";
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setBoolean(1, choice);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp deadline = resultSet.getTimestamp("deadline");
                String dateString = String.valueOf(deadline);
                int priority = resultSet.getInt("priority");
                boolean done = resultSet.getBoolean("done");

                //Afficher le résultat
                System.out.println("id:"+id+"\nTitle:"+title+"\nDescription:"+description+
                        "\nDeadline:"+dateString+"\nPriority:"+priority+"\nIs it done:"+done);
            }
        }
    }

    public static void showAllTodo(){
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
                System.out.println("-----------------*------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTodo() throws SQLException {
        Scanner sc = new Scanner(System.in);
        //Quel todo l'utilisateur veut mettre à jour
        System.out.println("Enter the id of the todo you would like to update: ");
        int choosenId = sc.nextInt();

        //Quelle colonne
        System.out.println("What column would you like to update: title, description, deadline, priority, done ?");
        String choosenColumn = sc.next();

        //La colonne choisie possède une valeur en String
        if(choosenColumn.equals("title") || choosenColumn.equals("description") || choosenColumn.equals("deadline")){
            System.out.println("Set the new value of "+choosenColumn);
            String newValue = sc.next();
            String sql = "UPDATE todo SET "+choosenColumn+" = ? WHERE id = ?";
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setString(1,newValue);
            statement.setInt(2, choosenId);

            statement.executeUpdate();
            System.out.println("Updated succesfully");

        }

        //La colonne choisie possède une valeur en int
        else if(choosenColumn.equals("priority")){
            System.out.println("Set the new value of " +choosenColumn);
            int newValue = sc.nextInt();
            String sql = "UPDATE todo SET "+choosenColumn+" = ? WHERE id = ?";
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setInt(1,newValue);
            statement.setInt(2, choosenId);

            statement.executeUpdate();
            System.out.println("Updated succesfully");
        }

        //La colonne choisie possède une valeur en boolean
        else if(choosenColumn.equals("done")){
            System.out.println("Set the new value of " +choosenColumn);
            boolean newValue = sc.nextBoolean();
            String sql = "UPDATE todo SET "+choosenColumn+" = ? WHERE id = ?";
            PreparedStatement statement = database.getPreparedStatement(sql);
            statement.setBoolean(1,newValue);

            statement.executeUpdate();
            System.out.println("Updated succesfully");
        }

    }

    public static void deleteTodo(int id){
        String sql = "DELETE FROM todo WHERE id=?";
        Scanner scn = new Scanner(System.in);

        //Confirmation
        System.out.println("Are you sure you want to delete this todo? : (yes or no)");
        String confirm = scn.next();

        if(confirm.equals("yes")){
            try {
                PreparedStatement statement = database.getPreparedStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                System.out.println("Todo deleted successfully");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("--♠-♠-♠-♠-♠-♠-♠-♠-♠-♠-♠-♠--");
        }
    }

    public static void quitProgram(){
        System.out.println("Thank you for using our services!");
    }
}
