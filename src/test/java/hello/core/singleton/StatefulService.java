package hello.core.singleton;

/**싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에,
 * 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
 * 무상태(stateless)로 설계해야 한다!
   - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
   - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다! 가급적 읽기만 가능해야 한다.
   - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
 */
public class StatefulService {
    private int price; //상태를 유지하는 필드. 싱글톤이기 때문에 공유되는 필드.

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; //문제! 다른 스레드가 중간에 끼어들면 값이 변경된다.
    }

    public int getPrice(){
        return price;
    }
}
