package seongho.coreprinciple.Singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();     //static. 스택 영역에 인스턴스 1개만 생성

    //이 매서드를 통해서 인스턴스 접근
    public static SingletonService getInstance(){
        return instance;
    }

    //생성자를 private으로 선언
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직");    }

}
