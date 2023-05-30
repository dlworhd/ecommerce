<div align="center"> 
<img width="50%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/ee852a3c-00f8-4f95-a617-d395bcccb6cb">
</div>

<br>

<div align="center"><h5> Prototype Design </h5></div><br>

<div align="center"> 
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/84ec6185-8b7c-424d-a227-a52f3c14ecf9">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/507526fe-cc96-495d-9406-470ff5ef5280">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/f73c0272-f75d-4780-aab3-f818fa2de772">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/0da5076f-dc24-4f66-a023-1b83d63bf872">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/317c7289-f0de-435a-acd0-cab5a1cb7894">
</div>
<div align="center"> 
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/e9a08e8d-aac6-45b9-a1d6-92e9e8f4ea61">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/55f5950b-fd66-4373-823a-fd2ce2cee26a">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/a456aeec-a732-4c0e-a0ba-21beb68087d8">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/c63ca1af-82d8-4c45-b307-d5c18368d1ec">
<img width="19%" src="https://github.com/scvefg/msa-ecommerce-backend/assets/102597172/fe1b9698-40ab-462b-bcd8-af63c9fec40d">
</div>

# Module Separation

|Module|Detail|
|--|--|
|API-Gateway Server|각각 포트가 다른 서버들의 요청을 가로챈 후 원하는 사전(JWT Token 검증) & 사후 필터링을 하고 라우팅 기능을 제공하는 서버|
|Eureka Server|서버 상태 및 다수의 포트 관리를 위한 서버|
|User Server|회원가입 및 로그인, 로그아웃, 정보 수정 및 JWT Token 발급을 위한 서버|
|Seller Server|판매자 등록, 탈퇴 및 판매자 JWT Token 발급을 위한 서버|
|Product Server|상품 등록, 수정, 삭제 및 조회 등의 서비스를 제공하는 서버|
|Order Server|상품 주문과 관련된 프로세스를 처리하는 서버|
|Payment Server|주문 후 결제를 담당하는 서버(KakaoPay API)|
|Shipment Server|결제가 완료되었을 때 이벤트 기반 메시지 큐를 받아 배송을 처리하는 서버|

# 주요 사용 기술

- Java 17
- SpringBoot 3.0.6
- Spring Data JPA 3.0.6
- QueryDSL 5.0.0
- Spring Kafka 3.0.4
- MySQL 8.0.32

# 패키지 구조

```
   └── Module
        ├── XXXXApplication.java
        ├── adapter
        │   ├── in
        │   │   ├── XXXXController.java
        │   │   └── XXXXControllerImpl.java
        │   └── out
        │       ├── XXXXEntity.java
        │       ├── XXXXPersistenceAdapter.java
        │       └── XXXXRepository.java
        ├── application
        │   ├── port
        │   │   ├── in
        │   │   │   └── XXXXUseCase.java
        │   │   └── out
        │   │       └── XXXXPersistencePort.java
        │   └── service
        │       └── XXXXService.java
        └── domain
            └── XXXXDto.java
```
<!-- 
### Adapter
- Adpater 구현체를 사용하여 Port를 통해 인프라 관련 계층으로 접근할 수 있다.

### UseCase
- 비즈니스와 관련된 로직들을 일련의 과정으로 묶어 추상화한 인터페이스이다.



- 비즈니스 도메인의 명확한 정의
- 특정 서비스 독립적인 확장 가능
- 결합도를 낮출 수 있으며, 독립적인 수정, 빌드, 배포 가능
- 독립적인 서버에서 장애 발생 시 서버 장애가 전파되지 않음
- 사양 비례 비용 폭이 크게 증가하는 스케일 업(Scale-Up)에 비해 스케일 아웃(Scale-Out)은 장비를 추가하고 수평적으로 확장할 수 있어 비용이 저렴하기 때문에,
  기존 모놀리식 보다는 마이크로 서비스 아키텍처 방식을 선택

  -->
