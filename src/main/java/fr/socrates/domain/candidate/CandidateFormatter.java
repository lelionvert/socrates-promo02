package fr.socrates.domain.candidate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CandidateFormatter {
    public List<String> format(List<Candidate> candidateList) {
        if(candidateList.isEmpty()) { return Collections.singletonList("No email to print"); }
        return candidateList
                .stream()
                .map(Candidate::toString)
                .collect(Collectors.toList());
    }
}
