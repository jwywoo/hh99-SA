import java.util.*;

public class GroupAssignOne {

    public static String scoreBuilder(Map<Integer, String> givenResult){
        int B = 0;
        int S = 0;
        for (String value : givenResult.values()) {
            if (value.equals("B")) B++;
            if (value.equals("S")) S++;
        }
        if (B == 0 & S == 0) return "0B0S";

        else {
            if (B != 0 & S != 0) return B+"B"+S+"S";
            else if (B == 0) return S+"S";
            else return B+"B";
        }
    }

    public static boolean scoreCounts(String given, int tries, ArrayList<Integer> answer) {
        int S = 0;
        int B = 0;
        String score = "";
        // HashMap One (int) index: (int) value
        Map<Integer, Integer> givenMap = new HashMap<>();
        // HashMap Two (int) value: (String) (S or B)
        Map<Integer, String> givenResult = new HashMap<>();

        for (int i = 0; i < given.length(); i++) {
            givenMap.put(i, given.charAt(i) - '0');
            givenResult.put(given.charAt(i) - '0', "");
        }

        // Ball Check
        for (Integer key : givenResult.keySet()) {
            if (Collections.frequency(answer, key) == 1) givenResult.put(key, "B");
        }
        // Strike Check
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).equals(givenMap.get(i))) givenResult.put(givenMap.get(i), "S");
        }

        System.out.println(tries + "번째 시도 :" + given);
        score = scoreBuilder(givenResult);
        System.out.println(score);
        if (score.equals("3S")) return true;
        else return false;
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
