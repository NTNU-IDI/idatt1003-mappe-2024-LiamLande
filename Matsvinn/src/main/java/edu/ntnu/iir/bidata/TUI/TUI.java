package edu.ntnu.iir.bidata.TUI;
import java.util.Scanner;



public class TUI {
    public Scanner S;

    public int readInt(String prompt){
        System.out.println(prompt);
        return Integer.parseInt(S.nextLine());
    }
    public String readString(String prompt){
        System.out.println(prompt);
        return S.nextLine();
    }
    public int readDate(String prompt){
        System.out.println(prompt);
        int date = Integer.parseInt(S.nextLine());
        if(date > 20000000 || date < 21000000){
            System.out.println("Invalid date");
            return 20241013;
        }
        return date;
    }
    public Boolean readBoolean(){
        System.out.println("(y/n)");
        String input = S.nextLine();
        if(input.equals("y")){
            return true;
        }
        return false;
    }

    public TUI(){
        S = new Scanner(System.in);
    }
    public void init(){
        System.out.println("Welcome to the food storage system");
        System.out.println("...");
    }
    public void start(){
        this.mainMenu();
    }
    public void mainMenu(){
        System.out.println("1. Foodstorage management");
        System.out.println("2. Recipes, and other extra features");
        System.out.println("3. Exit");
        int choice = this.readInt("(1,2,3)");
        switch (choice) {
            case 1:
                this.foodStorageMenu();
                break;
            case 2:
                this.secondMenu();
                break;
            case 3:
                System.exit(1);;
                break;
        
            default:
                break;
        }
    }
    private void secondMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'secondMenu'");
    }
    public void foodStorageMenu(){
        Boolean on = true;
        System.out.println("Welcome to the FoodStorage Menu");
        while (on) {
            System.out.println("1.Add Ingredient to ");
        }
    }
}
