import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varane on 10/1/22.
 */
public class EvictionManager {
    private Map<ReplacementPolicy, List<EvictionProcessor>> evictionProcessorMap;

    public EvictionManager() {
        this.evictionProcessorMap = new HashMap<>();
        this.evictionProcessorMap.put(ReplacementPolicy.LRU, Arrays.asList(new L1LruFifoEvictionProcessor(), new L2LruFifoEvictionProcessor()));
//        this.evictionProcessorMap.put(ReplacementPolicy.FIFO, Arrays.asList(new L1PlruEvictionProcessor()));
        this.evictionProcessorMap.put(ReplacementPolicy.OPT, Arrays.asList(new L1OptEvictionProcessor(), new L2OptEvictorProcessor()));
    }

    public EvictionProcessor getEvictionProcessorFor(ReplacementPolicy policy, CacheType type){
        return this.evictionProcessorMap
                .get(policy)
                .stream()
                .filter(processor -> processor.getSupportedType().equals(type))
                .findFirst()
                .get();

    }
}
