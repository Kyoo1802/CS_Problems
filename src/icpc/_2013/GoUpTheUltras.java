package icpc._2013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author Kyoo
 */
public class GoUpTheUltras {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while ((s = in.readLine()) != null) {
            StringBuilder sB = new StringBuilder("");
            int n = Integer.parseInt(s);
            StringTokenizer st = new StringTokenizer(in.readLine());
            int[] h = new int[n];
            for (int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Pick> picks = new ArrayList<Pick>(n);
            for (int i = 0; i < n; i++) {
                if ((i == 0 || h[i - 1] < h[i]) && (i == n - 1 || h[i] > h[i + 1])) {
                    picks.add(new Pick(h[i], i));
                }
            }
            Deque<Pick> stack = new LinkedList<Pick>();
            for (int i = 0; i < picks.size(); i++) {
                while (!stack.isEmpty() && stack.peek().h <= picks.get(i).h) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    picks.get(i).left = stack.peek();
                }
                stack.push(picks.get(i));
            }
            stack.clear();
            for (int i = picks.size() - 1; i >= 0; i--) {
                while (!stack.isEmpty() && stack.peek().h <= picks.get(i).h) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    picks.get(i).right = stack.peek();
                }
                stack.push(picks.get(i));
            }
            int[][] rmq = processRMQ(h);
            for (Pick pick : picks) {
                if (pick.left == null && pick.right == null) {
                    addUltra(sB, pick, pick.h);
                } else if (pick.left == null) {
                    addUltra(sB, pick, pick.h - rmq(pick, pick.right, rmq, h));
                } else if (pick.right == null) {
                    addUltra(sB, pick, pick.h - rmq(pick.left, pick, rmq, h));
                } else {
                    addUltra(sB, pick, pick.h - Math.max(rmq(pick.left, pick, rmq, h), rmq(pick, pick.right, rmq, h)));
                }
            }
            System.out.println(sB.toString().trim());
        }
    }

    private static void addUltra(StringBuilder ultras, Pick pick, int d) {
        if (d >= 150000) {
            ultras.append(pick.idx + 1).append(" ");
        }
    }

    private static int rmq(Pick a, Pick b, int[][] rmq, int[] picks) {
        int size = b.idx - a.idx + 1;
        int log2S = (int) (Math.log(size) / Math.log(2));
        return Math.min(picks[rmq[a.idx][log2S]], picks[rmq[b.idx - (1 << log2S) + 1][log2S]]);
    }

    private static int[][] processRMQ(int[] picks) {
        int n = picks.length;
        int log2Size = (int) (Math.log(n) / Math.log(2));
        int[][] rmq = new int[n][log2Size + 1];
        for (int idx = 0; idx < n; idx++) {
            rmq[idx][0] = idx;
        }

        for (int log2 = 1; log2 <= log2Size; log2++) {
            int pow = (1 << log2);
            for (int idx = 0; idx + pow - 1 < n; idx++) {
                if (picks[rmq[idx][log2 - 1]] < picks[rmq[idx + (1 << (log2 - 1))][log2 - 1]]) {
                    rmq[idx][log2] = rmq[idx][log2 - 1];
                } else {
                    rmq[idx][log2] = rmq[idx + (1 << (log2 - 1))][log2 - 1];
                }
            }
        }
        return rmq;
    }

    static class Pick {

        int h;
        int idx;
        Pick left;
        Pick right;

        public Pick(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }

        @Override
        public String toString() {
            int l = left == null ? -1 : left.idx + 1;
            int r = right == null ? -1 : right.idx + 1;
            return "[" + l + "_(" + (idx + 1) + ", " + h + ")_" + r + "]";
        }
    }
}
