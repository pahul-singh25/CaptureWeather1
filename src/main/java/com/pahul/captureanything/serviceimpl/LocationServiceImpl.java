package com.pahul.captureanything.serviceimpl;

import com.pahul.captureanything.manager.ExternalCallManager;
import com.pahul.captureanything.model.LocationResponse;
import com.pahul.captureanything.model.OldLocation;
import com.pahul.captureanything.repositories.LocationRepository;
import com.pahul.captureanything.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ExternalCallManager externalCallManager;
//    public final MongoTemplate mongoTemplate;


    @Autowired
    public LocationServiceImpl(@Autowired LocationRepository locationRepository,
                               @Autowired ExternalCallManager externalCallManager) {
        this.locationRepository = locationRepository;
//        this.mongoTemplate = mongoTemplate;
        this.externalCallManager=externalCallManager;
    }

    @Override
    public List<OldLocation> getSelectedLocation(String address) {
        List<OldLocation> responseFromDb = locationRepository.findByCityAndCountry(address, "");
//        findLocationDocumentsMatchingWithDisplayName(address);
        if(responseFromDb==null || responseFromDb.isEmpty()){
           List<LocationResponse> responseFromAPI  = externalCallManager.getLocationInfo(address);
           //save this response to the database so that we do not have to make API call next time
           return  saveToTheLocationCollection(responseFromAPI);
        }else{
            return responseFromDb;
        }
    }

//    public List<OldLocation> findLocationDocumentsMatchingWithDisplayName(String sentence) {
//        // Split the sentence into words
//        List<String> words = Arrays.asList(sentence.split("\\s+"));
//
//        // Build the query
//        Criteria criteria = new Criteria().orOperator(words.stream()
//                        .map(word -> Criteria.where("displayName").regex(word, "i")) // Case-insensitive match
//                        .collect(Collectors.toList())
//        );
//
//        Query query = new Query(criteria);
//
//        return mongoTemplate.find(query, OldLocation.class);
//    }

    private List<OldLocation> saveToTheLocationCollection(List<LocationResponse> responseFromAPI) {
        List<OldLocation> toBeSavedToTheDatabase = new ArrayList<>();
        responseFromAPI.forEach(r->{
            if(r.getAddresstype()!=null && r.getAddresstype().equalsIgnoreCase("city")) {
                OldLocation loc = new OldLocation();
                String displayName = r.getDisplay_name();
                loc.setLongitude(Double.parseDouble(r.getLon()));
                loc.setLatitude(Double.parseDouble(r.getLat()));
                loc.setDisplayName(displayName);
                loc.setCity(r.getName());
                loc.setCountry(displayName.substring(displayName.lastIndexOf(",") + 1));
                toBeSavedToTheDatabase.add(loc);
            }
        });
        //save to the database
      locationRepository.saveAll(toBeSavedToTheDatabase);
      return toBeSavedToTheDatabase;
    }
}
