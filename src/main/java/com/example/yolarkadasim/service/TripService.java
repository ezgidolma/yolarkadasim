package com.example.yolarkadasim.service;

import com.example.yolarkadasim.model.Trip;
import com.example.yolarkadasim.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TripService {

    @Autowired
    public TripRepository tripRepository;

    public List<Trip> getTrips(){
      return tripRepository.findAll();
    };

    public Optional<Trip> getTripById(String id){
        return  tripRepository.findById(id);
    }

    public Trip addTrip(Trip seyahat){
        return tripRepository.save(seyahat);
    }

     public Trip updateTrip(Trip seyahat){
        Optional<Trip> optionalTrip = getTripById(seyahat.getSeyahatId());

        if (optionalTrip.isPresent()){
            Trip currentTrip = optionalTrip.get();

            currentTrip.setSurucuId(seyahat.getSurucuId());
            currentTrip.setBosKoltukSayisi(seyahat.getBosKoltukSayisi());
            currentTrip.setSigaraDurumu(seyahat.isSigaraDurumu());
            currentTrip.setHayvanDurumu(seyahat.isHayvanDurumu());
            currentTrip.setBitisNoktasi(seyahat.getBitisNoktasi());
            currentTrip.setBaslangicNoktasi(seyahat.getBaslangicNoktasi());
            currentTrip.setUcret(seyahat.getUcret());
            currentTrip.setTarih(seyahat.getTarih());

            return tripRepository.save(currentTrip);
        }

        return null;
     }

     public void  deleteTrip(String id){
        tripRepository.deleteById(id);
      }

      public List<Trip> searchTrip(String baslangicNoktasi, String bitisNoktasi, LocalDate tarih, int bosKoltukSayisi){
        return tripRepository.findFilteredTrips(baslangicNoktasi,bitisNoktasi,tarih,bosKoltukSayisi);
      }
}
