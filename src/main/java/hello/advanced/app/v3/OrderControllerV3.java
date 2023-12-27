package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//fieldTraceLog만 있음
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;


    @GetMapping("/v3/request")
    public String request(@RequestParam(name = "itemId") String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderControllerV3.request()");// 구현체가 싱글톤으로 빈등록이 되있는상태라 (fieldLogTrace) 동시성 문제 있음
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 다시 던져주어야함
        }
    }
}
