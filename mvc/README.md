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

## !중요! HTTP 스펙이 제공하는 요청, 응답 메시지 자체를 이해해야 함
- HttpServletRequest과 HttpServletResponse는 HTTP 요청메시지, HTTP 응답 메시지를 편리하게 사용하도록 도와주는 객체일 뿐이다.

### HttpServletRequest
- Http요청 메시지
    - start line
    - 헤더
    - body
- 임시 저장소 기능
- 세션 관리 기능

### Http 요청 데이터
- GET - 쿼리 파라미터: Client에서 Server로 데이터를 전달할 때는 HTTP 메시지 바디를 사용하지 않기 때문에 content-type이 없음.

- POST - HTML FORM
	- content-type: application/x-www-form-urlencoded

- HTTP message body
	- content-type: json, xml, plain/text
	- json을 객체로 바꿔주기 위해서는 ObjectMapper 클래스가 필요
		```
		// SpringBoot는 기본적으로 jackson을 사용한다. gson도 따로 라이브러리 추가 후 사용 가능
		import com.fasterxml.jackson.databind.ObjectMapper;

		private ObjectMapper objectMapper = new ObjectMapper();
		
		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) {
			ServletInputStream inputStream = request.getInputStream();
			String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		

			MyData = data1 = objectMapper.readValue(messageBody, MyData.class);
		}
		```

### HttpServletResponse
- Http응답 메시지
    - Http 응답코드 지정
    - 헤더 생성
    - 바디 생성
- Content-Type
- 쿠키
- Redirect 지원

### Http 응답 데이터
- Text 전송(text/plain)
```
response.setContentType("text/plain");
response.setCharacterEncoding("utf-8");
```
- HTML 전송(text/html)
```
response.setContentType("text/html");
response.setCharacterEncoding("utf-8");
```
- Data 전송(application/json)
```
response.setHeader("Content-Type", "application/json");
// "application/json"은 기본적으로 utf-8
```

## Servlet -> JSP -> MVC
- Servlet: java에 html을 넣어줌
- JSP: html에 java를 넣어줌.
- MVC - Model, View, Controller 
    - View와 로직부분은 변경 라이프 사이클이 다르다.
    - 요청 -> Controller -> 서비스 -(모델)-> View -> 응답
    - 서블릿을 Controller로 JSP를 View로 이용, Model은 HttpServletRequest객체의 내부 저장소를 사용한다.
    ```
    WEB-INF/views/members.jsp
     jsp의 taglib 기능 사용 <c:forEach>를 사용하기 위함
    ```
### MVC패턴 - 한계
- 아직도 반복되는 코드가 존재한다.
- 사용 안 하는데 작성해야 하는 코드가 있다.(response)
```
dispatcher.forward(request, response)
```
- front Controller 패턴을 사용해서 해결해보자! 

### MVC - Front Controller
- Front Controller 서블릿 하나로 클라이언트의 요청을 받음
- Front Controller가 요청에 맞는 컨트롤러를 찾아서 호출
- request요청을 받는 곳은 Front Controller 하나로 받는다.
- 공통 처리 가능
- Front Controller를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨

- V1
```
장점: 서블릿 하나로 받기, 나머지 컨트롤러 서블릿 사용하지 않게 하기
단점: 모든 컨트롤러에 뷰로 이동하는 부분이 중복된다.
```