# [Spring MVC](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/dashboard)/[private_practice](https://github.com/dev7gy/SpringMVC.git)
# Web 기본 개념
### Web Server
- HTTP 기반으로 동작, 정적 리소스 제공
    - NGINX, APACHE 등이 존재.

### WAS
- HTTP 기반으로 동작, 프로그램 코드를 실행하여 로직을 수행
    - Tomcat, Jetty, Undertow 등이 존재

### 웹 시스템 구성
- web browser -> web server(정적) -> was(동적) -> DB

### servlet
- http 스펙을 편리하게 사용할 수 있게 해주는 것.
- http 요청, 응답 흐름
    - 1. WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
    - 2. 개발자는 Request 객체에서 http 요청 정보를 편리하게 꺼내서 사용
    - 3. 개발자는 Response 객체에 http 응답 정보를 편리하게 입력
    - 4. WAS는 Response 객체에 담겨있는 내용으로 http 응답 정보를 생성

#### 서블릿 컨테이너
- 서블릿을 지원하는 WAS 안에 존재
- 서블릿 객체의 생명주기를 관리
    - 서블릿 객체는 싱글톤으로 관리 -> 서블릿 컨테이너 종료시 함께 종료 !공유 변수 사용 주의!
    - JSP도 서블릿으로 변환되어서 사용
    - 동시요청을 위한 멀티 쓰레드 처리 지원
- 서블릿을 지원하는 WAS == 서블릿 컨테이너, 톰캣

#### 동시 요청 - 멀티 쓰레드
!중요! 서블릿 객체를 누가 호출하는가?
- 쓰레드
    - 애플리케이션 코드를 하나하나 순차적으로 실행함
    - 한번에 하나의 코드 라인만 수행
    - 동시처리가 필요할 땐 쓰레드를 추가 생성
    - !중요! 요청마다 쓰레드 생성
        - 동시 요청을 처리할 수 있다.
        - 하나의 쓰레드가 지연되어도, 나머지 쓰레드는 정상 동작한다.
        - 쓰레드 생성 비용이 비싸기 때문에, 응답속도가 느려진다.
        - 컨텍스트 스위칭 비용이 발생한다. 
        - 생성에 제한이 없어서 CPU가 감당할 수 없는 요청이 들어오게 된다.
        - 단점들을 커버하기 위해 쓰레드풀을 쓰고 쓰레드가 필요할때 빌려 쓰는 형태가 된다.

- WAS 튜닝 포인트
    - MAX Thread수
    - Apache ab, 제이미터, nGrinder
    
### 정적리소스, 동적페이지, API 
### SSR, CSR
- CSR
    - 1. 클라이언트가 서버에 HTML을 요청
    - 2. 서버가 내용이 없는 HTML과 자바스크립트 링크를 응답함
    - 3. 클라이언트가 다시 자바스크립트 링크를 통해 서버에 자바스크립트를 요청 
    - 4. 서버가 자바스크립트 코드로 응답함.
    - 5. 클라이언트에서 서버의 HTTP API를 호출해서 데이터를 요청함
    - 6. 클라이언트에서 자바스크립트를 통해 HTML 결과를 렌더링함.

### Spring Web
- Web Servlet: Spring MVC
- Web Reactive: Spring WebFlux, 비동기처리, 서블릿 기술 사용X


## Servlet Sample
- log 남기기 tip: logging.level.org.apache.coyote.http11=debug
- index.html 위치: main/webapp/index.html

### HttpServletRequest
- Http요청 메시지
    - start line
    - 헤더
    - body
- 임시 저장소 기능
- 세션 관리 기능
