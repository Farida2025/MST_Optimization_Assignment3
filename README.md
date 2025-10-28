# Assignment 3: Optimization of a City Transportation Network 

**Student:** Farida Dovletbayeva (SE-2403)  
**Course:** Design and Analysis of Algorithms  
**Topic:** Minimum Spanning Tree (MST) — Prim vs Kruskal

## Overview and Objective

The primary objective was to apply **Prim's** and **Kruskal's** algorithms to **optimize a city's transportation network** by finding the **Minimum Spanning Tree (MST)**. This requires determining the minimum set of potential roads (edges) that connect all city districts (vertices) with the lowest possible total construction cost (edge weight).

I implemented both algorithms, measured their **execution time** and **key operational counts**, and performed a comparative analysis.

---

## Technical Details

### Tech Stack
* **Java 17 (JDK 17)**
* **Maven** for project and dependency management.
* **Jackson** library for robust JSON input (`ass_3_input.json`) and output (`ass_3_output.json`) handling.

### Algorithms & Data Structures (Implementation Details)
| Algorithm | Core Structure | Time Complexity | Key Operations Policy |
| :-------: | :------------: | :-------------: | :-------------------- |
| **Prim's** | Adjacency List + **PriorityQueue** (Binary Heap) | $O(|E| \log |V|)$ | Count heap `offer`/`poll` and frontier expansions. |
| **Kruskal's** | Edge Sorting + **DSU** (Union-Find) | $O(|E| \log |E|) \approx O(|E| \log |V|)$ | Count two `find()` calls per processed edge for cycle checks. |

The **DSU** implementation uses both **path compression** and **union by rank** for optimal near-constant-time amortized performance.

---

## 1. Summary of Input Data and Algorithm Results

The algorithms were executed on two sparse datasets. All assignment metrics were successfully determined and recorded.

| Graph ID | V   | E   | Algorithm | MST Total Cost | Operations Count | Execution Time (ms) |
| :------: | :-: | :-: | :-------: | :------------: | :--------------: | :-----------------: |
| **1** | 5 | 7 | **Prim** | **16** | 12 | 4.5690 |
| | | | **Kruskal** | **16** | 10 | 1.6872 |
| **2** | 4 | 5 | **Prim** | **6** | 8 | 0.0732 |
| | | | **Kruskal** | **6** | 6 | 0.0496 |


**Correctness:** The **Total Cost** of the MST is identical (16 and 6), fulfilling a core assignment requirement.

**MST Edge Set (Example Graph 1):** The resulting minimum cost roads are: (B, C, 2), (A, C, 3), (B, D, 5), (D, E, 6).
---

## 2. Comparison between Prim's and Kruskal's Algorithms

The empirical analysis shows a clear advantage for Kruskal's on these sparse inputs:

1.  **Performance:** **Kruskal's is faster** (e.g., $1.6872$ ms vs $4.5690$ ms for Graph 1).
2.  **Efficiency:** **Kruskal's uses fewer key operations** (10 vs 12 for Graph 1).

This difference is explained by the overhead:
* **Kruskal's:** The $\mathbf{O(\alpha(|V|))}$ amortized time of the optimized DSU makes the central loop (cycle check) extremely fast, offsetting the one-time $\mathbf{O(|E| \log |E|)}$ cost of sorting.
* **Prim's:** The continuous $\mathbf{O(\log |V|)}$ overhead associated with managing the PriorityQueue for every edge incident to the growing tree sums up, making it empirically slower for sparse inputs.

---

## 3. Conclusions Discussing Algorithm Preferability

The optimal choice depends on the specific characteristics of the transportation network:

| Condition | Kruskal's Algorithm (Preferable) | Prim's Algorithm (Preferable) |
| :-------: | :------------------------------: | :---------------------------: |
| **Graph Density** | **Sparse Graphs** ($|E| \approx |V|$), typical for city roads. | **Dense Graphs** ($|E| \approx |V|^2$). |
| **Edge Representation** | When input is a **flat list of edge proposals**. | When the graph is structured for **adjacency lookup**. |
| **Implementation** | Requires complex, but fast, **DSU** implementation. | Relies on standard, readily available **Priority Queue** structures. |

**Final Conclusion for City Optimization:** Given that real-world city networks are generally **sparse**, **Kruskal's algorithm is the preferred choice**. Its superior empirical speed and lower operational count on sparse graphs, as confirmed by this assignment, make it the most efficient solution for minimizing the total construction cost.

---


