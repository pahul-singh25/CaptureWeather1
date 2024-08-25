package com.pahul.captureanything.serviceimpl;

import com.google.common.collect.Lists;
import com.pahul.captureanything.manager.ExternalCallManager;
import com.pahul.captureanything.model.Location;
import com.pahul.captureanything.model.WeatherData;
import com.pahul.captureanything.mongo.repositories.LocationRepository;
import com.pahul.captureanything.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
   public static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

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
    public List<Location> getSelectedLocation(String name, String country) {
        List<Location> responseFromDb = locationRepository.findByNameAndCountry(name, country);
//        findLocationDocumentsMatchingWithDisplayName(address);
        if(responseFromDb==null || responseFromDb.isEmpty()){
           WeatherData weatherData = externalCallManager.getWeatherInfo(name+","+country);
           //save this response to the database so that we do not have to make API call next time
            Location locationFromWeather = weatherData.getLocation();
           return Lists.newArrayList(locationRepository.save(locationFromWeather));
        }else{
            return responseFromDb;
        }

    }


    @Override
    public Location saveIfDoNotExist(Location location) {
        List<Location> locationList = locationRepository.findByLocationId(location.createLocationId());
        if(locationList==null || locationList.isEmpty()) {
            return locationRepository.save(location);
        }
        return null;
    }


//    public List<Location> findLocationDocumentsMatchingWithDisplayName(String sentence) {
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
//        return mongoTemplate.find(query, Location.class);
//    }

}
