package seongho.coreprinciple.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import seongho.coreprinciple.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;        //프록시로 처리

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}