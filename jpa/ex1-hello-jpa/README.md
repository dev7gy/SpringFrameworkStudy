# Postgresql
## posgresql 구조
출처: https://kimdubi.github.io/postgresql/pg_schema/
```
database : \l 로 조회
{
    schema : \dn 으로 조회,  table의 집합, mysql 에서의 논리 database와 동일한 개념 
    {
        table: \dt로 조회
        {
        }
    }
}
```
- test 데이터베이스 생성하기
```
=> create database test;
WARNING:  could not flush dirty data: Function not implemented
=> \l
```

- test 데이터베이스로 이동하기
```
=> \c test DBMS유저명
```

-  test 데이터베이스 안에 test_schema 생성하기
```
=> create schema test_schema authorization DBMS유저명;
=> \dn 
```

- 생성한 schema 사용하기: mysql에서 use database와 동일한 개념
```
=> set search_path to "$user", test_schema;
```

- table 생성하기
```
=> CREATE TABLE Member (
   id BIGINT NOT NULL PRIMARY KEY
   ,name VARCHAR (255)
   );
=> \dt 
```