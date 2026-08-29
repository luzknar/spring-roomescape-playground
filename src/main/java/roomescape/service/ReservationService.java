package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.exception.NotFoundException;
import roomescape.model.Reservation;
import roomescape.repository.ReservationRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> read() {
        List<Reservation> reservations = reservationRepository.find();
        return reservations;
    }

    public void delete(Long id) {
        Reservation deleteReservation = reservationRepository.find().stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("삭제할 예약을 찾을 수 없습니다."));
        reservationRepository.delete(deleteReservation);
    }
}
