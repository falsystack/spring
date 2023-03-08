# 스프링 빈
## 빈
**빈 이름**
- 빈이름은 메서드 이름을 사용한다.
- 빈 이름을 직접 부여할 수 도 있다.(비권장)
  - `@Bean(name = "memberService2")`
- 빈 이름은 항상 다른 이름을 부여해야 한다. 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.


---
# 스프링 컨테이너
`ApplicationContext`를 스프링 컨테이너라 한다.

```java
@Configuration
public class AppConfig {

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
```
- 기존에는 개발자가 `AppConfig` 를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.
- 스프링 컨테이너는 `@Configuration` 이 붙은 `AppConfig` 를 설정(구성) 정보로 사용한다.
  여기서 `@Bean` 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 **스프링 컨테이너에 등록된 객체를 스프링 빈**이라 한다.
- 스프링 빈은 `@Bean` 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다. ( memberService , orderService )
- 이전에는 개발자가 필요한 객체를 `AppConfig` 를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다. 스프링 빈은 `applicationContext.getBean()` 메서드를 사용해서 찾을 수 있다.
- 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.
- 스프링 컨테이너는 설정 정보를 참고해서 컨테이너 생성시 의존관계를 주입(DI)한다.

## 스프링 컨테이너 생성

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
```

- `ApplicationContext` 를 스프링 컨테이너라 한다.
- `ApplicationContext` 는 인터페이스이다.
- 스프링 컨테이너는 XML 을 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다.
- 직전에 `AppConfig` 를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든것이다.
- 자바 설정 클래스를 기반으로 스프링 컨테이너( `ApplicationContext` )를 만들어보자.
    - `new AnnotationConfigApplicationContext(AppConfig.class);`
    - 이 클래스는 `ApplicationContext` 인터페이스의 구현체이다.
- `AppConfig`또한 스프링 빈으로 등록된다.

## 컨테이너에 등록된 모든 빈 조회

```java
AnnotationConfigApplicationContext ac = 
  new AnnotationConfigApplicationContext(AppConfig.class)

// 모든 자바 빈 출력
String[] beanDefinitionNames = ac.getBeanDefinitionNames();
for (String beanDefinitionName : beanDefinitionNames) {
  Object bean = ac.getBean(beanDefinitionName);
  System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + bean);
}

// Application Bean 출력
String[] beanDefinitionNames = ac.getBeanDefinitionNames();
for (String beanDefinitionName : beanDefinitionNames) {
  BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

  if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
    Object bean = ac.getBean(beanDefinitionName);
    System.out.println("beanDefinitionName = " + beanDefinitionName + " object = " + bean);
  }
}
```
- 모든 빈 출력하기
  - 실행하면 스프링에 등록된 모든 빈 정보를 출력할 수 있다. 
  - `ac.getBeanDefinitionNames()` : 스프링에 등록된 모든 빈 이름을 조회한다.
  - `ac.getBean()` : 빈 이름으로 빈 객체(인스턴스)를 조회한다. 
- 애플리케이션 빈 출력하기 
  - 스프링이 내부에서 사용하는 빈은 getRole() 로 구분할 수 있다. 
  - `ROLE_APPLICATION` : 일반적으로 사용자가 정의한 빈 
  - `ROLE_INFRASTRUCTURE` : 스프링이 내부에서 사용하는 빈

## 스프링 빈 조회 - 기본
스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
- `ac.getBean(빈이름, 타입)` 
- `ac.getBean(타입)`
조회 대상 스프링 빈이 없으면 예외 발생 
- `NoSuchBeanDefinitionException: No bean named 'xxxxx' available`
```java
// 이름으로 조회
MemberService memberService = ac.getBean("memberService", MemberService.class);

// 타입으로 조회
MemberService memberService = ac.getBean(MemberService.class);

// 구체타입으로 조회
MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

// 없을시 예외 발생
assertThatThrownBy(() -> ac.getBean("xxx", MemberService.class))
    .isInstanceOf(NoSuchBeanDefinitionException.class);
```
구체 타입으로 조회하는건 추천하지 않는다. 역할과 구현을 분리하자 !!

## 스프링 빈 조회 - 동일한 타입이 둘 이상
- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자. 
- `ac.getBeansOfType()` 을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
```java
// 타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다
assertThatThrownBy(() -> ac.getBean(MemberRepository.class))
    .isInstanceOf(NoUniqueBeanDefinitionException.class);

// 타입으로 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다.
MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

// 특정 타입을 모두 조회하기
Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
for (String key : beansOfType.keySet()) {
  System.out.println("key = " + key + " value = " + beansOfType.get(key));
}
```
## 스프링 빈 조회 - 상속 관계
- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
- 그래서 모든 자바 객체의 최고 부모인 Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.
```java
// 부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류가 발생한다.
assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class)).isInstanceOf(
    NoUniqueBeanDefinitionException.class);

// 부모 타입으로 조회시, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.
DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

// 특정 하위 타입으로 조회
RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);

// 부모타입으로 모두 조회
Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
assertThat(beansOfType.size()).isEqualTo(2);
for (String key : beansOfType.keySet()) {
  System.out.println("key = " + key + " value = " + beansOfType.get(key));
}
```

## BeanFactory와 ApplicationContext
![](imgs/beanfactory.png)
```text
BeanFactory <- ApplicationContext <- AnnotationConfigApplicationContext
```
### BeanFactory
- 스프링 컨테이너의 최상위 인터페이스다.
- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
- `getBean()` 을 제공한다.
- 지금까지 우리가 사용했던 대부분의 기능은 `BeanFactory`가 제공하는 기능이다.

### ApplicationContext
- `BeanFactory` 기능을 모두 상속받아서 제공한다.

![](imgs/applicationcontext.png)

**메시지소스를 활용한 국제화 기능**
  - 예를 들어서 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력 
**환경변수**
- 로컬, 개발, 운영등을 구분해서 처리 
**애플리케이션 이벤트**
- 이벤트를 발행하고 구독하는 모델을 편리하게 지원
**편리한 리소스 조회**
- 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

## 다양한 설정 형식 지원 - 자바 코드, XML
