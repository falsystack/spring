# Language
- [KR.md](KR.md)

# Spring Container

`ApplicationContext`をSpring Containerとよぶ。

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
- SpringContainer`@Configuration`が付いた`AppConfig`
  を構成（設定）情報として使用する。ここでは@Beanが付いたメソッドを全部呼び出して返ってきたオブジェクトをSpringContainerに登録する、こうやってSpringContainerに登録されたオブジェクトをSpring
  Beanとよぶ。
- Spring Beanは`@Bean`が付いたメソッド名をSpringBeanの名前として使用する(`memberService`等々)
- 以前には開発者が必要なオブジェクトを`AppConfig`を使用して直接照会したが、これからはSpringContainerを通してSpringBeanを探さなければならない。
- SpringBeanは`applicationContext.getBean()`メソッドを使用して探せる。
- SpringContainerは設定情報を参考にしてコンテナー生成時依存関係（依存性）を注入する。

# SpringContainer生成

```java
  ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
```

- `ApplicationContext` をSpringContainerと呼ぶ（厳密には違う）
- `ApplicationContext` はインタフェースだ。
- SpringContainerを`XML`を基盤として作れるしアノテーション基盤のJava設定クラスでも作れる。
    - `new AnnotationConfigApplicationContext(AppConfig.class);`
    - このクラスは`ApplicationContext` インタフェースを具現化した物である。
- `AppConfig`もSpringBeanとして登録される。

## Spring Bean
**Bean Name**
- BeanNameはメソッドネームを利用する。
- Beanの名前を直接設定することもできるが推奨しない。
    - `@Bean(name = "memberService2")`
- Beanの名前は他のBeanと被れてはいけない。
  - 被った場合他のBeanが無視されたり上書きされる。

