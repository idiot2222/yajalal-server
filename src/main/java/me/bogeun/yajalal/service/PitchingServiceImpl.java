package me.bogeun.yajalal.service;

import lombok.RequiredArgsConstructor;
import me.bogeun.yajalal.entity.match.Pitching;
import me.bogeun.yajalal.entity.player.Player;
import me.bogeun.yajalal.repository.player.PitchingRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PitchingServiceImpl implements PitchingService {

    private final PitchingRepository pitchingRepository;

    @Override
    public Pitching getPitchingByPlayer(Player player) {
        return pitchingRepository.findByPlayer(player).orElse(new Pitching(player));
    }

    @Override
    public void savePitching(Pitching pitching) {
        pitchingRepository.save(pitching);
    }
}
