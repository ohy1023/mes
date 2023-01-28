# MES

## Project Introduction

과 선배님의 도움으로 진행되는 개인 프로젝트로, MES Project 입니다.

<br />

<br />

### 📌 주요 기능

추가 예정

<br />

<br />

### 📌 기술 스택

- frontend : React
- backend : java, spring boot, JPA, QueryDsl
- Database : Oracle

<br />

<br />

### 📌 Service Architecture

추가 예정

<br />

<br />

### 📌 ERD

추가 예정

<br />

<br />

### 📌 Endpoint

| Category | Method | URL                | Description        | note |
|----------|--------|--------------------|--------------------|------|
| 거래처      | GET    | /api/accounts      | 거래쳐 전체 조회          |      |
|          | POST   | /api/accounts      | 거래처 등록             |      |
| 품목       | GET    | /api/items         | 전체 품목 조회           |      |
|          | POST   | /api/items         | 품목 등록              |      |
|          | PUT    | /api/items         | 품목 수정              |      |
|          | DELETE | /api/items         | 품목 삭제              |      |
|          | GET    | /api/items/company | 품목 등록한 회사 이름 전체 조회 |      |
|          | GET    | /api/items/search | 품목 조건 조회 |      |

<br />

<br />


### 📌 ERROR CODE
| 에러 코드          | 설명                | HTTP status |
|----------------|-------------------|-------------|
| ITEM_NOT_FOUND | 해당 품목을 찾을 수 없는 경우 | 404         |
| DATABASE_ERROR | DB와의 연결 이상인 경우    | 500         |

<br />

<br />



### 📌 화면

![image](https://user-images.githubusercontent.com/110380812/215248164-da7896bb-dd2b-42d7-850b-8a1e7906a676.png)


<br />

<br />
