# 인공지능-2



### Linear Regression(선형 회귀)

- Regression중 가장 간단한 방법
- 결과값이 실수로 나온다.
- ex) 가격 예측, 소비자의 지출 예상, 

#### 수식

##### Linear Regression

- Response (real number) is a linear function of the inputs

  ### <img src="https://render.githubusercontent.com/render/math?math=y(x)=w^Tx %2B \epsilon">

  <img src="https://render.githubusercontent.com/render/math?math=w^T">의 기울기를 가지고 <img src="https://render.githubusercontent.com/render/math?math=\epsilon">의 절편을 가진다.

- Assume that <img src="https://render.githubusercontent.com/render/math?math=\epsilon"> (the residual error) has a Gaussian distribution

  <img src="https://render.githubusercontent.com/render/math?math=p(y|\theta) = N(y|u(x), \sigma^2(x))">

- where

  <img src="https://render.githubusercontent.com/render/math?math=u(x) = w^Tx = w_0 %2B w_1x">

##### Modeling non-linear relationships

- Simply Take

  <img src="https://render.githubusercontent.com/render/math?math=p(y|x, \theta) = N(y|w^Tx, \sigma^2)">

- and replace $x$ with some non-linear function of the inputs

  <img src="https://render.githubusercontent.com/render/math?math=p(y|x, \theta) = N(y|w^T\phi(x), \epsilon^2)">	ex) <img src="https://render.githubusercontent.com/render/math?math=\phi(x) = x^2, \phi(x) = 2x %2B 3x^2">

  this is called the basis function expansion.

  이 경우에도 여전히 Linear Regression 이라고 불린다. 왜냐하면 Parameter <img src="https://render.githubusercontent.com/render/math?math=w">에 대해 선형이기 때문이다. <img src="https://render.githubusercontent.com/render/math?math=w(x %2B x^2) = wx %2B wx^2"> 라는 의미.

- Polynomial Regression

  <img src="https://render.githubusercontent.com/render/math?math=x, x^2, x^n...">

- Multivariate linear regression

  <img src="https://render.githubusercontent.com/render/math?math=w_0 %2B w_1x_1 %2B w_2x_2, w_0 %2B w_1x_1 %2B w_2x_2 %2B w_3x_1^2 %2B w_4x_2^2 ...">

##### Maximum likelihood estimation(MLE)

- Using MLE, arguments  <img src="https://render.githubusercontent.com/render/math?math=\theta"> can be computed by

  $arg\space max\space  log p(D|\theta) = \sideset{}{_{i=1}^Nlogp(y_i|x_i, \theta)}\sum$

- if we plug in the Gaussian formulation

  <img src="https://render.githubusercontent.com/render/math?math=p(y|x, \theta) = N(y|w^Tx, \sigma^2)">

  and put it into the log likelihood above, we get

  $=\sideset{}{_{i=1}^Nlog[(\frac{1}{2\pi\sigma^2})^\frac{1}{2}exp(-\frac{1}{2\sigma^2}(y_i - w^Tx_i)^2)]}\sum$

  $= -\frac{N}{2}log(2\pi\sigma^2) - \frac{1}{2\sigma^2}\sideset{}{_{i=1}^N}\sum(y_i - w^Tx_i)^2$

  and **Negative log likelihood (NLL)** is

  $ \frac{N}{2}log(2\pi\sigma^2) +\frac{1}{2\sigma^2}\sideset{}{_{i=1}^N}\sum(y_i - w^Tx_i)^2$

- To **minimize NLL**, we minimize this term

  $\sideset{}{_{i=1}^N}\sum(y_i - w^Tx_i)^2$

  called **residual sum of squares(RSS)**

- RSS를 통해 NLL을 최소화 하는 것이 가장 최적화된 regression이 된다.

##### Ridge Regression

- MLE can overfit

  - For linear regression, this means the weight can become large

  - We can encourage the weights to be small by putting a zero-mean Gaussian prior on the weights

    <img src="https://render.githubusercontent.com/render/math?math=- l_2">**Regularization**(<img src="https://render.githubusercontent.com/render/math?math=\lambda||w||^2">를 이용하는 Regularzation)

- Zero-mean Gaussian prior on the weights

  $p(w) = \sideset{}{_j  N(w_j|0, \tau^2)}\prod$

- MAP estimation problem

  $argmax \sideset{}{_{i=1}^Nlog(y_i|w_0 +w^Tx_i, \sigma^2)}\sum + \sideset{}{j_i^D logN(w_j|0, \tau^2)}\sum$

- Compare with the MLE problem

  $arg\space max\space log\space p(D|\theta) = \sideset{}{_{i=1}^N (y_i - (w_0 + w^Tx_i))^2 + \lambda||w||_2^2}\sum$

  $J(w) = \frac{1}{N}\sideset{}{_{i=1}^N (y_i-(w_0+w^Tx_i))^2 + \lambda||w||_2^2}\sum$

- Compare with NLL Before

  $\sideset{}{_{i=1}^N}\sum(y_i - w^Tx_i)^2$

- So the first term of ridge regression is same as NLL, and the second term is the complexity penalty (when $\lambda$<img src="https://render.githubusercontent.com/render/math?math=w(x %2B x^2) = wx %2B wx^2" > 0)

- Corresponding solution is

  $\hat{w}_{ridge} = (\lambda I_D + X^TX)^{-1}X^Ty$

  $\hat{w}_{OLS} = (X^TX)^{-1}X^Ty$

- Regularization effects of big data

  - 데이터가 많아질 수록 Regularization 효과를 볼 수있다.
  - = 그래프가 부드러워짐

#### Regression 모델 평가하기

- test case와 past case의 오차 정도를 본다.
  - 둘다 너무 오차가 크다.
    - 모델 설계가 잘못
  - n이 작을 때 past case에 대해 오차가 적고 test case에 대해 약간의 오차를 보인다. n이 커질 수록 개선된다.
    - 설계 잘했다
  - n이 작을때 past case에 대해 오차가 거의없고 test case에 대해 큰 오차를 보인다. n이 커질수록 개선되지만 그래프가 가파르다.
    - Overfitting 이다.
    - = 불필요하게 complex
    - 하지만 n이 커지면 어느정도 유효한 모델이다.
  - n이 적당히 커졌음에도 past case에 대해서만 오차가적고 test case에 대해 오차가 크다.
    - 심하게 Overfitting 되어서 유효하지 않은 모델이다.