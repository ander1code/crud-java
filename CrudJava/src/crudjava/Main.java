package crudjava;

import crudjava.crud.*;
import java.util.List;
import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void PrintMessage(int method, int result) {
        if (method == 1) {
            if (result == 1) {
                System.out.println(ANSI_GREEN + "\n----------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "Successfully registered." + ANSI_RESET);
                System.out.println(ANSI_GREEN + "--------------------------------------------|\n" + ANSI_RESET);
            }

            if (result == -1) {
                System.out.println(ANSI_RED + "\n----------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_RED + "Error registering." + ANSI_RESET);
                System.out.println(ANSI_RED + "--------------------------------------------|\n" + ANSI_RESET);
            }
        }

        if (method == 2) {
            if (result == 1) {
                System.out.println(ANSI_GREEN + "\n----------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "Successfully edited." + ANSI_RESET);
                System.out.println(ANSI_GREEN + "--------------------------------------------|\n" + ANSI_RESET);
            }

            if (result == -1) {
                System.out.println(ANSI_RED + "\n----------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_RED + "Error editing." + ANSI_RESET);
                System.out.println(ANSI_RED + "--------------------------------------------|\n" + ANSI_RESET);
            }

            if (result == 0) {
                System.out.println("\n--------------------------------------------|");
                System.out.println("No record found with this ID.");
                System.out.println("--------------------------------------------|\n");

            }
        }

        if (method == 3) {
            if (result == 1) {
                System.out.println(ANSI_GREEN + "\n--------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "Successfully deleted." + ANSI_RESET);
                System.out.println(ANSI_GREEN + "--------------------------------------------|\n" + ANSI_RESET);
            }

            if (result == -1) {
                System.out.println(ANSI_RED + "\n----------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_RED + "Error deleting." + ANSI_RESET);
                System.out.println(ANSI_RED + "--------------------------------------------|\n" + ANSI_RESET);
            }

            if (result == 0) {
                System.out.println("\n----------------------------------------------|");
                System.out.println("No record delete.");
                System.out.println("--------------------------------------------|\n");
            }
        }

        if (method == 5) {
            if (result == 0) {
                System.out.println("\n--------------------------------------------|");
                System.out.println("No record found with this ID.");
                System.out.println("--------------------------------------------|\n");
            }
        }
    }

    public static boolean ValidateData(String name, String email, String salary, String birthday, String gender) {
        boolean result = true;
        System.out.println(ANSI_RED + "--------------------------------------------|" + ANSI_RESET);
        System.out.println(ANSI_RED + "ERRORS:                                     |" + ANSI_RESET);
        if (name.length() < 1) {
            System.out.println(ANSI_RED + "- Name is empty." + ANSI_RESET);
            result = false;
        }

        if (email.length() > 1) {
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println(ANSI_RED + "- Invalid e-mail." + ANSI_RESET);
                result = false;
            }

            if (Crud.CheckEmailRegistered(email)) {
                System.out.println(ANSI_RED + "- Email already exists." + ANSI_RESET);
                result = false;
            }

        } else {
            System.out.println(ANSI_RED + "- E-mail is empty." + ANSI_RESET);
            result = false;
        }

        if (salary.length() > 1) {
            try {
                if (Float.parseFloat(salary) < 0) {
                    System.out.println(ANSI_RED + "- Invalid salary." + ANSI_RESET);
                    result = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "- Invalid salary." + ANSI_RESET);
                result = false;
            }
        } else {
            System.out.println(ANSI_RED + "- Salary is empty." + ANSI_RESET);
            result = false;
        }

        if (birthday.length() > 1) {
            try {
                Date valueOf = Date.valueOf(birthday);
            } catch (Exception e) {
                System.out.println(ANSI_RED + "- Invalid birthday." + ANSI_RESET);
                result = false;
            }
        } else {
            System.out.println(ANSI_RED + "- Birthday is empty." + ANSI_RESET);
            result = false;
        }

        if (gender.length() > 0) {
            if (!gender.equals("M")) {
                if (!gender.equals("F")) {
                    System.out.println(ANSI_RED + "- Invalid gender." + ANSI_RESET);
                    result = false;
                }
            }
        } else {
            System.out.println(ANSI_RED + "- Gender is empty." + ANSI_RESET);
            result = false;
        }
        System.out.println(ANSI_RED + "--------------------------------------------|\n\n" + ANSI_RESET);
        return result;
    }

    public static void InsertPhysicalPerson() {
        System.out.println(ANSI_BLUE + "\n\n--------------------------------------------|" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "INSERT PHYSICAL PERSON                      |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Name: ");
        String name = keyboard.nextLine();

        System.out.print("Email: ");
        String email = keyboard.nextLine();

        System.out.print("Salary: ");
        String salary = keyboard.nextLine();

        System.out.print("Birthday (Ex: 1981-11-12): ");
        String birthday = keyboard.nextLine();

        System.out.print("Gender (M or F): ");
        String gender = keyboard.nextLine();

        if (ValidateData(name.trim(), email.trim(), salary.trim(), birthday.trim(), gender.trim())) {
            PrintMessage(1, Crud.Insert_PhysicalPerson(name, email, Float.parseFloat(salary), Date.valueOf(birthday), gender));
        }
    }

    public static void EditPhysicalPerson() {
        System.out.println(ANSI_BLUE + "\n\n--------------------------------------------|" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "EDIT PHYSICAL PERSON                        |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);

        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("ID: ");
            String id = keyboard.nextLine();

            PhysicalPerson pp = Crud.GetPhysicalPersonByID(Integer.parseInt(id));
            if (pp != null) {
                System.out.println("NAME: " + pp.getName());
                System.out.println("EMAIL: " + pp.getEmail());
                System.out.println("SALARY: " + pp.getSalary());
                System.out.println("BIRTHDAY: " + pp.getBirthday());
                System.out.println("GENDER: " + pp.getGender());
                System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "NEW DATA                                    |" + ANSI_RESET);
                System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);

                System.out.print("Name: ");
                String name = keyboard.nextLine();

                System.out.print("Email: ");
                String email = keyboard.nextLine();

                System.out.print("Salary: ");
                String salary = keyboard.nextLine();

                System.out.print("Birthday (Ex: 1981-11-12): ");
                String birthday = keyboard.nextLine();

                System.out.print("Gender (M or F): ");
                String gender = keyboard.nextLine();

                if (ValidateData(name.trim(), email.trim(), salary.trim(), birthday.trim(), gender.trim())) {
                    PrintMessage(2, Crud.Edit_PhysicalPerson(Integer.parseInt(id), name, email, Float.parseFloat(salary), Date.valueOf(birthday), gender));
                }
            } else {
                PrintMessage(2, 0);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "\n\n--------------------------------------------|" + ANSI_RESET);
            System.out.println(ANSI_RED + "Invalid ID for edition." + ANSI_RESET);
            System.out.println(ANSI_RED + "--------------------------------------------|" + ANSI_RESET);
        }
    }

    public static void DeletePhysicalPerson() {
        System.out.println(ANSI_BLUE + "\n\n--------------------------------------------|" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "DELETE PHYSICAL PERSON                      |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("ID: ");
            String id_temp = keyboard.nextLine();
            int id = Integer.parseInt(id_temp);

            int result = Crud.Delete_PhysicalPerson(id);
            PrintMessage(3, result);
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "\n\n--------------------------------------------|" + ANSI_RESET);
            System.out.println(ANSI_RED + "Invalid ID for deletion." + ANSI_RESET);
            System.out.println(ANSI_RED + "--------------------------------------------|" + ANSI_RESET);
        }
    }

    public static void GetPhysicalPersonByName() {
        System.out.println(ANSI_BLUE + "\n\n--------------------------------------------|" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "PHYSICAL PERSON BY NAME                     |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
        Scanner keyboard = new Scanner(System.in);
        String name = null;

        try
        {
            System.out.print("NAME: ");
            name = keyboard.nextLine();
            System.out.println(ANSI_BLUE + "\n--------------------------------------------|" + ANSI_RESET);
        }
        catch(Exception e)
        {
            name = "";
        }
        
        List<PhysicalPerson> list = Crud.GetPhysicalPersonByName(name);
        if (list != null) {
            for (PhysicalPerson pp : list) {
                System.out.println("ID: " + pp.getId());
                System.out.println("NAME: " + pp.getName());
                System.out.println("EMAIL: " + pp.getEmail());
                System.out.println("SALARY: " + pp.getSalary());
                System.out.println("BIRTHDAY: " + pp.getBirthday());
                System.out.println("GENDER: " + pp.getGender());
                System.out.println(ANSI_BLUE + "\n--------------------------------------------|" + ANSI_RESET);
            }
        }else{
            PrintMessage(5, 0);
        }
    }

    public static void GetPhysicalPersonByID() {
        System.out.println(ANSI_BLUE + "\n\n--------------------------------------------|" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "PHYSICAL PERSON BY ID                       |" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("ID: ");
            String id = keyboard.nextLine();

            PhysicalPerson pp = Crud.GetPhysicalPersonByID(Integer.parseInt(id));
            if (pp != null) {
                System.out.println("NAME: " + pp.getName());
                System.out.println("EMAIL: " + pp.getEmail());
                System.out.println("SALARY: " + pp.getSalary());
                System.out.println("BIRTHDAY: " + pp.getBirthday());
                System.out.println("GENDER: " + pp.getGender());
                System.out.println("\n\n");
            } else {
                PrintMessage(5, 0);
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid ID for search by ID." + ANSI_RESET);
        }
    }

    public static void main(String[] args) {

        int opc = 1;
        while (opc != 0) {

            System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "REGISTRATION                                |" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);
            System.out.println(ANSI_BLUE + " 1 - INSERT PHYSICAL PERSON                 |" + ANSI_RESET);
            System.out.println(ANSI_BLUE + " 2 - EDIT PHYSICAL PERSON                   |" + ANSI_RESET);
            System.out.println(ANSI_BLUE + " 3 - DELETE PHYSICAL PERSON                 |" + ANSI_RESET);
            System.out.println(ANSI_BLUE + " 4 - GET PHYSICAL PERSON BY NAME            |" + ANSI_RESET);
            System.out.println(ANSI_BLUE + " 5 - GET PHYSICAL PEROSN BY ID              |" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "--------------------------------------------|" + ANSI_RESET);

            try {
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Enter option: ");
                opc = keyboard.nextInt();

                switch (opc) {
                    case 1:
                        Main.InsertPhysicalPerson();
                        break;
                    case 2:
                        Main.EditPhysicalPerson();
                        break;
                    case 3:
                        Main.DeletePhysicalPerson();
                        break;
                    case 4:
                        Main.GetPhysicalPersonByName();
                        break;
                    case 5:
                        Main.GetPhysicalPersonByID();
                        break;
                    default:
                        System.out.println("FINISH.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("FINISH.");
                opc = 0;
            }
        }
    }
}
