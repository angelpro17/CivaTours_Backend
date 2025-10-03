package com.civa.challenge.civatours.platform.bus.application.internal.queryservices;

import com.civa.challenge.civatours.platform.bus.domain.model.aggregates.Bus;
import com.civa.challenge.civatours.platform.bus.domain.model.queries.GetAllBusesQuery;
import com.civa.challenge.civatours.platform.bus.domain.model.queries.GetBusByIdQuery;
import com.civa.challenge.civatours.platform.bus.domain.services.BusQueryService;
import com.civa.challenge.civatours.platform.bus.infrastructure.persistence.jpa.repositories.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BusQueryServiceImpl implements BusQueryService {
    private final BusRepository busRepository;

    @Override
    public Optional<Bus> handle(GetBusByIdQuery query) {
        return busRepository.findById(query.busId());
    }

    @Override
    public Page<Bus> handle(GetAllBusesQuery query, Pageable pageable) {
        return busRepository.findAll(pageable);
    }
}
