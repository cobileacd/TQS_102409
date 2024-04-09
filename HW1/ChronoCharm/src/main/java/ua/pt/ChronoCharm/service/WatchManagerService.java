package ua.pt.ChronoCharm.service;

import org.springframework.stereotype.Service;
import ua.pt.ChronoCharm.data.WatchRepository;
import ua.pt.ChronoCharm.data.Watch;

import java.util.List;
import java.util.Optional;

@Service
public class WatchManagerService {
    final WatchRepository watchRepo;

    public WatchManagerService(WatchRepository watchRepo) {
        this.watchRepo = watchRepo;
    }

    public Watch save(Watch watch) {
        return watchRepo.save(watch);
    }

    public List<Watch> getAllWatches() {

        return watchRepo.findAll();
    }

    public Optional<Watch> getWatchDetails(Long watchId) {
        return Optional.of(watchRepo.findByWatchId(watchId) );
    }
}