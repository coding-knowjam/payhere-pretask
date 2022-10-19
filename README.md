### 테이블 설계
ddl/ddl.sql : 데이터베이스 생성, 사용자 생성 및 권한 부여 스크립트
````
users : 고객 정보를 담는 테이블 
ledger : 가계부의 내역을 담는 테이블
````

----

### 기능 요구사항 정리
1. 고객은 이메일과 비밀번호를 입력을 통해서 회원 가입을 할 수 있습니다.
2. 고객은 회원 가입이후, 로그인과 로그아웃을 할 수 있습니다.
    ````
    a. 로그인 관련 인증은 토근을 사용해서 처리 (JWT 토근)
    b. 로그아웃 미구현 상태
    ````
3. 고객은 로그인 이후 가계부 관련 아래의 행동을 할 수 있습니다.
    ````
    a. 가계부에 오늘 사용한 돈의 금액과 관련된 메모를 남길 수 있습니다.
    b. 가계부에서 수정을 원하는 내역은 금액과 메모를 수정 할 수 있습니다.
    c. 가계부에서 삭제를 원하는 내역은 삭제 할 수 있습니다.
       삭제한 내역은 언제든지 다시 복구 할 수 있어야 합니다.
    d. 가계부에서 이제까지 기록한 가계부 리스트를 볼 수 있습니다.
    e. 가계부에서 상세한 세부 내역을 볼 수 있습니다.
    ````
4. 로그인하지 않은 고객은 가계부 내역에 대한 접근제한 처리가 되어야 합니다.
    ````
    토큰 기반 검증을 통해 URL에 대한 접근제한 구현필요
    ````
   
----

### API 정리
* 회원가입 
  * URL :  /users
  * Method : POST
  * Payload 예시
     ````JSON
     {"email" : "hel1lo@gmail.com" ,"password" : "password"}
     ````
    
* 로그인
   * URL :  /login
   * Method : POST
   * Payload 예시
      ````JSON
      {"email" : "hel1lo@gmail.com" ,"password" : "password"}
      ````

* 가계부 내역 작성
   * URL :  /ledgers
   * Method : POST
   * HTTP Header Authorization : JWT token 필요
   * Payload 예시
      ````JSON
      {
        "title" : "노트북 구입"
       ,"usedMoney" : "1300000"
       ,"content" : "애플스토어 판교점에서 맥북프로 구입"
     }
      ````
* 가계부 내역 수정
   * URL :  /ledgers/{ledgerId}
   * Method : PUT
   * HTTP Header Authorization : JWT token 필요
   * Payload 예시
      ````JSON
      {
        "title" : "노트북 구입"
       ,"usedMoney" : "1300000"
     }
      ````
* 가계부 내역 삭제
   * URL :  /ledgers/{ledgerId}
   * Method : DELETE
   * HTTP Header Authorization : JWT token 필요


* 가계부 내역 복구
   * URL :  /ledgers/{ledgerId}
   * Method : POST
   * HTTP Header Authorization : JWT token 필요

* 가계부 내역 전체 조회
   * URL :  /ledgers
   * Method : GET
   * HTTP Header Authorization : JWT token 필요

* 가계부 상세내역 조회
   * URL :  /ledgers{ledgerId}
   * Method : GET
   * HTTP Header Authorization : JWT token 필요

----

### TO-DO
* 현재 로그아웃 기능은 미구현
  * 토큰의 유효기간이 10분이므로, 그 이후에는 재 로그인 필요
  * 리프레시 토큰을 사용하여 로그인 지속가능하게도록 수정
* 기능 전반적으로 검증로직이 미 완성
  * 현재 로그인한 사용자가 다른 사용자의 내역을 볼 수 있음
  * API 요청 시점에 들어온 데이터에 대한 검증 필요
* 테스트 코드 작성 필요
  * 가계부 도메인 관련 Service, Controller 레이어 대한 테스트코드 필요
* 프로퍼티 로컬,개발,운영 분할 및 Log 설정 적용 필요
* API 문서화 라이브러리 적용 필요 

----

### 과제 후 느낀점
````
주어진 시간내에 제대로 완성하지 못해서 너무 아쉬운 마음이 큽니다.
미 완성된 상태로 제출을 드리지만, 게속 작업해서 완성 시킬 생각입니다.
과제를 하면서 미흡한 부분이나 발전해야 할 방향을 생각해볼 수 있었습니다.
경험을 하게 해주셔서 감사드립니다.

ps. Docker 이미지를 소스코드에 업로드 해달라는 말씀은 없으셔서,
직접 build 하신다고 이해하고 Dockerfile까지만 만들어 놓았습니다. 
````
