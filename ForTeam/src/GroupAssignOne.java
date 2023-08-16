import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GroupAssignOne {

    public static String scoreBuilder(int B, int S){
        String result = "";
        if (B == 0 && S == 0) result = "0B0S";
        else {
            if (S != 0 && B!=0) result = B+ "B" + S+ "S";
            else {
                if (S == 0) result = B + "B";
                else result = S+"S";
            }
        }
        return result;
    }

    public static boolean scoreCounts(String given, int tries, ArrayList<Integer> answer) {
        int S = 0;
        int B = 0;
        for (int i = 0; i < given.length(); i++) {
//            int numGiven = Integer.parseInt(String.valueOf(given.charAt(i)));
            int numGiven = given.charAt(i) - '0';
            if (answer.get(i).equals(numGiven)) {
                S++;
            } else if (Collections.frequency(answer, numGiven) == 1) {
                B++;
            }
        }
        System.out.println(tries + "번째 시도 :" + given);
        System.out.println(scoreBuilder(B,S));
        if (S==3) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            candidates.add(i);
        }

        Collections.shuffle(candidates);
        ArrayList<Integer> answer = new ArrayList<>(candidates.subList(0,3));
        System.out.println(answer);
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
        boolean result = false;
        int counts = 1;
        while (!result) {
            String given = sc.next();
            if (scoreCounts(given, counts, answer)) {
                System.out.println(counts + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                result = true;
            }
            counts++;
        }
    }
}
