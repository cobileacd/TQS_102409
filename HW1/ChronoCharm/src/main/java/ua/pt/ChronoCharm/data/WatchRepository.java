package ua.pt.ChronoCharm.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pt.ChronoCharm.data.Watch;

import java.util.List;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {

    public Watch findByWatchId(Long watchId);
    public List<Watch> findAll();

}