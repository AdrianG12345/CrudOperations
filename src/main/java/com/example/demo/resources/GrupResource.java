package com.example.demo.resources;

import com.example.demo.dto.GrupuriDTO;
import com.example.demo.entity.Grupuri;
import com.example.demo.service.GrupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grup")
public class GrupResource {
    private final GrupService grupService;

    public GrupResource(GrupService grupService) {
        this.grupService = grupService;
    }

    @GetMapping
    public ResponseEntity<List<Grupuri>> getALlGroups()
    {
        List<Grupuri> grups = grupService.getAllGroup();
        return new ResponseEntity<>(grups, HttpStatus.OK);
    }

    @GetMapping("/getGrupById/{id}")
    public ResponseEntity<Grupuri> getGroup(@PathVariable("id") int id)
    {
        Grupuri grup = grupService.getGroup(id);
        return new ResponseEntity<>(grup, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Grupuri> addGroup(@RequestBody Grupuri grupuri)
    {
        Grupuri grup = grupService.saveGroup(grupuri);
        return new ResponseEntity<>(grup, HttpStatus.CREATED);
    }

    @PutMapping("/updateGroup")
    public ResponseEntity<Grupuri> updateGroup(@RequestBody GrupuriDTO grupuriDTO)
    {
        Grupuri grup = grupService.updateGroup(grupuriDTO);
        return new ResponseEntity<>(grup, HttpStatus.OK);
    }

    @DeleteMapping("/deleteGroup/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") int id)
    {
        grupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
