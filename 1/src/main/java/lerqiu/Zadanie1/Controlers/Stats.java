package lerqiu.Zadanie1.Controlers;

import lerqiu.Zadanie1.Repository.Records;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class Stats {

    Records records;

    public Stats(Records records) {
        this.records = records;
    }

    Map<String, Integer> getThreeFirst() {
        return records.getAllSorted(false).entrySet().stream()
                .limit(3)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }

     Map<String, Integer> getAll() {
        return records.getAllSorted(false);
    }

     Map<String, Integer> getAll_IgnoreCase() {
        return records.getAllSorted(true);
    }

    @GetMapping(path = "/stats")
    @ResponseBody
    public  ResponseEntity<Map<String, Integer>> apply(@RequestParam(required = false)  String mode) {

        String[] acceptedParams = {"ALL", "IGNORE_CASE"};

        if (mode != null && !mode.equals(acceptedParams[0]) && !mode.equals(acceptedParams[1]))
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        if (mode == null)
            return new ResponseEntity<>(getThreeFirst(), HttpStatus.OK);

        if (mode.equals(acceptedParams[0]))
            return new ResponseEntity<>(getAll(), HttpStatus.OK);

        return new ResponseEntity<>(getAll_IgnoreCase(), HttpStatus.OK);
    }
}
