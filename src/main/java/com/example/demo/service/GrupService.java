package com.example.demo.service;

import com.example.demo.dto.GrupuriDTO;
import com.example.demo.entity.Grupuri;
import com.example.demo.repository.GrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class GrupService {
    @Autowired
    private GrupRepository grupRepository;


    ///handler for putting data in DB
    public Grupuri saveGroup(Grupuri grup)
    {
        grupRepository.save(grup);
        return grup;
    }
    //handler for getting all data from DB
    public List<Grupuri> getAllGroup(){
        return grupRepository.findAll();
    }

    public String deleteGroup(int id)
    {
        Grupuri foundById = grupRepository.findById(id).get();
        if (foundById != null)
        {///daca exista
            grupRepository.delete(foundById);
        }
        return "Deleted with success.";
    }
    ///handler for update object
    public Grupuri updateGroup(GrupuriDTO grupDTO)
    {
        int id = grupDTO.getGroup_id();
        Grupuri foundById = grupRepository.findById(id).get();
        Grupuri grup = new Grupuri(grupDTO.getGroup_id(), grupDTO.getGroupNr());
        if (foundById != null)
            grupRepository.save(grup);

        return grup;
    }
    ///handler for fetch a single row of data
    public Grupuri getGroup(int id)
    {
        Grupuri foundById = grupRepository.findById(id).get();
        return foundById;
    }
}
