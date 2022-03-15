/**
Q4. Valid Path

Problem Description

There is a rectangle with left bottom as (0, 0) and right up as (x, y).

There are N circles such that their centers are inside the rectangle.

Radius of each circle is R. Now we need to find out if it is possible that we can move from (0, 0) to (x, y) without touching any circle.

Note : We can move from any cell to any of its 8 adjecent neighbours and we cannot move outside the boundary of the rectangle at any point of time.



Problem Constraints

0 <= x , y, R <= 100

1 <= N <= 1000

Center of each circle would lie within the grid



Input Format

1st argument given is an Integer x , denoted by A in input.

2nd argument given is an Integer y, denoted by B in input.

3rd argument given is an Integer N, number of circles, denoted by C in input.

4th argument given is an Integer R, radius of each circle, denoted by D in input.

5th argument given is an Array A of size N, denoted by E in input, where A[i] = x cordinate of ith circle

6th argument given is an Array B of size N, denoted by F in input, where B[i] = y cordinate of ith circle



Output Format

Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).



Example Input

Input 1:

 x = 2
 y = 3
 N = 1
 R = 1
 A = [2]
 B = [3]
Input 2:

 x = 1
 y = 1
 N = 1
 R = 1
 A = [1]
 B = [1]


Example Output

Output 1:

 NO
Output 2:

 NO


Example Explanation

Explanation 1:

 There is NO valid path in this case
Explanation 2:

 There is NO valid path in this case


*/

package Graph1;

import java.util.ArrayList;

class ValidPath {
	
	String ans = "";
	public String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
		ans = "NO";
		int mat[][] = new int[A + 1][B + 1];
		for (int i = 0; i <= A; i++) {
			for (int j = 0; j <= B; j++) {
				int res = 1;
				for (int k = 0; k < C; k++) {
					int distance = (int) (Math.pow((i - E.get(k)), 2) + Math.pow((j - F.get(k)), 2));
					if (distance <= (D * D)) {
						res = 0;
						break;
					}
				}
				mat[i][j] = res;
			}
		}

		if (mat[0][0] == 0 || mat[A][B] == 0) {
			return ans;
		}

		boolean visited[][] = new boolean[A + 1][B + 1];
		dfs(mat, 0, 0, visited);
		return ans;
	}

	public void dfs(int mat[][], int row, int col, boolean visited[][]) {
		if (!isValid(mat, row, col, visited)) {
			return;
		}

		if (row == mat.length - 1 && col == mat[0].length - 1) {
			ans = "YES";
			return;
		}

		visited[row][col] = true;
		dfs(mat, row + 1, col, visited);
		dfs(mat, row, col + 1, visited);
		dfs(mat, row + 1, col + 1, visited);
		dfs(mat, row - 1, col - 1, visited);
		dfs(mat, row - 1, col + 1, visited);
		dfs(mat, row + 1, col - 1, visited);
		dfs(mat, row - 1, col, visited);
		dfs(mat, row, col - 1, visited);
	}

	public boolean isValid(int mat[][], int row, int col, boolean visited[][]) {
		if (row < 0 || col < 0 || row >= mat.length || col >= mat[0].length || mat[row][col] == 0
				|| visited[row][col]) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		ValidPath vp = new ValidPath();
		ArrayList<Integer> e = new ArrayList<>();
		e.add(2);
		ArrayList<Integer> f = new ArrayList<>();
		f.add(3);
		String ans = vp.solve(2, 3, 1, 1, e, f);
		System.out.println(ans);
	}

}