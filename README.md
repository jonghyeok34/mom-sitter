# 1. 환경 구성
## 1. api
- java : open jdk 11
- gradle : 7.2 
- spring boot : 2.5.5
- mysql: 8
- redis 
## 2. build
- docker : 20.10.8

# 2. 실행 가이드

1. mysql db, redis, app 올리기
```
$ docker-compose up -d
```

# 3. DB정보

![images](documents/images/company-db.jpg)



## USER : 유저 정보
| **key** | **설명** |
| --- | --- | --- |
| MEMBER_NO | 유저 넘버(PK) | 
| SITTER_NO | 시터 내용 넘버(FK) |
| USER_ID | 생년월일 | 
| EMAIL | 생년월일 | 
| NAME | 생년월일 | 
| USER_TYPE | 생년월일 | 
| PASSWORD | 생년월일 | 
| BIRTHDATE | 생년월일 |
| GENDER | 성별 | 
| REQUEST_INFO | 신청 내용 |
| CREATED_AT | 생성일 |
| UPDATED_AT | 수정일 |


  `MEMBER_NO` bigint NOT NULL AUTO_INCREMENT,
  `BIRTHDATE` date NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `GENDER` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `REQUEST_INFO` varchar(1000) DEFAULT NULL,
  `USER_ID` varchar(255) NOT NULL,
  `USER_TYPE` varchar(30) NOT NULL,
  `SITTER_NO` bigint DEFAULT NULL,
  `CREATED_AT` datetime(6) NOT NULL,
  `UPDATED_AT` datetime(6) NOT NULL,

## SITTER_DETAIL : 시터 상세정보
| **key** | **설명** |
| --- | --- | --- |
| SITTER_NO | 시터 넘버(PK) |
| INTRODUCE | 자기 소개	 | 
| MAX_CARE_AGE | 케어 가능한 최대 연령 |
| MIN_CARE_AGE | 케어 가능한 최소 연령 |
| CREATED_AT | 생성일 | 
| UPDATED_AT | 수정일 | 

## CHILD_INFO : 아이 정보
| **key** | **설명** |
| --- | --- | --- |
| CHILD_NO | 아이 넘버(PK) |
| MEMBER_NO | 유저 넘버 | 
| BIRTHDATE | 생년월일 |
| GENDER | 성별 |
| CREATED_AT | 생성일 | 
| UPDATED_AT | 수정일 | 

# 4. 요청사항
```
https://github.com/mfort-inc/momsitter-2021-1th-api-assignment
```