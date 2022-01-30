package 시험출제문제;

import java.io.*;
import java.nio.channels.OverlappingFileLockException;
import java.util.*;

/*20220119토요일 : 통행료 , LCA

(입력)
3
7 4
1 2 7
2 3 4
3 4 5
3 6 3
6 7 1
5 7 2
1 5
2 6
4 5
6 7
6 8
3 1 11
2 3 15
5 2 6
4 3 5
6 2 2
1 2
2 3
2 4
2 6
5 1
4 5
3 6
2 1

6 5
2 1 16
3 1 6
4 1 14
6 2 12
5 2 4
1 2
4 1
4 2
4 5
5 2

(결과)
#1 19
#2 57
#3 32
*/
public class 시험_220129_통행료 {
	static int N, Q;
	static ArrayList<Node>[] arrList;

	private class Node {
		int dest;
		int cost;

		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_data.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			arrList = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				arrList[i] = new ArrayList<>();				
			}
			
			// 인접리스트
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				arrList[a].add(new Node(b,c));
				arrList[b].add(new Node(a,c));
			}
			
			//
			
			
			

		} // text case

	}

}
