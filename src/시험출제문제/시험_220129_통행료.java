package 시험출제문제;

import java.io.*;
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
	static int T, N, Q;
	static int LGN;
	static int[][] parent, arrMax;
	static int[] depth;
	static long[] dist;

	static class Node {
		int dest;
		int cost;

		Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	static ArrayList<Node>[] arrList;
	static long result;
	static int MAX;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_data.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			arrList = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++)
				arrList[i] = new ArrayList<>();
			
			// 인접리스트
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				arrList[a].add(new Node(b, c));
				arrList[b].add(new Node(a, c));
			}

			int nodeCnt = 1;
			LGN = 0;
			while (nodeCnt < N) {
				nodeCnt *= 2;
				LGN++;
			}
			
			parent = new int[LGN + 1][N + 1];
			arrMax = new int[LGN + 1][N + 1];
			depth = new int[N + 1];
			
			Arrays.fill(depth, -1);
			dist = new long[N + 1];
			bfs(1);
			findAncestor();

			result = 0;
			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int lca = LCA(a, b);
				result += (dist[a] + dist[b] - 2 * dist[lca] - MAX);
			}
			bw.write("#" + test_case + " " + result + "\n");
			bw.flush();
		}
	}

	static void bfs(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		depth[n] = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (Node next : arrList[now]) {
				if (depth[next.dest] != -1)
					continue;
				depth[next.dest] = depth[now] + 1;
				parent[0][next.dest] = now;
				dist[next.dest] = dist[now] + next.cost;
				arrMax[0][next.dest] = next.cost;
				q.add(next.dest);
			}
		}
	}

	static void findAncestor() {
		for (int k = 1; k <= LGN; k++) {
			for (int v = 1; v <= N; v++) {
				parent[k][v] = parent[k - 1][parent[k - 1][v]];
				arrMax[k][v] = Math.max(arrMax[k - 1][v], arrMax[k - 1][parent[k - 1][v]]);
			}
		}
	}

	static int LCA(int x, int y) {
		MAX = Integer.MIN_VALUE;
		if (depth[x] > depth[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}

		for (int i = LGN; i >= 0; i--)
			if (depth[y] - depth[x] >= (1 << i)) {
				MAX = Math.max(MAX, arrMax[i][y]);
				y = parent[i][y];
			}

		if (x == y)
			return x;

		for (int i = LGN; i >= 0; i--)
			if (parent[i][x] != parent[i][y]) {
				MAX = Math.max(MAX, Math.max(arrMax[i][x], arrMax[i][y]));
				x = parent[i][x];
				y = parent[i][y];
			}

		MAX = Math.max(MAX, Math.max(arrMax[0][x], arrMax[0][y]));
		return parent[0][x];
	}
}
