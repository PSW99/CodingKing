import java.util.*;
 
public class Main {    
    
    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        
        int target = scan.nextInt();
        int m = scan.nextInt();
        
        boolean[] broken = new boolean[10];
        for(int i = 0; i < m; i++) {
            int n = scan.nextInt();
            broken[n] = true;
        }
        
        int result = Math.abs(target - 100);
        for(int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();
            
            boolean isBreak = false;
            for(int j = 0; j < len; j++) {
                if(broken[str.charAt(j) - '0']) { 
                    isBreak = true; 
                    break;
                }
            }
            if(!isBreak) { 
                int min = Math.abs(target - i) + len;
                result = Math.min(min, result);
            }
        }        
        System.out.println(result);
    }
}
