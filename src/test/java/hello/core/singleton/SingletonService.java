package hello.core.singleton;

public class SingletonService {
    //1. static 영역에 객체를 딱 1개만 생성해둔다. static 필드로 자신의 인스턴스를 공유.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 추가 객체 생성을 못하게 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
    /* 싱글톤 패턴 문제점
        1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
        2. 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다. (구체클래스.getInstance())
            -> 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
        3. 객체를 미리 셋팅해버리기 때문에 내부 속성을 변경하거나 초기화 하기 어렵다.
            -> 테스트하기 어렵다.
        4. private 생성자로 자식 클래스를 만들기 어렵다.
        => 결론적으로 유연성이 떨어진다. 안티패턴으로 불리기도 한다.
    */
}
