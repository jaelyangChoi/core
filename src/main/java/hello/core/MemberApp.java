package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        //스프링 컨테이너
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/
        //MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "최재량", Grade.BASIC);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());
    }
}
