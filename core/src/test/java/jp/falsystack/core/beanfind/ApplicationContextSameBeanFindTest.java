package jp.falsystack.core.beanfind;

import jp.falsystack.core.member.MemberRepository;
import jp.falsystack.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        assertThatThrownBy(() -> ac.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("같은 타입 둘 이상시 빈 이름을 지정")
    void findBeanByName() {
        // given
        // when
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        // then
        assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    }
    
    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        // given
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        // when
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        // then
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    // private 이면 안된다.
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }


}
