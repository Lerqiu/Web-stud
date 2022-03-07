package lerqiu.Zadanie1.Controlers;

import lerqiu.Zadanie1.Repository.Records;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cleaner {
    Records records;

    public Cleaner(Records records){
        this.records = records;
    }

    @DeleteMapping(path = "/delete", params = "name")
    public ResponseEntity<Long> apply(@RequestParam String name){
        if(name.length() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        if(records.remove(name))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
