package com.teamproject.tftranker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SummonerNameToPuuId {
  @Value("${riot.key}")
  private String key;
  public String summonerNameToPuuId(String summonerName) {

    String requestURL =
        "https://kr.api.riotgames.com/tft/summoner/v1/summoners/by-name/"
            + summonerName
            + "?api_key="
            + key;

    BufferedReader br = null;
    {
      try {
        URL url = new URL(requestURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        String line;
        String result ="";
        while((line = br.readLine()) !=null){
          result += line;
        }
        return result;
      } catch (IOException ignored) {

      }
    }
    /**
     * Todo: 예외처리 해야함
     */
    return null;
  }
}
