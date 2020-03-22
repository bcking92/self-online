# Data Mining-1

### Clustering(클러스터링)

- 데이터를 유사도에 의해 K개의 그룹으로 나누는 것

- 추천시스템에 이용된다.
- 사용 예시
  - 백화점 고객을 구매 상품에따라...
  - Gene 데이터를 유사도에 따라...
  - 추천 시스템에 의해 고객의 과거 패턴을 이용에 따라...
  - 텍스트 문서들을 주제에 따라...
  - 이미의 유사도에 따라...
  - 고객과 통화한 내용을 카테고리별로...
- n개의 데이터를 클러스터링 할 수 있는 경우의 수는 매우 많다.
  - 두개의 그룹으로 나눌 경우 $2^n$ 개



#### 클러스터링 성능 측정

- 아래의 식을 통해 성능을 측정할 수 있다.
- $C_m$ : 클러스터 센터
- $\sideset{}{_{m=1^k}}\sum\sideset{}{_{t_{mi}\in Km} (C_m - t_{mi})^2}\sum$
- 각각의 클러스터 마다 데이터와 클러스터의 중심 사이의 거리를 구하고 그 합을 이용해 성능을 측정하는 법.
- 값이 낮을 수록 클러스터링이 잘 이루어진 것이다.
- 나의 의문점
  - 그렇다면 그룹이 많아질수록 점수가 낮아지는데 이것은 어떻게 극복할 수 있는가?



### Partitional Algorithm

- 가능한 그룹핑을 만들어보면서 그 중에 measure 가 좋은 것들을 찾아내는 방법

- Enumerate K partitions optimizing some criterion

  - An example of some criteria - square-error criterion

    $\sideset{}{_{i=1}^k}\sum \sideset{}{_{p\in C_i}||p-m_i||^2}\sum$

  - $m_i$ is the mean of cluster $C_i$



### K-means Clustering

- Given k, the k-means algorithms performs the following repeatedly
  1. Partition objects into k nonempty subsets
     데이터를 랜덤하게 k개의 그룹으로 클러스터링을 한다.
  2. Compute seed points as the centroids of the clusters of the current partition (the centroid is the center, i.e., mean point, of the cluster)
     지금 나눠진 그룹의 centeroid(mean point)를 구한다. 
  3. Assign each object to the cluster with the nearest seed point
     각각의 데이터를 다시 보면서 가장 가까운 centeroid가 속한 그룹으로 다시 배치한다.
  4. Stop when no more new assignments. Otherwise go back to Step 2
     새로 배치할 데이터가 없으면 끝내고, 그렇지 않으면 2번 과정을 반복한다.
- The k-means algorithm finds one of clustering. results
- Thus, repeat the above many times and select the best clustering
  위의 과정으로 몇번의 클러스터링을 해본 후 그 중 가장 좋은 것을 선택한다.
- K-Means has problems when clusters are of differing(단점)
  - cluster의 size가 작거나 클 경우 잘 찾지 못한다.
  - Non-spherical shape
    평균점으로 부터 일정한 거리에 있는 데이터만 잘 찾는다. e.g.기다란 직사각형 모양의 데이터는 찾기 어려움
  - Outlier가 있을 경우 문제가된다. 너무 멀리 떨어진 한 데이터가 있을 경우 그것을 포함해서 중심점을 찾기 때문에 중심점이 좀 벗어나게된다.



#### K-medois Clustering

- Instead of taking the mean value of the object in a cluster as a reference point, medoids can be used, which is the most centrally located object in a cluster
- Outlier를 해결하기 위해 쓰는 K-means의 한 방법.
- centroid를 평균값이아니라 클러스터 내부의 데이터로 설정하는 방법



### Hierarchical Clustering Algorithm

- Given k, the hierarchical algorithm is implemented in four steps:

  - Say "Every point is it's own cluster"
  - Find "most similar" pair of clusters
  - Merge it into a parent cluster
  - Repeat.. until you've merged the whole dataset into k cluster

- bottom up 방식과 top down 방식이 있는데 bottom up 방식이 일반적으로 사용된다.

- 클러스터와 클러스터를 merge하는 상황이 생기는데 이 때, 어떤 알고리즘을 이용하느냐에 따라 성능 차이가 발생한다.

  - $d_{min}(C_i,C_j)$ = 클러스터간의 모든 데이터 pair의 거리를 다 계산하여 그 중 최솟값을 클러스터 사이의 거리라고 판별
    이 알고리즘을 사용하면 Single-link 알고리즘이라고 불린다.
  - $d_{mean}(C_i,C_j)$ = 클러스터의 중심점을 먼저 구하고 그 중심점 사이의 거리를 클러스터 사이의 거리라고 판별
    Centroid-link 라고 불린다.
  - $d_{ave}(C_i,C_j)$ = 클러스터간의 모든 데이터 pair의 거리를 다 계산한 후 그 평균값을 클러스터 사이의 거리라고 판별
    Average-link 라고 불린다.
  - $d_{max}(C_i,C_j)$ = 클러스터간의 모든 데이터 pair의 거리를 다 계산한 후 가장 먼 거리를 클러스터 사이의 거리라고 판별
    이 알고리즘을 사용하면 Complete-link 알고리즘이라고 불린다.

- Single-link, Complete-link 이외에도 Average-link($d_{ave}$), Mean-link(머지를 먼저 한다음 머지된 클러스터 내의 데이터들 사이의 데이터 pair의 거리를 구해 평균을 낸 값을 사용), Centroid-link($d_{mean}$)

  

### DBSCAN Clustering Algorithm(Density-based Clustering Algorithms)

- Two parameters:

  - Eps: Maximum radius of the neighbourhood
  - MinPts: Minimum number of points in an Eps-neighbourhood of that point

- $N_{Eps}(p) : \{q\space belongs\space to\space D|dist(p,q) \le Eps \}$

- Core point

  - p is a core point if $|N_{Eps}(q)| \ge MinPts$
  - Otherwise, p is a border point

- Directly density-reachable: A point p is directly density-reachable from a point q w.r.t Eps, MinPts if

  - p belongs to $N_{Eps}(q)$
  - $|N_{Eps}(q)| \ge MinPts$

- Density-reachable:

  - A point p is density-reachable from point q w.r.t, Eps, MinPts if there is a chain of points
    $p_1,...,p_n, p_1=q, p_n=p$ such that $p_{i+1}$ is directly density-reachable from $p_i$

- Density-connected

  - A point p is density-connected to a point q w.r.t. Eps, MinPts if there is a point o such that both, p and q are density-reachable from o w.r.t Eps and MinPts

- #### Algorithm

  - Given a set of points D, a cluster C is a density-based cluster w.r.t Eps and MinPts if

    - (**Maximality**) For every $p_i, p_j$ in $D_i$ if $p_i \in C$ and $p_j$ is density-reachable from $p_i,p_j$ belongs to $C$
    - (**Connectivity**) For every $p_i,p_j$ in $C, p_i$ is density-connected from $p_j$

  - Outlier

    - if a point in $p$ is a border point and does not belongs the any other cluster, $p$ is a outlier

  - Progress

    - e.g.

      - Eps = 1

      - MinPts = 3

      - 현재 클러스터 = k = 0

        1. 방문하지 않은 한 점을 선택
        2. core point인지 판별, core이면  k += 1 하고
           neighbor들은 클러스터 k에 속하게 함.
           core가 아니면 넘어감, 해당 점을 outlier로 생각함.(noise에 속하게 함)
           현재 점을 visited 처리
        3. 클러스터k 리스트에 있는 점중에 방문하지 않은 점을 방문
        4. core point 인지 판별, core이면 네이버를 전부 클러스터 k로 합친다.
           아닐 경우는 가만히 냅둔다.
           현재 점을 visited 처리
        5. 3번으로 돌아가 반복, 
           클러스터 k 리스트의 점들을 모두 방문했다면 1번으로 돌아간다.
           모든 점을 방문했다면 클러스터링 종료

      - **pseudocode**

        ![](https://t1.daumcdn.net/cfile/tistory/99F0D33D5B9D146E14?download)

        

