# 인공지능-4

### Deep Natural Language Processing(NLP)

- 단어, 문장, 문서에서 의미를 찾아내는 것이 NLP의 궁극적인 목표

#### Word, Sentence, and Document Embedding

##### Word Vector Representation : "Word Embedding"

- Assign meaningful numbers to a word, in order to user them as inputs for NLP systems

- Continuos(dense) Vector Representations

  $where$					<img src="https://render.githubusercontent.com/render/math?math=[6.2, 1.0, 2.4, 3.3, ..., 3.5]">

  하나의 단어를 실수 여러개의 vector로 표현한다.

  embedding field(word vector가 존재하는 공간) 위의 vector 값들이 서로 비슷한 공간에 위치하면서 연관성을 부여한다.

  ![](C:/CODES/self-online/day10/wordvector.jpg)

- One-hot Vector Representations

  $where$					<img src="https://render.githubusercontent.com/render/math?math=[0.0, 1.0, 0.0, 0.0, ..., 0.0]">

  단어들사이의 relation을 catch할 수 없다.

  ex. Hi, Hello 는 비슷한데 다른 단어로 conut한다.

##### Distributed Representations

- many-to-many relationship (such as concepts-neurons) between two types of representations

  - Each neuron represent something

  - Many-to-many relationship:

    - a concept - many neurons

    - a neuron - many concepts(word)

      | word(concepts)\|neuron | neuron1 | neuron2 | neuron3 | neuron4 | ...  |
      | ---------------------- | ------- | ------- | ------- | ------- | ---- |
      | dog                    | 1       | 3       | 2       | 4       | ...  |
      | cat                    | 4       | 6       | 3       | 1       | ...  |
      | human                  | 5       | 9       | 8       | 2       | ...  |
      | ...                    | ...     | ...     | ...     | ...     |      |

      이런식이다.

- Distributed Representations of Words

  - Semantic and syntactic characteristics are represented as activation of hidden neurons

##### Distributional Hypothesis

- Words that occurs in the same contexts tent to have similar meanings
- 문맥이 비슷하면 의미도 비슷할 것이다. 라는 가정.
- distributed representations은 이 가정을 바탕으로 하고 있다.

##### Word2Vec

- Distributed Representation of Words and Phrases and their Compositionality

- **CBOW**

  ![](C:/CODES/self-online/day10/CBOW.jpg)

  - 문맥에 있는 단어를 안다. 중간에 있는 단어가 무엇일까? fliction

- **Skip-Gram**

  ![](C:/CODES/self-online/day10/Skip-gram.jpg)

  - 중간에 있는 단어를 안다. 문맥의 양 옆에 있는 단어는 무엇일까?

- Evaluation: **Word Similar Task**

  - Efficient Estimation of Word Representations in Vector Space
  - 사람이 매긴 유사도 Evaluation과 비교해 봄.

- Evaluation: **Word Analogy Task**

  - <img src="https://render.githubusercontent.com/render/math?math=A:B=C:D">
    - Given <img src="https://render.githubusercontent.com/render/math?math=A, B">and <img src="https://render.githubusercontent.com/render/math?math=C">, compute <img src="https://render.githubusercontent.com/render/math?math=A %2B B - C">and evaluate cosine distance from <img src="https://render.githubusercontent.com/render/math?math=D">
    - 잘 학습되었다면 <img src="https://render.githubusercontent.com/render/math?math=A %2B B - C = D "> 이어야 한다.

- Problem of word-level approach

  - Out-of-vocabularies: Unseen words
    - Morphologically rich languages
    - Compositioinality of words
  - Quality of vectors assigned to rare words
  - Segmentation issues

##### Subword-information-skip-gram

![](C:/CODES/self-online/day10/subword-information-skip-gram.jpg)

- 단어를 잘라서 모델링을 한다. 한글로 치면 예쁨 -> ㅇ, ㅖ, ㅃ, ㅡ ㅁ 을 나눠서 조합
- 이렇게 하면 학습이 더 잘됨..

<br>

Word Embedding의 문제는 context가 없다는 것이다. 그래서 나온것이 바로!

##### Contextualized Word Embedding

- Nueral net기반 ELMo
  - 방향성이 한방향

##### "Transfomers" as a Bidirectional Language Model

- self-attention을 사용

  ex. eat이랑 apple이 나오면 연관이있다 라는 걸 미리 학습해놓고 문장내에서 연관을 짓는다.

- Bidirectional Encoder Representations from Transformers (BERT)

  - 양방향으로 다 연관을 짓자