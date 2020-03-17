# Collection 사용

Collection이란 Stack, Queue, Array, List, Hash, Map, Dictionary 같이 데이터를 그룹으로 저장할 수 있는 메모리 구조를 말한다.

### 왜 사용할까?

코드에서 **비교 연산** 후 값을 결정하는 동작이 동일한 패턴으로 반복된다면 Collection 사용을 고려할 수 있다.

Collection을 사용한다고 해서 무조건적으로 좋은 것은 아니지만 다음과 같은 이점을 기대할 수 있다.

- 코드의 가독성 향상
-  CPU 사용률 감소 (대신, 메모리 사용량 증가)

물론 이 두가지의 이점을 얻으려면 Collection을 **잘** 사용해야 한다. 콜렉션은 데이터를 주소 값으로 바로 접근하기 위해 사용하므로 미리 Collection 데이터를 선언해야 한다. 그러므로 메모리 사용량은 증가하지만 비교 연산을 계속 할 필요 없이 바로 데이터에 접근할 수 있는 장점이 있다. 또한 Collection을 원인, 결과 값의 데이터 쌍으로 저장할 경우 조건문을 나열하는 것 보다 코드의 가독성이 좋아지고 유지보수 하기 편리해진다.



### 과제

- [Java코드](Collection.java)
