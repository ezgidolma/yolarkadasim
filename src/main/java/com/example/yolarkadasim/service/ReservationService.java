package com.example.yolarkadasim.service;


import com.example.yolarkadasim.model.Reservation;
import com.example.yolarkadasim.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    public ReservationRepository reservationRepository;

    public List<Reservation> getReservations(){
       return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Integer id){
        return reservationRepository.findById(id);
    }

    public Reservation addReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Integer id, Reservation reservation){

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isPresent()){
            Reservation currentReservation = optionalReservation.get();

            currentReservation.setRezervasyon_tarihi(reservation.getRezervasyon_tarihi());
            currentReservation.setDurum(reservation.isDurum());
            currentReservation.setKullanici_id(reservation.getKullanici_id());
            currentReservation.setSeyahat_id(reservation.getSeyahat_id());

            return reservationRepository.save(currentReservation);
        }
        else {
            return null;
        }
    }

    public void deleteReservation(Integer id){
        reservationRepository.deleteById(id);
    }
}
