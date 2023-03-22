# 블로그 검색 서비스
Kakao Api 를 활용한 블로그 검색 및 인기 검색어 목록 조회 기능을 구현한 프로젝트입니다.       
[API 문서](http://localhost:8080/docs/index.html)

## 환경
- java 11
- Spring Boot 2.7.9
- Gradle 7.6.1

## 기능 정의
1. 블로그 검색
    - ```/v1/blogs``` 를 호출합니다.
    - 먼저 kakao 블로그 검색 API 를 호출한 뒤, 결과를 반환합니다.
    - REST_API_KEY 가 없거나 검색 필수 값(query) 가 없는 경우 에러가 발생합니다.
    - 위 두가지 경우를 제외한 다른 에러 발생 시, naver 블로그 검색 API 를 호출합니다.
    - 결과 반환 전, 별도 스레드로 블로그 검색에 사용된 키워드를 저장합니다.
2. 인기 검색어 목록
    - ```/v1/terms``` 를 호출합니다.
    - 레디스에 저장된 키워드 중 Top10 키워드를 조회하여 반환합니다.

## 기능 확인
**0-1. jar 파일을 다운받아 실행합니다.**
- 실행 전 로컬 redis 설치가 필요합니다. (host: localhost, port: 6379)
- ex. ```java -jar JAR_FILE_NAME.jar```
                         

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

## 빌드 결과 확인
[Download .jar](https://drive.google.com/file/d/1sxVrnLZdUl2-H741Vjcb21PYFdOGLM4d/view?usp=share_link)
