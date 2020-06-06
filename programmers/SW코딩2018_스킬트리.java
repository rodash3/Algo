package programmers;

/**
 * @author rodash3
 * @since 2020. 6. 7.
 * @see https://programmers.co.kr/learn/courses/30/lessons/49993
 * @memory
 * @time
 * @caution skill의 각 문자가 위치한 인덱스 비교
 */
class Solution {
    public int solution(String skill, String[] skill_trees) {
		char[] skills = skill.toCharArray(); //skill을 char배열로 변환
		int cnt = 0; //가능한 스킬트리 수
		
		for(String s: skill_trees) {
			//각 스킬트리에서 스킬의 각 문자가 위치한 인덱스 찾기
			int[] orders = new int[skills.length];
			for(int i=0; i<skills.length; i++) {			
				orders[i] = s.indexOf(skills[i]);
			}
			
			//인덱스가 오름차순이어야 가능한 스킬트리
			for(int i=1; i<skills.length; i++) {
				//선행 스킬이 후행 스킬보다 뒤에 위치 ex) 2 0 1 - 불가능한 트리
				//단, 0 -1 -1 처럼 순서가 반전되지만 가능한 경우가 있으므로 후행이 -1이 아닐 경우 조건 추가
				if(orders[i] != -1 && orders[i-1] > orders[i]) {
					cnt--; break;
				}
				// 선행스킬을 배우지 않고 후행 스킬을 배우는 경우 ex) -1 -1 0 - 불가능한 트리
				if(orders[i-1] == -1 && orders[i-1] < orders[i]) {
					cnt--; break;
				}
			}
			cnt++;
		}
        return cnt;
    }
}