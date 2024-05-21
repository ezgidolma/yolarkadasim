package com.example.yolarkadasim.service;


import com.example.yolarkadasim.model.Reservation;
import com.example.yolarkadasim.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations(){
       return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(String id){
        return reservationRepository.findById(id);
    }

    public Reservation addReservation(Reservation rezervasyon){
        return reservationRepository.save(rezervasyon);
    }

    public Reservation updateReservation( Reservation rezervasyon){

        Optional<Reservation> optionalReservation = reservationRepository.findById(rezervasyon.getRezervasyonId());

        if (optionalReservation.isPresent()){
            Reservation currentReservation = optionalReservation.get();

            currentReservation.setRezervasyonTarihi(rezervasyon.getRezervasyonTarihi());
            currentReservation.setDurum(rezervasyon.isDurum());
            currentReservation.setKullaniciId(rezervasyon.getKullaniciId());
            currentReservation.setSeyahatId(rezervasyon.getSeyahatId());

            return reservationRepository.save(currentReservation);
        }
        else {
            return null;
        }
    }

    public void deleteReservation(String id){
        reservationRepository.deleteById(id);
    }
}
