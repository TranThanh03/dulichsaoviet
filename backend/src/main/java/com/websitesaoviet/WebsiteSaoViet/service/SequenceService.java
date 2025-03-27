package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.entity.Sequence;
import com.websitesaoviet.WebsiteSaoViet.repository.SequenceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SequenceService {
    SequenceRepository sequenceRepository;

    public int getNextNumber(String type) {
        int year = Year.now().getValue();

        Sequence sequence = sequenceRepository.findByTypeAndYear(type, year)
                .orElseGet(() -> new Sequence(null, type, year, 0));

        int nextNumber = sequence.getLastNumber() + 1;
        sequence.setLastNumber(nextNumber);
        sequenceRepository.save(sequence);

        return nextNumber;
    }
}