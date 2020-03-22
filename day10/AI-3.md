# 인공지능-3

### Naive Bayes Classifier

- 기본이 되는 모형이다.
- 분류 결과가 좋진 않지만 연구에서 기준이 되는 모형. 
  ex) 나이브 베이즈 해봣더니 60%이더라 이것보단 좋아야된다. 이런식

#### Feature

- We use "feature" to simplify the classification and clustering problems
- Features turn objects (images, documents, etc) into a set of numbers so that we can do computation over them
- What features can we use for the image classification? For spam email classification?
- 데이터의 성질, detail을 말한다.

##### Problems

- Digit Recognition

  - input: pixel grids

    - int\[2]\[8]\[8]

  - output: a digit 0-9

  - **Naive Bayes model**

    - <img src="https://render.githubusercontent.com/render/math?math=P(Y)=">Y 숫자일 확률, prior probability 라고 한다.

      - Prior Probability는 Uniform을 따르지 않을 수도 있다. 
        ex) 전화번호 맨앞자리는 0일 확률이 높음

    - <img src="https://render.githubusercontent.com/render/math?math=F_{i,j} ="> pixel (i,j)이 색칠 되어있다.

    - <img src="https://render.githubusercontent.com/render/math?math=P(F_{i,j}|P(Y)) = ">숫자가 Y일 때 pixel(i,j)가 칠해질 확률

    - $P(Y|F_{0,0} ...F_{15,15}) \propto P(Y)\sideset{}{_{i,j} P(F_{i,j}|Y)}\prod)$

    - 그렇다면 <img src="https://render.githubusercontent.com/render/math?math=P(F_{i,j}|P(Y))">를 어떻게 찾아내냐?

      - 이걸 찾아내는 과정이 Naive Bayes model을 학습하는 과정이다.

      - Training data를 많이 때려넣는다.

      - Y를 그린 천번의 데이터 중 <img src="https://render.githubusercontent.com/render/math?math=F_{i,j}">가 색칠되어 있는 경우는 얼마나 되느냐?(Emprical)

        <img src="https://render.githubusercontent.com/render/math?math=P_{ML}(x) = \frac{count(x)}{total\space sample}">

        또는, 전문가에게 물어볼 수 도 있다. (xray에서 이부분이 이럴때 이 병일 확률이 얼마나될까요? 의사에게 물어보는)

    - Overfitting?

      - test data 구석에 점이 찍혀있으면 확률이 0이 되므로 total P 도 0이될 수 있다.

      - Smoothing estimate를 해야된다. 어떻게? 모든 데이터에 + 1을 해준다.

        <img src="https://render.githubusercontent.com/render/math?math=P_{ML}(x) = \frac{count(x) + 1}{total\space sample +1}">

        더 일반적으론 k를 더해준다.

        <img src="https://render.githubusercontent.com/render/math?math=P_{LAP}(x) = \frac{count(x) + k}{total\space sample +k}">

        이러한 smoothing을 Laplace smoothing이라고 한다.

        이 때, k 같은 parameter를 hyper parameter라고 한다. regulation에서의 <img src="https://render.githubusercontent.com/render/math?math=\lambda">도 마찬가지.

    - Tuning on Held-Out Data

      - Held-out data = validation data
      - 일반적으로 1000개의 데이터가 있으면 800은 training, 100은 validation, 100은 test용으로 사용한다.

- Email spam filtering

  - **Naive Bayes model**
    - <img src="https://render.githubusercontent.com/render/math?math=P(C)="> 메일 C가 스팸일 확률
    - <img src="https://render.githubusercontent.com/render/math?math=W_i">단어 i가 있다.
    - <img src="https://render.githubusercontent.com/render/math?math=P(W_i|C)=">C가 스팸이었는데 단어 $W_i$가 포함되어있을 확률

How would you do this?

#### Simple Classification

- Simple example: two binary features

  <img src="https://render.githubusercontent.com/render/math?math=P(m|s, f)"> <- direct estimate

  <img src="https://render.githubusercontent.com/render/math?math=P(m|s, f) = \frac{P(s, f|m)P(m)}{P(s,f)}"> <- Bayes estimate(no assumptions)

  <img src="https://render.githubusercontent.com/render/math?math=P(m|s, f) = \frac{P(s|m)P(f|m)P(m)}{P(s,f)}"> <- Conditional independence

#### Baselines

- model 학습의 기준이되는 

- 대부분의 경우 NB(Naive Baise)가 Baseline이 되는데 다른것들을 사용할 수도 있다.

- ex) most frequent label classifier

  label중 60%가 dog이라면 60%가 dog의 baseline이 된다.

- ex) 가장 최근에 나온 모델을 baseline으로 사용할 수도 있다.

#### What to do about Errors?

- 에러가 많이 나올 경우 다른 feature가 더 없는지 찾아 봐야 한다.

  - 일반적으로 homogeneous한 feature들이 좋음

    ex. 모든 feature가 word인 경우, 모든 feature가 숫자인 경우

#### Feature

- Naive Bayes

  - random variable & each value has conditional prob. given the label.

- Most Classifiers

  - features are real-valued fucntions

- Common special cases

  - Indicator features take values 0 and 1 or -1 and 1
  - Count feature return non-negative integers (Back of words 3, 5)

- Feature를 생성하기 위해 computation이 필요할 수도 있고 computation에 많은 cost가 있을 수 있다.

- Feature를 생성하기 위해 심지어 다른 classfier 나 model이 사용 될 수도 있다.

- Feature를 결정하는 것은 domain knowledge(전문지식)를 필요로한다.

  ex. 어떤 feature가 큰 영향을 주는지는 전문가의 지식을 필요로 함