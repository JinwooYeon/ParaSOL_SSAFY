![image-20220520100612412](README.assets/image-20220520100612412.png)

>  BaaS 플랫폼 구축을 위한 API 서버 설계/구축

## 팀 소개

> 자율 프로젝트 기업연계반 1팀

- 김나경
- 김형준
- 선민기
- 연진우
- 오나연
- 조항준

<br>

## 기획 의도 및 배경

- 인터넷 전문 은행의 코어뱅킹 시스템과 연계하기 위한 표준화된 Rest API 서버를 제공함으로써 여러 외부 핀테크 업체와의 제휴서비스를 제공
- 계정계 메인 시스템과 Frontend를 Decoupling 하여 다양한 형태의 모바일 App을 구현, 고객에게 제공하기 위한 환경을 제공
- BaaS란 Banking as a Service를 말함

<br>

## 프로젝트 목표

- 뱅킹서비스 제공에 필요한 기능별로 API 목록화하여 정의하고 해당 목록별 API 서버 서비스를 구현
- API 호출 시 적용 가능한 모든 보안 기능을 검토하여 구현
- 각종 홍보 이벤트 및 거래 Peak 일을 감안하여 Transaction을 효율적으로 소화할 수 있는 아키텍쳐를 설계 및 구축

<br>

### [📊 중간발표 PPT](README.assets/Presentation/ParaSOL_중간발표.pdf)

### [📊 최종발표 PPT](README.assets/Presentation/ParaSOL_최종발표.pdf)

## 기술 스택

### 🌏 환경 버전

#### 형상관리 <br>
<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira software&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/gitlab-yellow?style=for-the-badge&logo=gitlab&logoColor=FC6D26">&nbsp;
<img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/webex-316AFF?style=for-the-badge&logo=cisco&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/google sheets-34A853?style=for-the-badge&logo=google sheets&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/code_with_me-000000?style=for-the-badge&logo=IntelliJ idea&logoColor=white">&nbsp;

<br>

### ⛏ 기술 스택

#### Backend

![Generic badge](https://img.shields.io/badge/Java-11.0.2-007396.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/SpringBoot-2.6.6-6DB33F.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/QueryDSL-5.0.0-0089CF.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/JPA-2.4-BAAE85.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Lombok-1.18.24-B23B23.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Spring_Webflux-2.6.6-6DB33F.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Spring_Cloud_Gateway-3.1.2-6DB33F.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/GRPC-1.3.5-244B5A.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/MySQL-5.7-4479A1.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/C++-20-00599C.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Java_Net_Socket-20-007396.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Java_JWT-3.10.3-000000.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/OAuth2_Client-2.5.4-000000.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Spring_Security-4.3.18-6DB33F.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Gradle-7.4.1-02303A.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Docker-20.10.16-2496ED.svg)&nbsp;


#### Frontend 
**[Web]**<br>
![Generic badge](https://img.shields.io/badge/React-18.0.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/TypeScript-4.6.3-3178C6.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Styled_Components-5.3.5-DB7093.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Axios-0.26.1-5A29E4.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Copy_To_Clipboard-5.1.0-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Dom-17.0.2-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Scripts-17.0.2-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Web_Vitals-2.1.4-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Emotion/React-11.9.0-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Emotion/Styled-11.8.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Mui/Lab-5.0.0-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Mui/Material-5.6.2-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Testing_Library/Jest_Dom-5.16.4-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Testing_Library/React-13.1.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Testing_Library/User_Event-13.5.0-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/Jest-27.4.1-3178C6.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/Node-16.11.27-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/React-18.0.5-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/React_Dom-18.0.1-61DAFB.svg)&nbsp;


**[App]**<br>
![Generic badge](https://img.shields.io/badge/React_Native-0.64.3-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo-44.0.2-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React-17.0.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/TypeScript-4.6.4-3178C6.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Styled_Components-5.3.5-DB7093.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Axios-0.27.2-5A29E4.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@React_Native_Async_Storage/Async_Storage-1.15.0-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@React_Navigation/Bottom_Tabs-6.3.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@React_Navigation/Native-6.0.10-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@React_Navigation/Native_Stack-6.6.2-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/Styled_Components-5.1.25-DB7093.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/Styled_Components_React_Native-5.1.3-DB7093.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Native_Picker_Select-8.0.4-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Native_Qrcode_svg-6.1.2-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Native_Safe_Area_Context-3.3.2-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Native_Screens-3.10.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Native_Svg-12.1.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Native_Web-0.17.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Application-4.0.1-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Barcode_Scanner-11.2.0-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Camera-12.1.2-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Clipboard-2.1.0-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Device-4.1.0-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Local_Authentication-12.1.0-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/Expo_Splash_Screen-0.14.1-000020.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/React_Dom-17.0.1-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Babel/Core-7.12.9-F9DC3E.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/Jest-27.5.0-3178C6.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/React-17.0.21-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/React_Native-0.67.6-61DAFB.svg)&nbsp;
![Generic badge](https://img.shields.io/badge/@Types/React_Test_Renderer-18.0.0-61DAFB.svg)&nbsp;


<br>

## 🤙 컨벤션

### JIRA

- Epic은 행동 단위 명사
- 기본 8~10시간, 주당 40시간
  - 스프린트 시작 전에 그 주의 일정을 보고 조율
- Task 명은 Git Commit Message와 동일 (Emoji만 제외)
- 월요일 17시에 다음 스프린트로 넘어갑니다.

### GIT

> Git Branch 컨벤션

```
master
develop
release
hotfix
feature/frontend/기능명
feature/backend/기능명
```

> Git Commit 메세지 컨벤션 & 템플릿

- 스페이스 주의
- 이모지 추가해주세요
- 백프론트 구분 가능한 커밋은 [FE/BE] 넣어주세요
``ex) Feat : ✨ [BE] 새로운 기능 추가 ``

```
Feat : ✨ 새로운 기능 추가
Fix : 🐛 버그 수정
Docs : 📚 문서 수정
Chore : 📝 그 외 자잘한 작업
Test : ☔️ 테스트 코드
Build : 🏹 시스템 또는 외부 종속성에 영향을 미치는 변경사항 (npm, gulp, yarn 레벨)
CI : 🎡 CI관련 설정
Style : 🎨 코드 의미에 영향을 주지 않는 변경사항 (포맷, 세미콜론 누락, 공백 등)
Refactor : 💡 성능 개선
Merge : 🤝 기능 개발 후 브랜치 병합
Docker : 🐳 도커 구성
DB : 🐬 MySQL 데이터베이스 특정 (마이그레이션, 스크립트, 확장 등)
Study : 📖 공부
Settings : ⚙️ 환경설정
Design : ✏️ 기획
```

### 스크럼 회의

- 오전에는 일정 끝나고 조례 미팅 후 5분 이내로 디코로 모여 팀 미팅 15분 내로 반드시 종료
- 팀장 회의 끝나고 전달사항 있으면 간단하게 미팅
- 오후에는 반별 종례 미팅 후 5분 이내로 Jira 등 테스크 정리하고 15분 내로 반드시 종료

### 네이밍

```
1. 함수명:
  - Frontend: PascalCase 사용, 동사 형태 Create()
  - Backend: camelCase 사용, 동사 형태 create()
2. URI: kebab-case 사용 /kebab-eat
3. 코드 내 변수명: camelCase 사용 const goodAfterNoon = AftetNoon()
4. DB 컬럼명: snake_case 사용 user_id
5. 구조체명, 클래스명: PascalCase 사용 class AfterNoon { }
6. 클래스 멤버 변수명: camelCase 사용 private string description
7. 인수 변수명: camelCase create(int userId)
8. 인터페이스 명: I + PascalCase 사용 interface IGreatBread { }
9. 열거형 객체명: E + PascalCase 사용 enum EGreatBread { }
10. JAVA 패키지명: snake_case 사용
```

<br>

## 프로젝트 구성도

### 아키텍처

> 서버

![image-20220519220112790](README.assets/image-20220519220112790.png)

> MSA

![image-20220519220129226](README.assets/image-20220519220129226.png)

> Auto Scaling

![오토스케일링](README.assets/오토스케일링.gif)

### ERD

[ERD](README.assets/Parasol ERD)

### [📂 와이어 프레임](https://www.figma.com/file/AojpGMxBn5gG9AP9ntbfTF/ParaSOL?node-id=157%3A2379)

### [📝 명세서 작성](https://docs.google.com/spreadsheets/d/1EJGdwgTLKDMG2TD3CdbwNqSU4w2ebX2s2LZdYw5q3yg/edit?usp=sharing)

### 시퀀스다이어그램

고객정보 등록

![고객정보등록](README.assets/Parasol Sequence Diagram/고객정보등록.JPG)

계좌 등록

![계좌등록](README.assets/Parasol Sequence Diagram/계좌등록.JPG)

계좌정보 등록

![계좌정보등록](README.assets/Parasol Sequence Diagram/계좌정보등록.JPG)

뱅킹정보 등록

![뱅킹정보등록](README.assets/Parasol Sequence Diagram/뱅킹정보등록.JPG)

은행 연결 [백엔드]

![은행연결](README.assets/Parasol Sequence Diagram/은행연결.JPG)

은행 연결 [프론트엔드]

![은행연결_프론트](README.assets/Parasol Sequence Diagram/은행연결_프론트.JPG)

입금

![입금](README.assets/Parasol Sequence Diagram/입금.JPG)

결제

![결제](README.assets/Parasol Sequence Diagram/결제.JPG)

계좌목록 조회

![계좌목록조회](README.assets/Parasol Sequence Diagram/계좌목록조회.JPG)

거래내역 조회

![거래내역조회](README.assets/Parasol Sequence Diagram/거래내역조회.JPG)

### Gateway

![image-20220519220226455](README.assets/image-20220519220226455.png)

<br>

## 구현 기능

- 각종 뱅킹서비스를 제공할 수 있는 거래 별 API 구현
  - 인증(로그인, 2차인증 등)
  - 계좌 목록 조회
  - 계좌 잔액 조회
  - 계좌 거래내역 조회
  - 입/지금 실행 - 각종 페이 충전에 대응
- OAuth 2.0 등 표준을 이용한 SNS 및 플랫폼 계정 인증 구현
- API 기능별 단위 테스트를 위한 프론트 엔드 구현
- API 서버는 AWS 등 클라우드 환경에 구축하여 컨테이너를 활용한 MSA 구축
- 일부 레거시 시스템 연계를 위한 Gateway 구현
- 대량 트랜잭션 발생 시 소화 가능한 아키텍쳐 설계 아이디어 제시
  - AWS 등 클라우드 환경의 기능 활용
  - Kubenete 등 컨테이너 관리 플랫폼 등 활용
- 부정 Access, 침해 방지 등 정보보호관점 설계 아이디어 제시

### 📟 Web Dashboard

**서비스 소개**

- 뱅킹 서비스 제공에 필요한 기능별로 API를 목록화하여 정의하고 구현
- API 기능별 단위 테스트를 위한 웹 대시보드
- Swagger, Postman을 모티브로 하여 레이아웃 및 디자인 설계
- API 개발 완료 여부, HTTP method, 기능명, URI 등 Rest API의 정보에 대해 쉽게 알아볼 수 있음
- 입력, 출력값과 응답 코드를 표시
- 사용자가 직접 입력하는 값과 자동으로 입력되는 값(ex. header token)을 구분

**기술 스택**

- React JS
- HTML5/CSS
- Axios
- styled components
- Material UI

**주요 기능**

> 기능 별 API 목록 분류

- 유저, 계좌, 인증 및 페이 탭

| ![image-20220518174522157](README.assets/image-1.png) | ![image-20220518174544683](README.assets/image-2.png) |
| ----------------------------------------------------- | ----------------------------------------------------- |

![image-20220518174554843](README.assets/image-3.png)

> 입력값, 출력값 확인

- 사용자가 직접 입력하는 데이터는 활성화
- default로 입력되는 데이터는 비활성화(ex. jwt token header)

| ![image-20220518174611235](README.assets/image-4.png) | ![image-20220518174617105](README.assets/image-5.png) | ![image-20220518174622670](README.assets/image-6.png) |
| ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- |

| ![image-20220518174632165](README.assets/image-7.png) | ![image-20220518174636524](README.assets/image-8.png) |
| ----------------------------------------------------- | ----------------------------------------------------- |

### 📱 Mobile App

**서비스 소개**

- ParaSOL 앱은 코어뱅킹 시스템과 연계하기 위한 표준 RestAPI 서버가 제공되는 프로토타입의 모바일 앱
- React Native를 이용하여 구현한 반응형 모바일 어플리케이션
- 인증된 사용자 기반 서비스 제공
- Google OAuth 인증 지원

**기술 스택**

- React Native
- Expo
- HTML5/CSS
- Axios
- styled components

**주요 기능**

> 인증된 사용자 기반 서비스

- 로그인, 회원가입, 비밀번호 재설정

![로그인](README.assets/ParaSOL%20APP/01로그인.gif)

- 회원정보, 비밀번호 수정, 회원탈퇴

| ![로그인](README.assets/ParaSOL%20APP/02회원정보.gif) | ![로그인](README.assets/ParaSOL%20APP/08회원탈퇴.jpg) |
| ----------------------------------------------------- | ----------------------------------------------------- |

> 계좌 조회

- 월 별로 잔액 조회, 거래내역 조회
- 계좌 연결

| ![로그인](README.assets/ParaSOL%20APP/07거래내역.gif) | ![로그인](README.assets/ParaSOL%20APP/03계좌관리하기.gif) |
| ----------------------------------------------------- | --------------------------------------------------------- |

> 페이

- 연결된 계좌에 충전
- 연결된 계좌에서 출금

| ![로그인](README.assets/ParaSOL%20APP/04충전하기.gif) | ![로그인](README.assets/ParaSOL%20APP/05출금하기.gif) |
| ----------------------------------------------------- | ----------------------------------------------------- |

> 송금

- 입력된 계좌로 송금
- QR 스캐너로 계좌 정보 입력

![로그인](README.assets/ParaSOL%20APP/06송금하기.gif)

> 2차인증

- 생체 인증 - 지문 인식

| ![로그인](README.assets/ParaSOL%20APP/09생체인증정보등록.gif) | ![로그인](README.assets/ParaSOL%20APP/10생체인증송금.gif) |
| ------------------------------------------------------------ | --------------------------------------------------------- |



