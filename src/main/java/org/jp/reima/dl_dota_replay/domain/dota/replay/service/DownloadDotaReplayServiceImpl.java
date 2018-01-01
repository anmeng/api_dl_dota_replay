package org.jp.reima.dl_dota_replay.domain.dota.replay.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jp.reima.dl_dota_replay.domain.dota.replay.exception.NotReadyGenerateUrlException;
import org.jp.reima.dl_dota_replay.domain.dota.replay.exception.RestReturnsIsNotExpectedException;
import org.jp.reima.dl_dota_replay.domain.dota.replay.resource.Replay;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DownloadDotaReplayServiceImpl implements DownloadDotaReplayService {

    private Client client = ClientBuilder.newClient();

    public String createUrl(String matchId) {
        String url = null;
        try {
            String json = callRestService(matchId);
            Replay replay = toReplay(json);
            url = replay.createUrl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotReadyGenerateUrlException e) {
            e.printStackTrace();
        } catch (RestReturnsIsNotExpectedException e) {
            e.printStackTrace();
        }
        return url;
    }
    
    private String callRestService(String matchId) {
        WebTarget target = client.target("https://api.opendota.com/api/replays")
                .queryParam("match_id", matchId);
        String json = null;
        try {
            json = target.request().get(String.class);
        } catch (BadRequestException e) {
            throw e;
        }
        return json;
    }
    
    private Replay toReplay(String json) throws JsonParseException, JsonMappingException, IOException, RestReturnsIsNotExpectedException {
        ObjectMapper mapper = new ObjectMapper();
        List<Replay> replays = mapper.readValue(json, new TypeReference<List<Replay>>() {});
        if(replays.size() == 1) {
            return replays.get(0);
        } else {
            throw new RestReturnsIsNotExpectedException();
        }
    }
}
