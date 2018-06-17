import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test1 {

    @Test
    public void zipping() {
        Flux<String> titles = Flux.just("Mr.", "Mrs.");
        Flux<String> firstNames = Flux.just("John", "Jane");
        Flux<String> lastNames = Flux.just("Doe", "Blake");
        Flux<String> names = Flux
                .zip(titles, firstNames, lastNames)
                .log()
                .map(t -> t.getT1() + " " + t.getT2() + " " + t.getT3())
                .log();
        //StepVerifier.create(names).expectNext("Mr. John Doe", "Mrs. Jane Blake").verifyComplete();

        Flux<Long> delay = Flux.interval(Duration.ofMillis(5));
        Flux<String> firstNamesWithDelay = firstNames.zipWith(delay, (s, l) -> s);
        Flux<String> namesWithDelay = Flux
                .zip(titles, firstNamesWithDelay, lastNames)
                .map(t -> t.getT1() + " " + t.getT2() + " " + t.getT3());
        //StepVerifier.create(namesWithDelay).expectNext("Mr. John Doe", "Mrs. Jane Blake").verifyComplete();
    }

}
