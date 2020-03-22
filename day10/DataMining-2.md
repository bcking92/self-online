# Data Mining-2

- Generative model(생성 모델)

  - A model for randomly generating observable data based on hidden parameters

    e.g.) Repeatedly select a pocket first and next retrieve a ball from the selected pocket with probability distributions

- Probabilistic Modeling for Generating Documents

  - 관찰된 표본으로 모분포를 추정한다.

### EM(Expectation-Maximization) Clustering using Gaussian Mixture Model(GMM)

- 원하는 클러스터링의 개수 k
- k 개수의 cluster가 있다고 가정하고 cluster마다 data를 생성하는 어떤 분포가 있다고 가정한다.
- 이 때 주어진 데이터를 이용해 cluster 마다 어떤 분포를 가지는지 찾아내겠다고 하는 것이 EM Clustering이다.
- 가장 높은 likelihood를 가지는 분포별 Weight, Variance, Mean 의 조합을 찾아낸다.
- 이후 특정 데이터가 어떤 분포에 의해 생성되었을 확률이 가장 높은지 계산하여 클러스터링을 한다.
- Powerful한 클러스터링 방법이다.



### Probabilistic Latent Semantic Indexing(PLSI)

 