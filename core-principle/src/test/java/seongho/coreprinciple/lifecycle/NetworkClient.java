package seongho.coreprinciple.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + "  message: " + message);
    }

    public void disconnect(){
        System.out.println("close: " + url);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {     //InitializingBean의 매서드. 빈 생성과 의존관계 주입 끝나면 호출되는 콜백함수
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {        //DisposableBean의 매서드. 빈이 종료될 때 호출
//        System.out.println("close: " + url);
//    }

    @PostConstruct
    public void init() {     //InitializingBean의 매서드. 빈 생성과 의존관계 주입 끝나면 호출되는 콜백함수
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {        //DisposableBean의 매서드. 빈이 종료될 때 호출
        System.out.println("close: " + url);
    }
}
