package lerqiu.Zadanie1.Repository;

import lerqiu.Zadanie1.Records.Status;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Records {
    HashMap<String, Integer> data;

    public Records() {
        data = new HashMap<>();
    }

    public Status get(String name) {
        if (data.containsKey(name))
            return new Status(name, data.get(name));
        return new Status(name, 0);
    }

    public Status incAndGet(String name) {
        Status p = get(name);
        data.put(name, p.count() + 1);
        return new Status(name, p.count() + 1);
    }

    public boolean remove(String name) {
        return data.remove(name) != null;
    }

    HashMap<String, Integer> hashMapKeysIGNORE_CASE(HashMap<String, Integer> data) {
        HashMap<String, Integer> tmp = new HashMap<>();

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String lowerCase = entry.getKey().toLowerCase(Locale.ROOT);

            if (tmp.containsKey(lowerCase))
                tmp.put(lowerCase, entry.getValue() + tmp.get(lowerCase));
            else
                tmp.put(lowerCase, entry.getValue());
        }
        return tmp;
    }

    public HashMap<String, Integer> getAllSorted(boolean ignoreCase) {
        HashMap<String, Integer> unsortedMap = ignoreCase ? hashMapKeysIGNORE_CASE(data) : data;

        return unsortedMap.entrySet().stream()
                .sorted((t0, t1) -> -t0.getValue().compareTo(t1.getValue()))
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }
}