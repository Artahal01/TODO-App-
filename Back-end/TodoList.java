import java.util.Scanner;
import java.sql.SQLException;

public class TodoList {
    public static void main(String[] args) throws SQLException {
        chooseAction();
    }

    public static void chooseAction() throws SQLException {
        Scanner sc = new Scanner(System.in);

        //Afficher les choix :
        System.out.println("Choose an action to do: ");
        System.out.println("1 : Add a todo ,");
        System.out.println("2 : Find a todo , ");
        System.out.println("3 : Show all todo , ");
        System.out.println("4 : Update a todo , ");
        System.out.println("5 : Delete a todo , ");
        System.out.println("6 : Qui the program");
        System.out.println("-----------------*------------------");
        //Choisir :
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();

        if(choice==1){

            System.out.println("Enter the id of the todo: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.println("The title: ");
            String title = sc.nextLine();

            System.out.println("The description: ");
            String description = sc.nextLine();

            System.out.println("The deadline: (XXXX-YY-ZZ AA:BB:CC5)");
             String deadline = sc.nextLine();

            System.out.println("How important is it? (number 1-10)");
            int priority = sc.nextInt();

            System.out.println("Status : true of false");
            boolean done = sc.nextBoolean();

            //Appeler la méthode addTodo() : Ajouter un todo
            actionTodo.addTodo(id, title, description, deadline, priority, done);
            System.out.println("-----------------*------------------");
            chooseAction();
        }
        else if(choice==2){
            //Appeler la méthode findTodo() : Chercher un todo
            actionTodo.findTodo();

            System.out.println("-----------------*------------------");
            chooseAction();
        }
        else if(choice==3){
            //Appeler la méthode showAllTodo() : Afficher tous les todo
            actionTodo.showAllTodo();

            System.out.println("-----------------*------------------");
            chooseAction();
        }
        else if(choice==4){
            //Appeler la méthode updateTodo() : Changer une colonne dans un todo
            actionTodo.updateTodo();

            System.out.println("-----------------*------------------");
            chooseAction();
        }
        else if(choice==5){
            System.out.println("Enter the id of the todo you want to delete :");
            int id = sc.nextInt();
            //Appeler la méthode deleteTodo() : Supprimer un todo
            actionTodo.deleteTodo(id);

            System.out.println("-----------------*------------------");
            chooseAction();
        }
        else if(choice==6){
            //Appeler la méthode quit()
            actionTodo.quitProgram();
        }

        else{
            System.out.println("Choose one of the following number!");
            chooseAction();
            System.out.println("-----------------*------------------");
        }
        sc.close();
    }

}
