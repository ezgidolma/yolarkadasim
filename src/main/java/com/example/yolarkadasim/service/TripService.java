package com.example.yolarkadasim.service;

import com.example.yolarkadasim.model.Trip;
import com.example.yolarkadasim.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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

    public Optional<Trip> getTripById(Integer id){
        return  tripRepository.findById(id);
    }

    public Trip addTrip(Trip trip){
        return tripRepository.save(trip);
    }

     public Trip updateTrip(Integer id,Trip trip){
        Optional<Trip> optionalTrip = getTripById(id);

        if (optionalTrip.isPresent()){
            Trip currentTrip = optionalTrip.get();

            currentTrip.setSurucu_id(trip.getSurucu_id());
            currentTrip.setSeyahat_id(trip.getSeyahat_id());
            currentTrip.setBos_koltuk_sayisi(trip.getBos_koltuk_sayisi());
            currentTrip.setSigara_durumu(trip.isSigara_durumu());
            currentTrip.setHayvan_durumu(trip.isHayvan_durumu());
            currentTrip.setBitis_noktasi(trip.getBitis_noktasi());
            currentTrip.setBaslangic_noktasi(trip.getBaslangic_noktasi());
            currentTrip.setUcret(trip.getUcret());
            currentTrip.setTarih(trip.getTarih());

            return tripRepository.save(currentTrip);
        }

        return null;
     }

     public void  deleteTrip(Integer id){
        tripRepository.deleteById(id);
      }

      public List<Trip> searchTrip(String baslangic_noktasi, String bitis_noktasi, LocalDate tarih, int kisi_sayisi){

        return tripRepository.findFilteredTrips(baslangic_noktasi,bitis_noktasi,tarih,kisi_sayisi);
      }
}
