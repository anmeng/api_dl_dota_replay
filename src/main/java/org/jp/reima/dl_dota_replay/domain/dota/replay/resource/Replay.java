package org.jp.reima.dl_dota_replay.domain.dota.replay.resource;

import java.util.Objects;

import org.jp.reima.dl_dota_replay.domain.dota.replay.exception.NotReadyGenerateUrlException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Replay {
    
    @JsonProperty("match_id")
    private final String matchId;
    
    @JsonProperty("cluster")
    private String cluster;
    
    @JsonProperty("replay_salt")
    private String replaySalt;
    
    public String createUrl() throws NotReadyGenerateUrlException {
        final String format = "http://replay%s.valve.net/570/%s_%s.dem.bz2";
        String url = null;
        if(Objects.nonNull(matchId) &&
                Objects.nonNull(cluster) &&
                Objects.nonNull(replaySalt)) {
            url = String.format(format, cluster, matchId, replaySalt);
        }
        else {
            throw new NotReadyGenerateUrlException();
        }
        return url;
    }
}
