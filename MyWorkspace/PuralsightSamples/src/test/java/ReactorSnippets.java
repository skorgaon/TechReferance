import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class ReactorSnippets {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );



    @Test
    public void simpleCreation() {
        /*
        Flux<String> fewWords = Flux.just("Hello", "World");
        fewWords.subscribe(System.out::println);

        Flux.just("Hello", "World")
                .subscribe(System.out::println);

        Flux<String> manyWords = Flux.fromIterable(words);
        manyWords.subscribe(System.out::println);

        Flux.fromIterable(words)
            .subscribe(System.out::println);
        */

        // iterating for Map.Entry
        HashMap<String, String> aMap = new HashMap<>();
        aMap.put("Sandeep", "Korgaonkar");
        aMap.put("Ashwini", "Korgaonkar");
        aMap.put("Sohum", "Korgaonkar");
        aMap.put("Mangesh", "Korgaonkar");
        aMap.put("Arnav", "Korgaonkar");
        aMap.put("Manasee", "Korgaonkars");
        List<String> aList = new ArrayList<>();

        for (Map.Entry aEntry : aMap.entrySet()) {
            System.out.println((String) aEntry.getKey() + (String) aEntry.getValue());
            //aList.add(aEntry.getKey() + " - " + (String) aEntry.getValue());
            aList.add(String.format("%s <=> %s", aEntry.getKey(), aEntry.getValue()));
        }

        Flux.fromIterable(aList)
                .subscribe(System.out::println);

    }

    @Test
    public void simpleHashMap() {
        /*
        HashMap<Integer, String> aHashMap = new HashMap<Integer, String>();
        aHashMap.put(101,"Let us C");
        aHashMap.put(102, "Operating System");
        aHashMap.put(103, "Data Communication and Networking");
        //Flux<HashMap<Integer, String>> manyWords = Flux.collectMap(aHashMap);

        manyWords.subscribe(System.out::println);
        System.out.println();
        manyWords.subscribe(System.out::println);
        */
    }

    @Test
    public void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);
    }

    @Test
    public void restoringMissingLetter() {
        Mono<String> missing = Mono.just("s");
        Flux<String> allLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(missing)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        allLetters.subscribe(System.out::println);
    }


    /*
    @Test
    public void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscriptionMillis(500));

        helloPauseWorld.subscribe(System.out::println);
    }
    @Test
    public void blocks() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello")
                        .concatWith(Mono.just("world")
                                .delaySubscriptionMillis(500));

        helloPauseWorld.toStream()
                .forEach(System.out::println);
    }

    @Test
    public void firstEmitting() {
        Mono<String> a = Mono.just("oops I'm late")
                .delaySubscriptionMillis(450);
        Flux<String> b = Flux.just("let's get", "the party", "started")
                .delayMillis(400);

        Flux.firstEmitting(a, b)
                .toIterable()
                .forEach(System.out::println);
    }
    */
}
