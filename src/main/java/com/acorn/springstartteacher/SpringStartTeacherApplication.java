package com.acorn.springstartteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Bean : 스프링 컨테이너에서 관리 하는 객체
@SpringBootApplication
public class SpringStartTeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStartTeacherApplication.class, args);
		//실행이 안되는 이유!!
		//스프링은 컨테이너에서 관리하는 객체를 주입하는 형태로 언어가 작성 되는데 이것을 관점지향언어라 부른다.
		//그런데 spring-mybatis 를 라이브러리로 사용 하면 db 접속을 컨테이너에서 관리한다.
		//이때 db 접속 내역이 없으면 오류가 발생
	}
	//war vs jar
	//war : main 함수가 톰캣에게 있고 우리가 작성한 웹앱을 war로 톰캣에 배포 => 톰캣이 jvm 실행해서 웹앱을 배포
	//main이 있다는 것은 spring boot 가 jar 로 배포된다는 뜻
	//spring boot jar : spring 이 jvm 을 실행하고 톰캣의 서블릿을 사용 => 스프링 jar 를 배포

}
