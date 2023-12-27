package hello.advanced;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        //return new FieldLogTrace(); // 싱글톤으로 등로된 빈이라 한번에 여러쓰레드가 동시에오면 동시성 문제 있음
        return new ThreadLocalLogTrace();
    }
}
