package lerqiu.Zadanie1.Controlers;

import lerqiu.Zadanie1.Records.Status;
import lerqiu.Zadanie1.Repository.Records;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Register {
    Records records;

    public Register(Records records) {
        this.records = records;
    }

    @GetMapping(path = "/register", params = "name")
    public ResponseEntity<?> apply(@RequestParam String name) {
        Status p = records.incAndGet(name);
        return new ResponseEntity<>(new Status("Ok", p.count()), HttpStatus.OK);
    }
}
