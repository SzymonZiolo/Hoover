package hoover.service;

import org.springframework.stereotype.Service;

import hoover.dto.HooverRequest;
import hoover.dto.HooverResponse;

import java.text.StringCharacterIterator;
import java.util.*;

@Service
public class HooverService {

    public HooverResponse calculate(HooverRequest request) {
        int borderX = request.getRoomSize().get(0);
        int borderY = request.getRoomSize().get(1);
        int X = request.getCoords().get(0);
        int Y = request.getCoords().get(1);
        int patches = 0;

        List<List<Integer>> patchesCoord = request.getPatches();
        StringCharacterIterator sc = new StringCharacterIterator(request.getInstructions());
        char a = sc.first();
        for (int i = 0; i < request.getInstructions().length() ; i++) {
            List<Integer> coords = new ArrayList<>();
            coords.add(X);
            coords.add(Y);
            for (int j = 0; j < patchesCoord.size(); j++){
                if(Objects.equals(coords.get(0), patchesCoord.get(j).get(0)) && Objects.equals(coords.get(1), patchesCoord.get(j).get(1))){
                    patches++;
                    patchesCoord.remove(j);
                }
            }
            if (Objects.equals(a, 'N') && (Y < borderY)) {
                Y++;
            } else if (Objects.equals(a, 'S') && Y > 1) {
                Y--;
            } else if (Objects.equals(a, 'W') && X > 1) {
                X--;
            } else if (Objects.equals(a, 'E') && X < borderX) {
                X++;
            }
            a = sc.next();
        }
        HooverResponse response = new HooverResponse();
        List<Integer> coords = new ArrayList<>();
        coords.add(X);
        coords.add(Y);
        response.setCoords(coords);
        response.setPatches(patches);
        return response;
    }
}
