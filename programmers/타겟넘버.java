package programmers;

/**
 * @author rodash3
 * @since 2020. 6. 5.
 * @see https://programmers.co.kr/learn/courses/30/lessons/43165
 * @memory
 * @time
 * @caution numbers의 순서는 유지
 */
class Solution {
    static int cnt = 0; //target을 만드는 방법 수
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return cnt;
    }
    
    static void dfs(int[] numbers, int target, int sum, int depth){
        if(depth == numbers.length){
        	// 더하기 빼기로 target을 만들었으면 cnt 증가
            if(sum==target) cnt++;
            return;
        }
        
        dfs(numbers, target, sum+numbers[depth], depth+1); //더하기
        dfs(numbers, target, sum-numbers[depth], depth+1); //빼기
    }
}