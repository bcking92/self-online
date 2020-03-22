# 이더리움의 이해

### 이더리움 소개

- 2013년, Vitalik Buterin이 제안한 공개형 블록체인 
- 분산 어플리케이션을 위한 플랫폼을 표방한다.
- BlockChain 20
  - 비트코인의 scripting language를 개선, 발전시킨 스마트 컨트랙트
- 이더(Ether, ETH)
  - 이더리움에서 발행한 암호화폐
  - 거래, 스마트 컨트랙트 비용 지불 등 이더리움이 동작하기 위해 사용

##### 분산화된 상태전이 머신

- 트랜잭션에 기반한 상태전이
  - 이전상태로 되돌릴 수 없음!
- 암호화 알고리즘 활용 -> 무작위로 상태전이가 일어나는 것을 방지
- 모든 참여자가 동일한 상태를 공유
- 블록은 해당 시점의 이더리움 상태를 나타낸다고 볼 수 있다.
  - 현재 블록 -> 현재 이더리움의 상태
- 전세계의 컴퓨터들이 동일한 장부를 가지고 있다.

##### 이더리움 계정의 종류

- 외부 소유 계정(EOA, External Owned Account)
  - ETH 잔액 유지
  - 개인 키를 통한 주소 관리
  - ETH 전송, 컨트랙트 실행을 위한 거래 전송 가능
  - 컨트랙트 코드를 가지고 있지 않음(빈 문자열 hash값)
- 컨트랙트 계정(CA, Contract Account)
  - ETH 잔액 유지
  - 주소를 가지고 있으나 개인키는 존재하지 않음
  - 컨트랙트 코드 보유
  - 거래나 메시지를 수신하면 보유하고 있는 컨트랙트 코드를 실행

##### 주소 생성

- 개인키 생성 256bit 무작위숫자 -> 64 Hex로 인코딩
- 타원곡선전자서명 알고리즘(ECDSA, secp256k1)으로 공개키 생성
- Keccak-256 해싱
- 계정생성

##### 상태(State)

- 어느 한 시점에 대한 Ether의 잔액, 기타 정보를 담고 있는 계정(Account)들의 집합
- 모든 이더리움의 참여 노드는 로컬에 상태 정보를 유지함
- stageObject
  - 상태으 ㅣ수정이 발생한 이더리움 계정을 나타내는 객체
  - 주요 데이터(go-ethereum ~/ core/state/state_objectgo)
    - Address commonAdderes -> 이더리움의 주소
    - addrHash common.hash -> 이더리움 주소의 hash 값
    - data Account -> 이더리움 계정
    - db *stateDB -> 상태를 저장할 DB
    - code Code -> 컨트랙트 코드
- stateObject를 통해 이더리움의 state database인 LevelDB에 업데이트



##### 이더리움의 사애 전이(State Transition)

- 블록 채굴로 인한 거래 내역 추가 시 상태 전이 발생
- 상태전이함수에 의해 실행
  - APPLY(S, TX) -> S' or Error

##### 트랜잭션 종류

- (외부)트랜잭션
- 내부 트랙잭션

##### 트랜잭션(Transaction)

- EOA가 다른 EOA 혹은 CA로 보내는 '서명된 메시지'
  - EOA -> EOA: ETH잔액 변경(ETH 송금)
  - EOA -> CA: 컨트랙트 코드 실행
- 계정의 상태 변화를 유도하는 일종으 트리거

##### 내부 트랜잭션(Internal Transaction)

- CA가 다른 CA혹은 EOA에게 전달하는 '서명되지 않은 메시지'
- 주로 컨트랙트 함수 호출에 사용
- 블록체인에 별도로 저장되지 않음

##### 트랜잭션 구조

- type txdata struct {

  AccountNonce // 외부 트랜잭션의 수

  Price // 트랜잭션 수수료

  GasLimit  // 트랜잭션 실행을 위해 지불할 최대 가스양

  Recipient  // 거래의 수신처

  Amount // 거래하고자 하는 ETH의 양

  Payload // 메시지, 함수 호출 등

  V, R, S // 서명정보

  }

##### 트랜잭션 리시트(Receipt)

- 트랜잭션의 실행결과를 기록
- 검색을 위한 인덱스, 해당 트랜잭션이 포함된 블록의 번호 및 해시 등 저장

##### 블록

- 이더리움 장부에 기록되는 데이터의 기본 단위

- 트랜잭션들의 집합

- 주요 데이터(go-ethereum ~core/types/block.go)

  - type Block struct {

    header

    undes

    transactions

    ...

    }

##### 엉클 블록(Uncle Block)

- 동일한 시점에 채굴된 블록 중 채굴 난이도가 낮아 메인 체인에 연결되지 못한 블록
- 블록 생성 시간이 빠를 수록 엉클 블록 발생확률이 높음
  - 비트코인 -> 약 10분, 이더리움 -> 약 14 초
- 문제점
  - 엉클 블록에 포함된 트랜잭션은 승인되지 않았기 때문에 트랜잭션 처리 지연 발생
  - 승인되지 않은 블록에 연산이 소모되어 연산량이 낭비됨
  - 평균 블록생성 시간이 늘어나 채굴 난이도가 감소하게 되어 네트워크의 보안 수준이 낮아짐

##### 작업증명(PoW, Proof of Work)

- Ethash
  - 비트코인의 PoW의 문제점(낮은 ASIC 저항성)을 개선
  - ASIC(Application Specific Integrated Circuit) 저항성을 높임
  - 계산은 어렵게, 검증은 쉽게

###### 지분증명(PoS, Proof os Stake)

- 검증자가 가진 지분(Stake)에 비례한 확률로 블록 생성 권한을 획득하고 생성된 블록을 원하는 체인에 연결, 보상 획득
- PoW의 문제점을 개선
  - 전력 소모량, 채굴 중앙화 등
- 이더리움은 Casper를 통해 PoS로 전환 중

##### Casper FFG(Friendly Finality Gadget)

- Pow + PoS의 하이브리드 방식
- 1~ 99번째 블록은 Pow, 100번째 블록은 PoS방식으로 채굴
  - 이 100번째 PoS 블록을 체크포인트(Checkpoint)라고 정의함
  - 전체 PoS 검증자들 중 2/3 이상이 투표한 블록이며 되돌릴 수 없음 -> Finalize
- 1~100 번째 블록을 Epoch라 정의함
  - 현재는 100개의 블록이 하나의 epoch를 구성
  - 추후 이 epoch의 길이를 축소해가면서 PoS블록의 비율을 늘릴 예정

##### 스마트 컨트랙트

- 스마트 컨트랙트 개발언어
  - **Solidity**: 현재 가장 많이 사용되고 있는 이더리움 스마트 컨트랙트 언어
    - 작동과정
      - 스마트 컨트랙트 작성
      - 컴파일(solc)
      - Bytecode, ABI
      - 이더리움 블록에 저장되어서 Bytecode 배포
      - Web3 같은 library를 통해 스마트 컨트랙트를 run 할 수 있음
  - SERPENT: 초창기 이더리움의 스마트 컨트랙트 언어
  - LLL: (EVM을 위한) 어셈블리 언어와 유사

##### 스마트 컨트랙트의 배포(Deployment)

- 스마트 컨트랙트를 이더리움에 배포하기 위해
  - Recipient -> 수신자 미정(0으로 할당)
  - Payload -> Bytecode와 ABI
  - 스마트 컨트랙트 배포를 위한 가스

를 담은 특수한 형태의 트랜잭션을 생성, 전송



##### 스마트 컨트랙트의 실행(Execution)

- 스마트 컨트랙트를 실행하기 위해
  - Recipient -> 해당 스마트 컨트랙트의 CA
  - Payload -> 실행하려는 함수, 매개변수
  - 스마트 컨트랙트 실행을 위한 가스를 담은 형태의 트랜잭션을 생성, 전송

##### 가스(Gas)

- 이더리움을 움직이게 하는 '기본 단위'
- 트랜잭션, 스마트 컨트랙트를 위한 수수료
  - 최대가스(gasLimit, StartGas) X 가스가격(gasPrice)
- 가스가격의 기본 단위: Gwei(1 Gwei = 0.000000001 ETH)
  - 이는 사용자가 정의할 수 있으며 단위가 클 수록 트랜잭션의 빠른 처리가 가능
  - SafeLow -> 1 Gwei, Fast -> 4Gwei
- 스마트 컨트랙트를 배포하거나 run할 때 gas가 필요함

##### 이더리움의 동작 메커니즘

- 계정, 트랜잭션의 생성 -> 서명 -> 검증 -> 채굴

### 이더리움 가상머신(EVM, Ethereum Virtual Machine)

##### EVM(Ethereum Virtual Machine)

- 이더리움 스마트 컨트랙트를 실행하기 위한 가상머신
- 특징
  - 튜링 완전 머신, 스택 기반 구조, 32 byte의 메모리
  - 이더리움 주소 연산(160bit), 256bit 암호화 알고리즘 등 이더리움 관련 구조 연산에 최적화
- 모든 동작을 수행하기 위해서는 사전에 가스가 지불되어야 함
  - 이는 Dos(Denial of Service) 공격을 방지하기 위함
- EVM의 프로그램은 내부에서만 실행되고 가상머신의 HOST 환경에서는 접근 불가능
- EVM간 메시지를 통해 데이터를 송수신 할 수 있음
- 결정적(Deterministic) 머신 -> 때문에 항상 동일 한 상태를 반환

##### EVM Stack

- 이더리움의 모든 연산은 스택에서 수행
- 피연산자(데이터), 연산의 임시 값 역시 스택에 저장
- 최대크기: 256bit * 1024

##### 스택 기반 동작의 이해

- 입력 값 -> bytecode
- 입력값 종류에 따라 다른 동작을 수행
  - 명령어: 연산(예: PUSH, ADD, MUL ...)
  - 데이터: PUSH

##### EVM Memory

- 스마트 컨트랙트 호출 시 생성
- 함수의 매개변수, 지역의 변수 및 반환 값 등을 임시적으로 저장
- 일반적인 PC의 RAM과 마찬가지로 휘발성
- EVM 첫 구동 시 크기는 0으로 설정됨 -> 접근 시 256 비트 단위로 증가
  - Memory의 크기 증가 시 가스가 반드시 소모되며 가스량을 지수적으로 증가

##### EVM Storage

- key-value 저장소
- key: 256비트, Value: 256 비트
- 스마트 컨트랙트는 자신의 storage에만 접근 가능
- 초기값은 0 -> 접근마다 확장될 수 있음
- 스마트 컨트랙트에서 사용자 정의 함수 외부에 선언된 변수를 나타냄(state variable)



##### EVM 실행을 위한 입력값: EVM code

- Bytecode 형태
  - 스마트 컨트랙트 컴파일의 결과물
  - 어셈블리:PUSH1 01 PSUH01 02...

##### 기본적으로 256 비트의 크기를 가짐

- EVM의 모든 명령어는 이더리움 yellow paper에 정의되어 있음
- 컨트랙트 생성, 삭제
  - CREATE, DELEGATECALL
- 해시 수행
  - SHA3
- 쉬프트 연산
  - MUL, DIV, SDIV
    - DIV의 경우 zero division exception은 구현되지 않음
- 스택, 메모리, 저장소
  - MLOAD, SLOAD, SSTORE, ...
- 메시지
  - CALL, CALLCODE

##### CALL 명령어를 통한 EVM 간 메시지 송수신

- EVM은 다른 EOA 혹은 CA에 메시지를 보낼 수 있음
- 데이터 전달 시 EVM의 메모리를 이용하여 함수인자, 반호나값을 전달

### 솔리디티 개요(Solidity)

##### 솔리디티란?

- 이더리움 스마트 컨트랙트 언어의 종류
  - 가장 많이 활용되는 언어
  - Java와 유사한 문법을 가짐
- 특징
  - 객체지향 언어
    - class = Contract
    - Object instance = EVM에 배포된 스마트 컨트랙트
  - 정적 타입 언어
    - 다음 노드의 주소를 저장하는 자료구조
  - 스택 기반으로 동작하는 EVM 상에서 구동

##### 솔리디티 컨트랙트의 기본 구조

```solidity
pragma solidity >=0.4.22 <0.6.0;

contract SimpleContract {
	
	uint256 data;
	
	event LogDataSet(address to, uint256 amount);
	
	constructor() public {
		data = 0;
	}
	
	function get() public view returns (uint256) {
		...
	}
	
	funciton set(uint256 data_) public {
		data = data_;
		emit LogDataSet(msg.sender, data);
	}
}
```

- contract, 멤버변수, function

##### 솔리디티 스마트 컨트랙트의 기본 구조

- Version pragma
  - 솔리디티 컴파일러 버전의 호환성 지정
- 컨트랙트 이름 정의 부분
  - 컨트랙트 이름은 CamelCase로 정의하는 것을 원칙으로 함
- 하나의 솔리디티 파일에서 여러개의 컨트랙트 정의 가능
  - 파일 내 컨트랙트를 상속, 호출, 생성 가능
- 상태변수 정의 부분
  - 컨트랙트에 저장할 상태변수들을 정의
  - 이더리움에 Key-Value 형태로 저장됨(EVM Storage)
  - 가스 소모가 타 연산에 비해 많이 소모
- 접근제어 가능
  - 상태변수의 기본 접근제어는 internal
  - public
    - 모든 함수, 컨트랙트에서 접근 가능
  - internal
    - 컨트랙트, 상속하는 컨트랙트 내부에서만 접근 가능
  - private
    - 컨트랙트 내부에서만 접근 가능
  - external
    - 컨트랙트 내/외부에서 접근 가능하나 상속은 불가능
- 함수 정의 부분
  - 컨트랙트, 외부 컨트랙트, EOA에서 호출할 수 있는 함수를 정의
- 접근제어 가능
  - public
    - EOA, 컨트랙트 내부, 외부 컨트랙트에서 호출 가능
  - internal
    - 컨트랙트 내부, 상속하는 컨트랙트 내부에서만 호출 가능
  - private
    - 컨트랙트 안에서만 호출 가능(상속 X)
  - Exteranl
    - EOA에서만 호출 가능
- 함수의 종류
  - View
    - 상태를 write하지 않는 함수
  - Pure
    - 상태를 read/write하지 않느 함수
  - Fallback
    - 함수이름, 매개변수, 반환값이 존재하지 않는 함수. 컨트랙트의 함수 호출 시 해당 함수가 존재하지 않을 때 호출 됨
  - Modifier
    - 함수의 행위를 변경
  - Payable
    - 이더를 송금해야만 호출 가능한 함수
- 자료형
  - Bool
  - Integer
    - int8, int16, ...
  - Address
    - 20 Byte의 이더리움 주소를 나타내는 자료형
    - Address.balance: 해당 주소의 잔액을 wei 단위로 반환
    - Address.transfer(): 해당 주소로 이더 전송
  - String
  - Array
  - Mapping
  - Struct