# 블로그 검색 서비스
Kakao Api 를 활용한 블로그 검색 및 인기 검색어 목록 조회 기능을 구현한 프로젝트입니다.       
[API 문서](http://localhost:8080/docs/index.html)

## 환경
- java 11
- Spring Boot 2.7.9
- Gradle 7.6.1

## 기능 정의
1. 블로그 검색
2. 인기 검색어 목록

## 기능 확인
**0. jar 파일을 다운받아 실행합니다.** ```java -jar JAR_FILE_NAME.jar```
                         

**1. 블로그 검색**
```
$ curl 'http://localhost/v1/blogs?query=kakao&sort=ACCURACY&page=1&size=10' -i -X GET \
    -H 'Endpoint: http://localhost'
```
- query 는 검색하고자 하는 블로그의 키워드입니다. **(필수)**
- sort 는 검색 결과 정렬 방식(ACCURACY || RECENCY)입니다.
- page 는 현재 검색하는 페이지 번호(1 ~ 50)를 의미합니다.
- size 는 현재 검색된 페이지에 보여질 문서 수(1 ~ 50)를 의미합니다.
                    
**2. 인기 검색어 목록**
```
$ curl 'http://localhost/v1/terms' -i -X GET \
    -H 'Endpoint: http://localhost'
```
- limit 을 주어 최대 몇개까지 가져올지 설정할 수 있습니다. (기본 10)

***자세한 사항은 상단 API 문서를 통해 확인하실 수 있습니다. (jar 파일 실행 후 접근이 가능합니다.)***

## 추가 개선 사항

## 빌드 결과 확인
[Download .jar]()