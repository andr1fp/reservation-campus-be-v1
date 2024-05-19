package com.enigmacamp.reservationcampus.services.impl;

import com.enigmacamp.reservationcampus.repository.AnalyticsRepository;
import com.enigmacamp.reservationcampus.services.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final AnalyticsRepository analyticsRepository;
    @Override
    public Map<String, Long> getFaciltiesCountByType() {
        List<Object[]> results = analyticsRepository.countFacilitiesByType();
        Map<String, Long> countByType = new HashMap<>();
        for (Object[] result : results) {
            countByType.put((String) result[0], (Long) result[1]);
        }
        return countByType;
    }

    @Override
    public Map<String, Long> getFaciltiesAvailability() {
        List<Object[]> results = analyticsRepository.countFacilitiesByAvailability();
        Map<String, Long> countByAvailability = new HashMap<>();
        for (Object[] result : results) {
            countByAvailability.put((String) result[0], (Long) result[1]);
        }
        return countByAvailability;
    }

    @Override
    public Map<String, Long> getTransactionCountByStatus() {
        List<Object[]> results = analyticsRepository.countTransactionsByStatus();
        Map<String, Long> countByStatus = new HashMap<>();
        for (Object[] result : results) {
            countByStatus.put((String) result[0], (Long) result[1]);
        }
        return countByStatus;
    }
}
