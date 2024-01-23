//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Grade grade = Grade.GOLD;

        switch (grade){
            case GOLD:
                System.out.println("Gold12");
                break;
            case SILVER:
                System.out.println("Silver12");
                break;
            default:
                System.out.println("끝");
                break;
        }
    }
}