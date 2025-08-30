package org.example;

import java.util.*;

public class Main {
    // 树的邻接表表示
    private static List<List<Edge>> graph;
    // 红点状态
    private static boolean[] isRed;
    // 节点深度
    private static int[] depth;
    // 父节点
    private static int[] parent;
    // 从根到每个节点的距离
    private static long[] distFromRoot;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取n和q
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        // 初始化数据结构
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        isRed = new boolean[n + 1];
        depth = new int[n + 1];
        parent = new int[n + 1];
        distFromRoot = new long[n + 1];

        // 读取初始红点状态
        for (int i = 1; i <= n; i++) {
            isRed[i] = scanner.nextInt() == 1;
        }

        // 构建树
        for (int i = 0; i < n - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        // DFS预处理
        dfs(1, -1, 0);

        // 处理查询
        for (int i = 0; i < q; i++) {
            int t = scanner.nextInt();
            int v = scanner.nextInt();

            if (t == 1) {
                // 切换节点v的红点状态
                isRed[v] = !isRed[v];
            } else {
                // 查询节点v到所有红点的距离和
                long result = queryDistanceSum(v);
                System.out.println(result);
            }
        }
    }

    // DFS预处理：计算深度、父节点、子树大小、到根的距离
    private static void dfs(int node, int par, int d) {
        parent[node] = par;
        depth[node] = d;
        distFromRoot[node] = d;

        for (Edge edge : graph.get(node)) {
            if (edge.to != par) {
                distFromRoot[edge.to] = distFromRoot[node] + edge.weight;
                dfs(edge.to, node, d + 1);
            }
        }
    }

    // 查询节点v到所有红点的距离和
    private static long queryDistanceSum(int v) {
        long sum = 0;
        // 对于每个红点，计算到v的距离
        for (int i = 1; i <= graph.size() - 1; i++) {
            if (isRed[i]) {
                sum += distance(v, i);
            }
        }
        return sum;
    }

    // 计算两个节点之间的距离
    private static long distance(int u, int v) {
        // 找到LCA
        int lca = findLCA(u, v);
        return distFromRoot[u] + distFromRoot[v] - 2 * distFromRoot[lca];
    }

    // 找到最近公共祖先
    private static int findLCA(int u, int v) {
        while (depth[u] > depth[v]) {
            u = parent[u];
        }
        while (depth[v] > depth[u]) {
            v = parent[v];
        }
        while (u != v) {
            u = parent[u];
            v = parent[v];
        }
        return u;
    }

    // 边的类
    private static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
