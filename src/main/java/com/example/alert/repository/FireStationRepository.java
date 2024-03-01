package com.example.alert.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.alert.model.FireStation;
import com.example.alert.util.Util;

@Repository
public class FireStationRepository {
    public List<FireStation> fireStationsList = new ArrayList<FireStation>();
    public Map<Integer, List<String>> mapFireStation = new HashMap<Integer, List<String>>();

    public FireStationRepository() throws IOException {
        FireStation[] fireStationArray = Util.getAccess().getFireStations();//return array
        for (FireStation firestation : fireStationArray) {
            fireStationsList.add(firestation);
            if (!mapFireStation.containsKey(Integer.valueOf(firestation.getStation()))) {//key not present
                mapFireStation.put(Integer.valueOf(firestation.getStation()), new ArrayList<String>());//set key and value
            }
            mapFireStation.get(Integer.valueOf(firestation.getStation())).add(firestation.getAddress());
        }
    }
    public List<String> getAddressByStationNumber(int stationNumber) {
        return mapFireStation.get(stationNumber);
    }
    public List<FireStation> getFireStationByAddress(String stationAddress) {
        int i = 0;
        ArrayList<FireStation> sationByAddressList = new ArrayList<FireStation>();
        while (i < fireStationsList.size()) {
            if (fireStationsList.get(i).getAddress().equals(stationAddress) && !sationByAddressList.contains(fireStationsList.get(i))) {
                sationByAddressList.add(fireStationsList.get(i));
            }
            i++;
        }
        return sationByAddressList;
    }
}