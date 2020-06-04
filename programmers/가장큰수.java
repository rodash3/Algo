package programmers;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author rodash3
 * @since 2020. 6. 5.
 * @see https://programmers.co.kr/learn/courses/30/lessons/42746#
 * @memory
 * @time
 * @caution 숫자 정렬이 아니라 문자열 정렬로 접근
 */
class Solution {
    public String solution(int[] numbers) {
    	// int[] numbers를 스트링 배열로 받는다
        String[] snumbers = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            snumbers[i] = String.valueOf(numbers[i]);
        }
        
        // concat하였을 때 커지는 순서로 snumbers를 정렬한다
        Arrays.sort(snumbers, new Comparator<String>(){
            @Override
		    public int compare(String o1, String o2) {
			    return (o1.concat(o2)).compareTo(o2.concat(o1))*-1;
		    }
        });
        
        // 정렬된 순서로 이어붙여 출력하기 위해 sb에 합친다
        StringBuilder sb = new StringBuilder();
        for(String s: snumbers){
            sb.append(s);
        }
        // 0으로 시작하는 경우는 sb가 0000.. -> 0으로 출력해야한다
        if(sb.toString().startsWith("0")){
            return "0";   
        }
        // 이외의 경우엔 그대로 출력
        return sb.toString();
    }
}